package com.example.app_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityWeb extends AppCompatActivity {

    WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        wv1 = findViewById(R.id.webView);

        //atrapar lo que viene
        String URL = getIntent().getStringExtra("direccionWeb");

        //para que navegue dentro de la app, si no usa el chrome por fuera
        wv1.setWebViewClient(new WebViewClient());
        //metodo para disparar la accion, puede venir www.lalal.com o lalala.com
        wv1.loadUrl("http://" + URL);
    }

    public void cerrar(View view){

        finish(); // finaliza este Activity y vuelve al anterior
    }
}
