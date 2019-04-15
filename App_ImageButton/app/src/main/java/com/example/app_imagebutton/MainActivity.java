package com.example.app_imagebutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton goku;
    private ImageButton vegeta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goku = (ImageButton)findViewById(R.id.ib_goku);
        vegeta = (ImageButton)findViewById(R.id.ib_vegeta);
    }

    public void cambiarColor(View view){

        if(goku.isPressed()){
            ConstraintLayout c =(ConstraintLayout)findViewById(R.id.fondo);
            c.setBackgroundColor(Color.RED);
            Toast.makeText(this,"Goku is Pressed",Toast.LENGTH_LONG).show();
        }else if(vegeta.isPressed()){
            ConstraintLayout c =(ConstraintLayout)findViewById(R.id.fondo);
            c.setBackgroundColor(Color.YELLOW);
            Toast.makeText(this,"Vegeta is Pressed",Toast.LENGTH_LONG).show();
        }
    }
}
