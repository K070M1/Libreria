package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonRegister, botonSearch, botonList;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadUI();

        //Abrir el activity Registro
        botonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Registro.class);
                startActivity(intent);
            }
        });

        //Abrir el activity Busqueda
        botonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Busqueda.class);
                startActivity(intent);
            }
        });

        //Abrir el activity Listado
        botonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Listado.class);
                startActivity(intent);
            }
        });



    }

    private void loadUI(){
        botonRegister = findViewById(R.id.btActivityRegistro);
        botonSearch = findViewById(R.id.btActivityBusqueda);
        botonList = findViewById(R.id.btActivityListado);
    }
}