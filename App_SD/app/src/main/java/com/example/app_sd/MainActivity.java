package com.example.app_sd;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et_nombre, et_contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombre = findViewById(R.id.txt_name);
        et_contenido = findViewById(R.id.txt_contenido);
    }

    //metodo para el boton guardar
    public  void guardar(View view){

        String nombreArchivo = et_nombre.getText().toString();
        String contenido = et_contenido.getText().toString();

        try {
            //guardar de manera temporal la ruta donde esta la tarjeta SD
            //enviroment ayuda a recuperar la ruta para guardarla dentro del objeto tarjetaSD
            File tarjetaSD = Environment.getExternalStorageDirectory();
            //muestra la ruta donde se va a guardar el archivo
            Toast.makeText(this, tarjetaSD.getPath(), Toast.LENGTH_SHORT).show();

            //crear el archivo
            File rutaArchivo = new File(tarjetaSD.getPath(), nombreArchivo);
            //abrir el archivo para poder escribirlo
            OutputStreamWriter archivoAbierto = new OutputStreamWriter(openFileOutput(nombreArchivo, Activity.MODE_PRIVATE));

            archivoAbierto.write(contenido);//escribo
            archivoAbierto.flush();//limpio buffer
            archivoAbierto.close();//cierro archivo que esta abierto

            Toast.makeText(this, "guardado correctamente", Toast.LENGTH_SHORT).show();

            //limpio los campos
            et_nombre.setText("");
            et_contenido.setText("");

        }catch(IOException e){

            Toast.makeText(this, "No se pudo guardar", Toast.LENGTH_SHORT).show();

        }
    }

    public void consultar(View view){
        //recuperar nombre del archivo a consultar
        String nombre = et_nombre.getText().toString();

        try {
            //donde esta ubicado nuestro archivo
            File tarjetaSD = Environment.getExternalStorageDirectory();

            //buscar al archivo una vez obtenida la ruta
            File rutaArchivo = new File(tarjetaSD.getPath(), nombre);

            //abrir el archivo
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombre));

            //leer el archivo
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);

            //indicarle que lea la primer linea de texto y la guarde ne varialble
            String linea = leerArchivo.readLine();

            String contenidoCompleto = "";

            while (linea != null){
                contenidoCompleto = contenidoCompleto + linea + "\n";
                linea = leerArchivo.readLine();
            }

            leerArchivo.close();
            abrirArchivo.close();
            et_contenido.setText(contenidoCompleto);

        }catch (IOException e){

            Toast.makeText(this, "Error al leer el archivo", Toast.LENGTH_SHORT).show();

        }
    }
}
