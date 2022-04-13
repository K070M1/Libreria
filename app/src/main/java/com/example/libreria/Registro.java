package com.example.libreria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText etTitulo, etAutor, etEditorial, etAñopubli, etLugarpubli, etNpaginas, etPrecio;
    Button botonRegistrarLibro;
    Context context = this;
    DBAccess accesso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        loadUI();

        accesso = new DBAccess(context);

        botonRegistrarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarRegister();
            }
        });
    }

    //Verificar si quiere registrar
    private void verificarRegister(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);

        dialogo.setTitle("Libreria SENATI")
                .setMessage("¿Estas seguro de registrar este libro?")
                .setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        registerData();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Operación cancelada", Toast.LENGTH_SHORT).show();
                    }
                });
        dialogo.show();
    }

    //Registrar nuevo libro
    private void registerData(){
        String  titulo = etTitulo.getText().toString();
        String  autor = etAutor.getText().toString();
        String  editorial = etEditorial.getText().toString();
        int    añopublicacion = Integer.parseInt(etAñopubli.getText().toString());
        String lugarpublicacion = etLugarpubli.getText().toString();
        int    npaginas = Integer.parseInt(etNpaginas.getText().toString());
        double precio = Double.parseDouble(etPrecio.getText().toString());

        accesso.agregarLibro(titulo, autor, editorial, añopublicacion, lugarpublicacion, npaginas, precio);
        Toast.makeText(context, "Registrado correctamente", Toast.LENGTH_LONG).show();
        resetUI();
    }

    private void resetUI(){
        etTitulo.setText(null);
        etAutor.setText(null);
        etEditorial.setText(null);
        etAñopubli.setText(null);
        etLugarpubli.setText(null);
        etNpaginas.setText(null);
        etPrecio.setText(null);
    }

    private void loadUI(){
        etTitulo = findViewById(R.id.etTitulo);
        etAutor = findViewById(R.id.etAutor);
        etEditorial = findViewById(R.id.etEditorial);
        etAñopubli = findViewById(R.id.etAPubli);
        etLugarpubli = findViewById(R.id.etLPubli);
        etNpaginas = findViewById(R.id.etNPaginas);
        etPrecio = findViewById(R.id.etPrecio);
        botonRegistrarLibro = findViewById(R.id.btRegistrarLibro);
    }
}