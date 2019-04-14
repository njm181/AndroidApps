package com.example.app_checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText txt_valor1, txt_valor2;
    private CheckBox ch_suma, ch_resta;
    private TextView tv_resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //capturo los valores de los componentes
        txt_valor1 = (EditText)findViewById(R.id.txt_valor1);
        txt_valor2 = (EditText)findViewById(R.id.txt_valor2);
        ch_suma = (CheckBox)findViewById(R.id.ch_suma);
        ch_resta = (CheckBox)findViewById(R.id.ch_resta);
        tv_resultado = (TextView)findViewById(R.id.tv_resultado);
    }

    //metodo para btnCalcular
    public void calcular(View view)
    {
        //parse los string a int para hacer las operaciones
        int valor1 = Integer.parseInt(txt_valor1.getText().toString());
        int valor2 = Integer.parseInt(txt_valor2.getText().toString());
        String resultado = "";

        if(ch_suma.isChecked())
        {
            int suma = valor1+valor2;
            resultado = "La suma es: "+suma+" / ";

        }
        if (ch_resta.isChecked())
        {
          int resta = valor1-valor2;
          resultado = resultado +" La resta es: "+resta;

        }
        tv_resultado.setText(resultado);
    }


}
