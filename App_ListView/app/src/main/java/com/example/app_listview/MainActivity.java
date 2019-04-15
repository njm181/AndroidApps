package com.example.app_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private ListView lv1;

    private String nombres[] = {"Nicolás","Javier","Goku", "Gohan","Goten","Vegeta"};
    private String edades[] = {"23","22","30","20","15","35"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        lv1 = (ListView)findViewById(R.id.lv1);

        //ArrayAdapter  para conectar la vista con los datos del Array nombres
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_personalizado, nombres);
        lv1.setAdapter(adapter);

        /*asignar la funcion que cada vez que se presione sobre un nombre en ListView muestre el
        mensaje que pertenece a ese nombre*/

        /*Este setOnItemClickListener debe resicibir una clase anonima(sin nombre)
        * Android presta una clase anonima por defecto*/
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //esto asocia el nombre con la edad en orden, posicion 0 nombre con posicion 0 en edad
                tv1.setText("La edad de "+lv1.getItemAtPosition(position)+" es "+ edades[position]+" años");
            }
        });
    }
}
