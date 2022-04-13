package com.example.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.libreria.entidades.Libro;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listViewLibros;
    ArrayList<String> listaInformacion;
    ArrayList<Libro> listaLibros;
    DBAccess access;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        access = new DBAccess(this);

        loadUI();

        consultarLibros();

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaInformacion);
        listViewLibros.setAdapter(adaptador);

        //Al pulsar
        listViewLibros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String message = "";
                message += "Autor: " + listaLibros.get(position).getAutor() + "\n";
                message += "Editorial: " + listaLibros.get(position).getEditorial() + "\n";
                message += "Año de publicación: " + listaLibros.get(position).getAñopublicacion() + "\n";
                message += "Lugar de publicación: " + listaLibros.get(position).getLugarpublicacion() + "\n";
                message += "Número de páginas: " + listaLibros.get(position).getNpaginas() + "\n";

                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void consultarLibros(){
        SQLiteDatabase db = access.getReadableDatabase();
        Libro libro = null;

        listaLibros = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM libros", null);

        while(cursor.moveToNext()){
            libro = new Libro();
            libro.setIdlibro(cursor.getInt(0));
            libro.setTitulo(cursor.getString(1));
            libro.setAutor(cursor.getString(2));
            libro.setEditorial(cursor.getString(3));
            libro.setAñopublicacion(cursor.getInt(4));
            libro.setLugarpublicacion(cursor.getString(5));
            libro.setNpaginas(cursor.getInt(6));
            libro.setPrecio(cursor.getDouble(7));

            listaLibros.add(libro);
        }

        obtenerListLibros();
    }

    private void obtenerListLibros(){

        listaInformacion = new ArrayList<String>();

        for(int i = 0; i < listaLibros.size(); i++){
            listaInformacion.add(listaLibros.get(i).getIdlibro() + " - " +  listaLibros.get(i).getTitulo() + " - " + listaLibros.get(i).getPrecio());
        }
    }

    private void loadUI(){
        listViewLibros = findViewById(R.id.lvLibros);
    }
}