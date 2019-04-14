package com.example.app_radiobutton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txt_valor1, txt_valor2;
    private TextView tv_resultado;
    private RadioButton rb_sumar, rb_restar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //capturar los valores por id de componente
        txt_valor1 = (EditText)findViewById(R.id.txt_valor1);
        txt_valor2 = (EditText)findViewById(R.id.txt_valor2);
        tv_resultado = (TextView)findViewById(R.id.txt_resultado);
        rb_sumar = (RadioButton)findViewById(R.id.rb_sumar);
        rb_restar = (RadioButton)findViewById(R.id.rb_restar);
    }

    //método para boton Calcular
    public void calcular(View view){

        int valor1 = Integer.parseInt(txt_valor1.getText().toString());
        int valor2 = Integer.parseInt(txt_valor2.getText().toString());

        if(rb_sumar.isChecked())
        {
            int suma = valor1+valor2;
            String resultado = String.valueOf(suma);
            tv_resultado.setText(resultado);
        }else if(rb_restar.isChecked())
        {
            int resta = valor1-valor2;
            String resultado = String.valueOf(resta);
            tv_resultado.setText(resultado);
        }


    }
}
