package com.example.app_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.txt_mail);

        //usar sharedpreferences
        //recuperar datos que ya esten guardados, puede mostrar si hay algo o vacio si no
        //"datos" es el nombre del archivo SharedPreferences que va a leer
        SharedPreferences preferences = getSharedPreferences("datos", Context.MODE_PRIVATE);

        //referencia del valor que estamos buscando "mail"
        et1.setText(preferences.getString("mail", ""));
    }

    //metodo del boton
    public void guardar(View view){
        //lo que va a crear cuando ponga guardar para que despues cuando
        // se recupere en OnCreate lo muestre
        SharedPreferences preferences = getSharedPreferences("datos", MODE_PRIVATE);
        //para guardar archivo
        //vamos a editar al objeto preferences recien creado
        SharedPreferences.Editor objEditor = preferences.edit();

        //aca guardo lo ingresado por el usuario
        objEditor.putString("mail", et1.getText().toString());

        //confirmar que lo guardo
        objEditor.commit();

        finish();
    }
}
