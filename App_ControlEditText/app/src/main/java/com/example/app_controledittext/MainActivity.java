package com.example.app_controledittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txt_nombre, txt_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_nombre = (EditText)findViewById(R.id.txt_nombre);
        txt_pass = (EditText)findViewById(R.id.txt_password);
    }

    public void registrar(View view){

        String nombre = txt_nombre.getText().toString();
        String pass = txt_pass.getText().toString();

        if(nombre.length()==0){
            Toast.makeText(this,"Ingrese nombre", Toast.LENGTH_LONG).show();
        }
        if(pass.length()==0){
            Toast.makeText(this,"Ingrese password", Toast.LENGTH_LONG).show();
        }
        if(nombre.length()!= 0 && pass.length() != 0){
            Toast.makeText(this,"Registro en proceso...", Toast.LENGTH_LONG).show();
        }
    }
}
