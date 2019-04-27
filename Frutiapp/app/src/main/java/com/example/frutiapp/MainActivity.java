package com.example.frutiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre;
    private ImageView iv_personaje;
    private TextView tv_bestScore;
    private MediaPlayer mediaPlayer;

    //para que las imagenes de Personaje sean aleatorias
    int num_azar = (int)(Math.random()*10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = findViewById(R.id.txt_nombre);
        iv_personaje = findViewById(R.id.iv_personaje);
        tv_bestScore = findViewById(R.id.tv_bestScore);

        //agregar icono al actionbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //que se elija personaje
        int id;//ruta de la imagen
        if (num_azar == 0 || num_azar == 10){
            //obtener la ruta de la imagen y nombre
            id = getResources().getIdentifier("mango","drawable", getPackageName());
            //colocar dentro de imageview
            iv_personaje.setImageResource(id);
        }else if (num_azar == 1 || num_azar == 9){
            //obtener la ruta de la imagen y nombre
            id = getResources().getIdentifier("fresa","drawable", getPackageName());
            //colocar dentro de imageview
            iv_personaje.setImageResource(id);
        }else if (num_azar == 2 || num_azar == 8){
            //obtener la ruta de la imagen y nombre
            id = getResources().getIdentifier("manzana","drawable", getPackageName());
            //colocar dentro de imageview
            iv_personaje.setImageResource(id);
        }else if (num_azar == 3 || num_azar == 7){
            //obtener la ruta de la imagen y nombre
            id = getResources().getIdentifier("sandia","drawable", getPackageName());
            //colocar dentro de imageview
            iv_personaje.setImageResource(id);
        }else if (num_azar == 4 || num_azar == 5 || num_azar == 6){
            //obtener la ruta de la imagen y nombre
            id = getResources().getIdentifier("uva","drawable", getPackageName());
            //colocar dentro de imageview
            iv_personaje.setImageResource(id);
        }

        //crear objeto de base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "BD", null, 1);
        //para poder escribir en la base de datos
        SQLiteDatabase BD = admin.getWritableDatabase();

        //consulta a la BD
        Cursor consulta = BD.rawQuery(
                "select * from puntaje where score = (select max(score) from puntaje)", null);

        //si encontro algun puntaje o no
        if(consulta.moveToFirst()){//si encontraste algo hace lo siguiente
            String temp_nombre = consulta.getString(0);//columna nombre de la tabla
            String temp_score = consulta.getString(1);//columna score
            //agregar los valores al textview
            tv_bestScore.setText("Record: "+temp_score+" de "+temp_nombre);
            //cerrar conexion
            BD.close();
        }else{
            BD.close();
        }



        //pista de audio inicial
        //guardo mi cancion dentro del objeto mediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.alphabet_song);
        //que comience
        mediaPlayer.start();
        //y que la loopee
        mediaPlayer.setLooping(true);
    }

    public void Jugar(View view){
        //validar que ingrese nombre
        String nombre = et_nombre.getText().toString();

        if(!nombre.equals("")){
            //como ingreso nombre, hay que pasarlo al siguiente Activity
            //pero antes hay que frenar el audio
            mediaPlayer.stop();
            //destruye mediaPlayer
            mediaPlayer.release();
            //creo el paso al segundo activity
            Intent intent = new Intent(this, Nivel1.class);
            //Enviar el nombre dle jugador al otro activity
            intent.putExtra("jugador", nombre);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(this,"Primero debes escribir tu nombre", Toast.LENGTH_SHORT).show();
            //abrir el teclado para que se empiece a escribir dentro del text nombre
            et_nombre.requestFocus();
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(et_nombre, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    //metodo para controlar el boton Back <
    @Override
    public void onBackPressed(){
        //al estar vacio no hace nada
    }
}
