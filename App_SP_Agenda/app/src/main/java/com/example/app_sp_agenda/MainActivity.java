package com.example.app_sp_agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_name, et_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.txt_name);
        et_contact = findViewById(R.id.txt_contact);
    }

    public void saveContact(View view){

        String name = et_name.getText().toString();
        String data = et_contact.getText().toString();

        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);

        //objeto para poder editar el archivo que se guarda
        SharedPreferences.Editor objEditor = preferences.edit();

        //primer parametro es la referencia para poder localizar el contacto
        //segundo parametro es los datos que queremos guardar
        objEditor.putString(name, data);
        objEditor.commit();

        Toast.makeText(this, "El contacto se guardo exitosamente", Toast.LENGTH_SHORT).show();

    }

    public void searchContact(View view){

        String name = et_name.getText().toString();

        //primer parametro es el archivo que estamos trabajando
        SharedPreferences preferences = getSharedPreferences("agenda", Context.MODE_PRIVATE);

        //aca se va a mostrar los datos que el usuario esta buscando del contacto
        //name es la referencia al contacto que estamos buscando
        String data = preferences.getString(name, "");

        if(data.length() == 0){
            //no encontro nada
            Toast.makeText(this, "No se encontro ningun contacto", Toast.LENGTH_SHORT).show();
        }else{
            //encontro el contacto
            et_contact.setText(data);

        }
    }
}
