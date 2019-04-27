package com.example.frutiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Nivel1 extends AppCompatActivity {

    //objetos de este activity
    private TextView tv_nombre, tv_score;
    private ImageView iv_Auno, iv_Ados, iv_vidas;
    private EditText et_respuesta;
    private MediaPlayer mediaPlayer, mp_great, mp_bad;

    int score, numAleatorio_uno, numAleatorio_dos, resultado, vidas = 3;
    String nombre_jugador, string_score, string_vidas;

    //array que contiene el nombre de las imagenes de numero
    String numero[] = {"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);

        Toast.makeText(this, "Nivel 1 - Sumas básicas", Toast.LENGTH_SHORT).show();

        //crear relaciones entre logica y grafica
        tv_nombre = findViewById(R.id.tv_nombre);
        tv_score = findViewById(R.id.tv_score);
        iv_vidas = findViewById(R.id.imageView_vidas);
        iv_Auno = findViewById(R.id.imageView_numUno);
        iv_Ados = findViewById(R.id.imageView_numDos);
        et_respuesta = findViewById(R.id.editText_resultado);


        //obtener el nombre del jugador desde el activity de bienvenida
        nombre_jugador = getIntent().getStringExtra("jugador");
        //donde queremos colocarlo
        tv_nombre.setText("Jugador: "+ nombre_jugador);

        //agregar el icono al actionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //agregar audio de fondo
        mediaPlayer = MediaPlayer.create(this, R.raw.goats);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        mp_great = MediaPlayer.create(this, R.raw.wonderful);
        mp_bad = MediaPlayer.create(this, R.raw.bad);
        //para que se generen los numeros aleatorios ni bien se inicia la app
        NumAleatorio();
    }

    //metodo para asignar funcion al boton comprobar
    public void Comprobar(View view){
        String respuesta = et_respuesta.getText().toString();

        if(!respuesta.equals("")){

            int respuesta_jugador = Integer.parseInt(respuesta);
            //validar que sea respuesta lo que introduzca
            if(resultado == respuesta_jugador){
                mp_great.start();
                score++;
                tv_score.setText("Score: "+score);
                et_respuesta.setText("");

                //uso de la base de datos por que aumenta el score
                BaseDeDatos();

            }else{

                mp_bad.start();
                vidas--;
                //uso de la base de datos por que pierde y debe mostrar score
                BaseDeDatos();

                switch (vidas){
                    case 3:
                        iv_vidas.setImageResource(R.drawable.tresvidas);
                        break;
                    case 2:
                        Toast.makeText(this, "Te quedan 2 manzanas", Toast.LENGTH_SHORT).show();
                        iv_vidas.setImageResource(R.drawable.dosvidas);//cambiar imagen
                        break;
                    case 1:
                        Toast.makeText(this, "Te quedan 1 manzanas", Toast.LENGTH_SHORT).show();
                        iv_vidas.setImageResource(R.drawable.unavida);//cambiar imagen
                        break;
                    case 0:
                        Toast.makeText(this, "Has perdido todas tus manzanas", Toast.LENGTH_SHORT).show();
                        //volver al main
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        //detener audio
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        break;
                }
                //limpiar campo
                et_respuesta.setText("");
            }
            //independientemente de la respuesta bien o mal actualiza las imagenes con numeros
            NumAleatorio();
        }else{
            Toast.makeText(this, "Escribe tu respuesta", Toast.LENGTH_SHORT).show();
        }
    }


    //generar numeros aleatorios
    public void NumAleatorio(){//no corresponde a ningun boton por eso no recibe View

        if (score <= 9){
            //numeros aleatorios de 0 a 9
            numAleatorio_uno = (int)(Math.random()*10);
            numAleatorio_dos = (int)(Math.random()*10);

            resultado = numAleatorio_uno + numAleatorio_dos;

            //las sumas no pueden ser mayor a diez
            if(resultado <= 10){
                for(int i = 0; i <numero.length; i++){
                    //devuelve un numero que se guarda en id y abajo busca ese numero en las imagenes
                    int id = getResources().getIdentifier(numero[i], "drawable", getPackageName());

                    if(numAleatorio_uno == i){
                        iv_Auno.setImageResource(id);
                    }
                    if(numAleatorio_dos == i){
                        iv_Ados.setImageResource(id);
                    }
                }
            }else{
                //metodo recursivo, se invoca a si mismo
                //si la suma no da menor o igual a diez
                NumAleatorio();//vuelve a usarse el metodo hasta que la suma de el valor esperado
            }

        }else{
            //si el score es 10
            Intent intent = new Intent(this, Nivel2.class);
            //enviar el score y el nombre al siguiente activity
            string_score = String.valueOf(score);
            string_vidas = String.valueOf(vidas);
            intent.putExtra("jugador", nombre_jugador);
            intent.putExtra("score", string_score);
            intent.putExtra("vidas", string_vidas);

            startActivity(intent);
            finish();
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public void BaseDeDatos(){
        //para hacer CRUD a la BD
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();//apertura en modo lectura y escritura de la Bd

        //consulta para ver si hay registros
        Cursor consulta = BD.rawQuery("select * from puntaje where score = (select max(score)from puntaje)", null);
        if(consulta.moveToFirst()){

           String temp_nombre = consulta.getString(0);
           String temp_score = consulta.getString(1);

           int bestScore = Integer.parseInt(temp_score);
           //que sea el score mas alto
           if(score > bestScore){
               ContentValues modificacion = new ContentValues();
               modificacion.put("nombre",nombre_jugador);
               modificacion.put("score",score);

               //para actualizar la BD
               BD.update("puntaje", modificacion, "score="+bestScore, null);
           }
            BD.close();

        }else{

            ContentValues insertar = new ContentValues();

            insertar.put("nombre", nombre_jugador);
            insertar.put("score", score);

            BD.insert("puntaje", null, insertar);
            BD.close();

        }
    }

    //controlar el boton back
    @Override
    public void onBackPressed(){

    }
}
