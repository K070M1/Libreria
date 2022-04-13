package com.example.libreria;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBAccess extends SQLiteOpenHelper {

    //Constantes
    private static final String NOMBRE_BD = "LIBRERIA";
    private static final int VERSION_BD = 1;
    private static final String TABLA_LIBROS = "CREATE TABLE libros (idlibro INTEGER NOT NULL, titulo TEXT NOT NULL, autor TEXT NOT NULL, editorial TEXT NOT NULL, añopublicacion INTEGER NOT NULL, lugarpublicacion TEXT NOT NULL, npaginas NUMERIC NOT NULL, precio REAL NOT NULL, PRIMARY KEY(idlibro AUTOINCREMENT))";

    //Constructor
    public DBAccess(@Nullable Context context){
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_LIBROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS libros");
        db.execSQL(TABLA_LIBROS);
    }

    //Método para insertar datos
    public void agregarLibro(String titulo, String autor, String editorial, int añopublicacion, String lugarpublicacion, int npaginas, double precio){

        SQLiteDatabase db =getWritableDatabase();

        if(db != null){

            ContentValues parametros = new ContentValues();

            parametros.put("titulo", titulo);
            parametros.put("autor", autor);
            parametros.put("editorial", editorial);
            parametros.put("añopublicacion", añopublicacion);
            parametros.put("lugarpublicacion", lugarpublicacion);
            parametros.put("npaginas", npaginas);
            parametros.put("precio", precio);

            long idlibro = db.insert("libros", "idcurso", parametros);

            Log.i("info", "ID Libro generado:" + String.valueOf(idlibro));

            db.close();
        }
    }
}
