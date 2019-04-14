package com.example.myapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Atrapo lo que viene desde la vista por id de componentes
        et1 = (EditText)findViewById(R.id.txt_nota1);
        et2 = (EditText)findViewById(R.id.txt_nota2);
        et3 = (EditText)findViewById(R.id.txt_nota3);
        tv1 = (TextView)findViewById(R.id.tv_estatus);
    }

    //una vez atrapados los datos de los componentes
    //Genero el método que hará la funcionalidad
    //recibe parametros de tipo View
    public void estatus(View view){
/*
        String nota1_string = et1.getText().toString();
        String nota2_string = et2.getText().toString();
        String nota3_string = et3.getText().toString();
*/
        int nota1_int = Integer.parseInt(et1.getText().toString());
        int nota2_int = Integer.parseInt(et1.getText().toString());
        int nota3_int = Integer.parseInt(et1.getText().toString());


        //calcular promedio
        int promedio = (nota1_int+nota2_int+nota3_int)/3;

        if(promedio >=6){
            tv1.setText("Estatus  aprobado "+promedio);
        }else if(promedio <=5){
            tv1.setText("Estatus reprobado "+promedio);
        }
    }
}
