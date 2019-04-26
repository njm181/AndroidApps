package com.example.app_reproduciraudios;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play;
    SoundPool sp;
    int sonido_reproduccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.btn_play);

        /*primer parametro: maximo de reproducciones simultaneas
        * segundo: tipo de stream de audio -->stream music
        * tercero: calidad de reproduccion*/
        sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 1);

        //cargar la pista de audio al objeto sp
        //load permite cargar la pista que se va a reproducir
        /*recibe parametros: context, ruta del audio y nombre de la pista y prioridad*/
        sonido_reproduccion = sp.load(this, R.raw.sound_short, 1);

    }

    //metodos para los botones
    public void audioSoundPool(View view){
        //reproducir la pista que se cargo previamente
        //recibe parametros, el identificador donde se encuentra la pista-->sonido_reproduccion
        //el volumen para las salidas izquierda y derecha
        //prioridad
        //el numero de repeticiones, -1 que siempre se esta repitiendo, 0 solo una vez, 1 se repite una vez
        //la velocidad de reproduccion, normal = 0
        sp.play(sonido_reproduccion, 1,1, 1, 0, 0);
    }

    public void playAudioMediaPlayer(View view){
        //objeto de la clase MediaPlayer
        //dos parametros, context, ruta de la pista

        MediaPlayer mp = MediaPlayer.create(this, R.raw.sound_long);
        mp.start();
    }

}
