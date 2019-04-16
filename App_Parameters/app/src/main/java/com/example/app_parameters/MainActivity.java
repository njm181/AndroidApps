package com.example.app_parameters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.txt1);
    }

    public void enviar(View view){
        Intent i = new Intent(this, Main2Activity.class);
        //putExtra es para mandar al otro Activity un dato
        //"dato" es la etiqueta con la cual lo identifico en el otro Activity
        //el segundo parametro es lo que quiero enviar
        i.putExtra("dato", et1.getText().toString());
        startActivity(i);

    }
}
