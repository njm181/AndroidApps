package com.example.frutiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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
}
