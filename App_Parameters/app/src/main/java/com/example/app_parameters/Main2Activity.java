package com.example.app_parameters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv1 = findViewById(R.id.tv);

        //con variable dato capturo lo que viene por medio de la etiqueta "dato"
        String dato = getIntent().getStringExtra("dato");
        tv1.setText("Hola "+ dato);
    }

    public void volver(View view){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
