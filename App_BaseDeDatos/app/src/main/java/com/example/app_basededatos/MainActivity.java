package com.example.app_basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_codigo, et_descripcion, et_precio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_codigo = findViewById(R.id.txt_codigo);
        et_descripcion = findViewById(R.id.txt_descripcion);
        et_precio = findViewById(R.id.txt_precio);
    }

    //registrar producto
    public void registrar(View view){
        //objeto de la clase AdminBD
        //contexto es this y el segundo parametro el nombre de la base de datos
        AdminBD admin = new AdminBD(this, "administracion", null, 1);

        //abrir base de datos en modo lectura y escritura
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        //validar campos
        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){


            //guardar en base de datos los valores que escribio el usuario
            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            //ahora hay que insertarlos en la tabla ARTICULOS
            //los valores para cada columna de la tabla estan guardados dentro de registro
            BaseDeDatos.insert("ARTICULOS", null, registro);

            BaseDeDatos.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this, "Registro de producto exitoso", Toast.LENGTH_SHORT).show();


        }else{

            Toast.makeText(this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();

        }
    }

    //para consultar producto
    public void consultar(View view){

        AdminBD admin = new AdminBD(this, "administracion", null, 1);

        //apertura de modo lectura y escritura de la base de datos
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //atrapar el valor con el cual vamos a hacer la busqueda
        String codigo = et_codigo.getText().toString();

        //validacion
        if(!codigo.isEmpty()){

            //Cursos ayuda al momento de querer seleccionar un producto a traves de su codigo
            Cursor fila = BaseDeDatos.rawQuery
                    ("select descripcion, precio from ARTICULOS" +
                            " where codigo ="+ codigo, null);

            //si la consulta trajo valores
            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "No existe el articulo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes ingresar codigo de articulo", Toast.LENGTH_SHORT).show();

        }
    }

    //para eliminar producto
    public void eliminar(View view){

        AdminBD admin = new AdminBD(this, "administracion", null, 1);

        //apertura de modo lectura y escritura de la base de datos
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //atrapar el valor con el cual vamos a hacer la busqueda
        String codigo = et_codigo.getText().toString();

        //validar
        if(!codigo.isEmpty()){

            int cantidadRegistrosBorrados = BaseDeDatos.delete("ARTICULOS", "codigo="+codigo, null);
            BaseDeDatos.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if(cantidadRegistrosBorrados == 1){

                Toast.makeText(this, "Articulo eliminado exitosamente", Toast.LENGTH_SHORT).show();


            }else{
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();

            }


        }else{
            Toast.makeText(this, "Debes ingresar codigo de articulo", Toast.LENGTH_SHORT).show();

        }
    }

    //para modificar
    public void modificar(View view){

        //objeto de la clase AdminBD
        AdminBD admin = new AdminBD(this, "administracion", null, 1);

        //apertura de modo lectura y escritura de la base de datos
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        //atrapar el valor con el cual vamos a hacer la busqueda
        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){


            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            //con esto updateo el registro
            int cantidadRegistrosModificados = BaseDeDatos.update("ARTICULOS", registro, "codigo ="+ codigo, null);

            BaseDeDatos.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if(cantidadRegistrosModificados == 1){

                Toast.makeText(this, "Articulo modificado exitosamente", Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(this, "El articulo no existe", Toast.LENGTH_SHORT).show();

            }

        }else{
            Toast.makeText(this, "Debes ingresar codigo de articulo", Toast.LENGTH_SHORT).show();

        }



    }
}
