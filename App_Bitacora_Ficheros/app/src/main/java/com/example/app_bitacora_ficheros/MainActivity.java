package com.example.app_bitacora_ficheros;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.txt_bitacora);

        //ir a buscar el archivo que estamos utilizando
        //devuelve un arrray con los ficheros almacenados de la app
        String archivos[] = fileList();

        if(archivoExiste(archivos, "bitacora.txt")){

            try {
                InputStreamReader readFile = new InputStreamReader(openFileInput("bitacora.txt"));
                //el Buffer nos permite leer un archivo abierto por streamReader
                BufferedReader br = new BufferedReader(readFile);
                //guardo la primer linea de texto en esta variable
                // linea es ademas, para verificar que exista el archivo
                String linea = br.readLine();
                //para guardar todo el texto que vayamos encontrado en bitacora.txt
                String bitacoraCompleta = "";

                while (linea != null){
                    bitacoraCompleta = bitacoraCompleta + linea + "\n";
                    //cada vez que termine de leer una linea continua con la siguiente
                    //hasta que haya leido todo y de null para terminar el ciclo while
                    linea = br.readLine();
                }

                br.close();
                readFile.close();
                et1.setText(bitacoraCompleta);

            }catch (IOException e){
                e.printStackTrace();            }
        }
    }

    public boolean archivoExiste(String archivos[], String nombreArchivo){

        for(int i=0; i<archivos.length; i++){


            if(nombreArchivo.equals(archivos[i])){
                //si encuentra a bitacora.txt
                return true;
            }
        }
        return false;
    }

    public void guardar(View view){

        //para indicar que vamos a mandar texto a un nuevo archivo
        //que vamos a escribir
        //crea archivo bitacora.txt
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            //metodo para escribir en bitacora.txt el texto que quiero guardar
            archivo.write(et1.getText().toString());
            //flush limpia los datos del buffer, todo lo que venia dentro de archivo
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Bitacora guardada correctamente", Toast.LENGTH_SHORT).show();
        finish();

    }

}
