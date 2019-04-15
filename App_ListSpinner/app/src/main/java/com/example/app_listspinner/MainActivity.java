package com.example.app_listspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText valor1, valor2;
    private Spinner spinner;
    private TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //capturo los valores
        valor1 = (EditText)findViewById(R.id.txt_valor1);
        valor2 = (EditText)findViewById(R.id.txt_valor2);
        tv_resultado = (TextView)findViewById(R.id.tv_resultado);
        spinner = (Spinner)findViewById(R.id.spinner);

        //array de opciones
        String[] opciones = {"sumar","restar","multiplicar","dividir"};

        /*ArrayAdapter sirve para comunicar mi array opciones con la vista en el spinner
        * this = en que activity queremos que se muestre lo que haga
        * tipo de spinner que voy a utilizar(hay 2 tipos)
        * nombre del array que contiene el texto que queremos que se muestre dentro de nuestro componente*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_personalizado, opciones);
        //aca seteo el adapter en spinner
        spinner.setAdapter(adapter);

    }

    public void calcular(View view)
    {
        int val1 = Integer.parseInt(valor1.getText().toString());
        int val2 = Integer.parseInt(valor2.getText().toString());

        //capturo lo que esta seleccionado en el spinner y parseo a String
        String seleccion = spinner.getSelectedItem().toString();

        if(seleccion.equals("sumar")){
            int suma = val1+val2;
            String resultado = String.valueOf(suma);
            tv_resultado.setText(resultado);
        }else if(seleccion.equals("restar")) {
            int resta = val1 - val2;
            String resultado = String.valueOf(resta);
            tv_resultado.setText(resultado);
        }else if(seleccion.equals("multiplicar")) {
            int mult = val1 * val2;
            String resultado = String.valueOf(mult);
            tv_resultado.setText(resultado);
        }else if(seleccion.equals("dividir")){

            if(val2 !=0){
                int suma = val1+val2;
                String resultado = String.valueOf(suma);
                tv_resultado.setText(resultado);
            }else{
                Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_LONG).show();
            }
        }

    }
}
