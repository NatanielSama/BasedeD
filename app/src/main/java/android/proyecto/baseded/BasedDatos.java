package android.proyecto.baseded;

/**
 * Created by Nataniel-Sama on 14/04/2015.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
Utilice una nueva clase para crear la base de datos que usa la aplicación
para ello agrege a la clase la extención SQLiteOpenHelper
*/
public class BasedDatos extends SQLiteOpenHelper{
    public BasedDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /*
    El siguiente codigo sirve para crear la tabla de la base de datos
    */
    @Override
    public void onCreate(SQLiteDatabase base) {
        base.execSQL("create table libros (codigo integer primary key unique, nombre text unique, autor text, editorial text, precio float) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase base, int oldVersion, int newVersion) {
        base.execSQL("drop table if exists libros");
        base.execSQL("create table  libros (codigo integer primary key unique, nombre text unique, autor text, editorial text, precio float)");
    }

}
