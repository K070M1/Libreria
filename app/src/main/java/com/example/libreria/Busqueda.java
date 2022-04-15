package com.example.libreria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Busqueda extends AppCompatActivity {

    EditText etIDBuscado,etTitulo, etAutor, etEditorial, etAñopubli, etLugarpubli, etNpaginas, etPrecio;
    Button botonBuscarID, botonEliminar, botonActualizar;
    Context context = this;
    DBAccess access;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        access = new DBAccess(context);
        loadUI();

        botonBuscarID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchData();
            }
        });

        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarEliminar();
            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificarCasillas();
            }
        });

    }

    //Verificar si las casillas estan vacias
    private void verificarCasillas(){
        //Cambio de valores previos
        String IDB = etIDBuscado.getText().toString();
        String añopubli = etAñopubli.getText().toString();
        String paginas = etNpaginas.getText().toString();
        String precio = etPrecio.getText().toString();
        String titulo = etTitulo.getText().toString();
        String autor = etAutor.getText().toString();
        String editorial = etEditorial.getText().toString();
        String lugarpublicacion = etLugarpubli.getText().toString();

        if(IDB.isEmpty() || titulo.trim().isEmpty() || autor.trim().isEmpty() || editorial.trim().isEmpty() || añopubli.isEmpty() || lugarpublicacion.trim().isEmpty() || paginas.isEmpty() || precio.isEmpty()){
            Toast.makeText(context, "Faltan completar algunas casillas", Toast.LENGTH_SHORT).show();
        }else{
            verificarActualizar();
        }
    }

    //Metodo para comprobar si quiere modificar
    private void verificarActualizar(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);

        dialogo.setTitle("Libreria SENATI")
                .setMessage("¿Estas seguro de modificar este registro?")
                .setCancelable(false)
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        actualizarDatos();
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

    //Metodo para modificar el registro
    private void actualizarDatos(){
        SQLiteDatabase db = access.getWritableDatabase();
        String[] param = { etIDBuscado.getText().toString() };

        ContentValues values = new ContentValues();
        values.put("titulo", etTitulo.getText().toString());
        values.put("autor", etAutor.getText().toString());
        values.put("editorial", etEditorial.getText().toString());
        values.put("añopublicacion", etAñopubli.getText().toString());
        values.put("lugarpublicacion", etLugarpubli.getText().toString());
        values.put("npaginas", etNpaginas.getText().toString());
        values.put("precio", etPrecio.getText().toString());

        int i  = db.update("libros", values, "idlibro=?", param);

        if(i > 0){
            Toast.makeText(context, "Actualizado Correctamente", Toast.LENGTH_LONG).show();
        }
        db.close();
    }

    //Metodo para comprobar si se quiere eliminar
    private void verificarEliminar(){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(context);

        String IDB =  etIDBuscado.getText().toString();

        if(IDB.isEmpty()){
            Toast.makeText(context, "No hay un registro asignado para eliminar", Toast.LENGTH_LONG).show();
        }else{
            dialogo.setTitle("Libreria SENATI")
                    .setMessage("¿Estas seguro de eliminar este registro?")
                    .setCancelable(false)
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            eliminarRegistro();
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
    }

    //Metodo para eliminar el registro
    private void eliminarRegistro(){
        SQLiteDatabase db = access.getWritableDatabase();
        String[] param = { etIDBuscado.getText().toString() };

        int i = db.delete("libros", "idlibro=?", param);

        if(i > 0){
            resetUI();
            Toast.makeText(context, "Eliminado correctamente", Toast.LENGTH_LONG).show();
        }

        db.close();
    }

    //Metodo para buscar por ID un registro
    private void searchData(){

        SQLiteDatabase db = access.getReadableDatabase();

        String IdBuscado = etIDBuscado.getText().toString();

        String[] paramConsult = { IdBuscado };

        String[] campObtn = {"titulo", "autor" , "editorial", "añopublicacion", "lugarpublicacion", "npaginas", "precio"};

        if(IdBuscado.isEmpty()){
            Toast.makeText(context, "Debe colocar un valor a buscar", Toast.LENGTH_LONG).show();
            resetUI();
        }else{
            try {
                Cursor cursor = db.query("libros", campObtn, "idlibro=?", paramConsult, null,null,null);
                if(cursor.moveToFirst()){
                    etTitulo.setText(cursor.getString(0));
                    etAutor.setText(cursor.getString(1));
                    etEditorial.setText(cursor.getString(2));
                    etAñopubli.setText(cursor.getString(3));
                    etLugarpubli.setText(cursor.getString(4));
                    etNpaginas.setText(cursor.getString(5));
                    etPrecio.setText(cursor.getString(6));
                }else{
                    Toast.makeText(context,"No se encontró el registro buscado", Toast.LENGTH_LONG).show();
                    resetUI();
                }
                cursor.close();
            }
            catch (Exception error){
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }



    }

    //Metodo para resetar casillas
    private void resetUI(){
        etIDBuscado.setText(null);
        etTitulo.setText(null);
        etAutor.setText(null);
        etEditorial.setText(null);
        etAñopubli.setText(null);
        etLugarpubli.setText(null);
        etNpaginas.setText(null);
        etPrecio.setText(null);
    }

    //Método para cargar elementos
    private void loadUI(){
        etIDBuscado = findViewById(R.id.etIDBuscadob);
        etTitulo = findViewById(R.id.etTitulob);
        etAutor = findViewById(R.id.etAutorb);
        etEditorial = findViewById(R.id.etEditorialb);
        etAñopubli = findViewById(R.id.etAPublib);
        etLugarpubli = findViewById(R.id.etLPublib);
        etNpaginas = findViewById(R.id.etNPaginasb);
        etPrecio = findViewById(R.id.etPreciob);

        botonBuscarID = findViewById(R.id.btBuscar);
        botonActualizar = findViewById(R.id.btActualizar);
        botonEliminar = findViewById(R.id.btEliminar);
    }
}