package com.example.ernesto.pruebascrollview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Poner el icono en action Bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    public void Selecccion(View view){
        switch (view.getId()){
            case R.id.fresas:
                Toast.makeText(this, "Estas son fresas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.manzanas:
                Toast.makeText(this, "Estas son manzanas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.piña:
                Toast.makeText(this, "Esto es una piña", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sandia:
                Toast.makeText(this, "Esto es una sandia", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bananas:
                Toast.makeText(this, "Estas son bananas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cerezas:
                Toast.makeText(this, "Estas son cerezas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.kiwis:
                Toast.makeText(this, "Estos son kiwis", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mangos:
                Toast.makeText(this, "Estos con mangos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.melon:
                Toast.makeText(this, "Este es un mélon", Toast.LENGTH_SHORT).show();
                break;
            case R.id.naranjas:
                Toast.makeText(this, "Estas son naranjas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.peras:
                Toast.makeText(this, "Estas son peras", Toast.LENGTH_SHORT).show();
                break;
            case R.id.uvas:
                Toast.makeText(this, "Estas son uvas", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
