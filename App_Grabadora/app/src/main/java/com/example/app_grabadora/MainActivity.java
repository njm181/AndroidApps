package com.example.app_grabadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaRecorder grabacion;
    private String archivoSalida = null;
    private Button btn_recorder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_recorder = findViewById(R.id.btn_rec);

        //permisos necesarios
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, 1000);
        }
    }

    public void Recorder(View view){
        //si es igual a null quiere decir que no se esta grabando nada
        if(grabacion == null){
            //vamos a grabar en un directorio dentro del dispotivo
            // para obtener ruta donde se alojan las grabaciones
            //nombre del archivo de audio.mp3
            archivoSalida = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Grabacion.mp3";
            //instacio el objeto
            grabacion = new MediaRecorder();
            //utilizar el objeto que acabo de crear
            //y vamos a grabar audio con ayuda del sensor
            grabacion.setAudioSource(MediaRecorder.AudioSource.MIC);
            //formato de salida que va a tener el audio
            grabacion.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            //ya capturado lo transformo
            grabacion.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            //el nombre que va a recbir el archivo es el de archivoSalida
            grabacion.setOutputFile(archivoSalida);

            try {
                //preparo el objeto para empezar a grabar
                grabacion.prepare();
                //aca comienza
                grabacion.start();
            }catch (IOException e){

            }
            //cambiar imagen de boton cuando empieza a grabar(boton dinamico)
            btn_recorder.setBackgroundResource(R.drawable.rec);
            Toast.makeText(this, "Grabando...", Toast.LENGTH_SHORT).show();

        } else if(grabacion != null){ //si esta grabando

            grabacion.stop();
            grabacion.release();//finalizada
            grabacion = null;
            btn_recorder.setBackgroundResource(R.drawable.stop_rec);
            Toast.makeText(this, "Grabación finalizada", Toast.LENGTH_SHORT).show();


        }
    }

    //reproducir audio
    public void Reproducir(View view){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            //archivo que quiero reproducir
            mediaPlayer.setDataSource(archivoSalida);
            mediaPlayer.prepare();//que prepare el audio que vamos a reproducir

        }catch (IOException e){

        }
        mediaPlayer.start();
        Toast.makeText(this, "Reproduciendo audio", Toast.LENGTH_SHORT).show();

    }
}
