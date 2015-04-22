package android.proyecto.baseded;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;


public class MainActivity extends ActionBarActivity {
    /*
    Declaramos las variables de tipo Editext
    */
    EditText codigo,nombre,autor,editorial,precio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    /*
    Creamos la relacion entre el codigo y los elementos en vista
     */
        codigo = (EditText) findViewById(R.id.code);
        nombre = (EditText) findViewById(R.id.nombre);
        autor = (EditText) findViewById(R.id.autor);
        editorial = (EditText) findViewById(R.id.edit);
        precio = (EditText) findViewById(R.id.price);
    }
    /*
    El siguiente codigo tiene la funcion de ingresar nuevos datos en la base de datos
    */
    public void agregar (View v) {
        try {
            /*
            Con la siguiente condición la aplicacion comparara el tamaño del valor dentro de los edittext para comprobar que no estan vacios y si no es el caso entonces
            proseguir con la operacion
             */
            if (codigo.getText().length()==0 || nombre.getText().length()==0  || editorial.getText().length()==0  || autor.getText().length()==0 || precio.getText().length()==0 ) {


                    Toast.makeText(this, R.string.camposv, Toast.LENGTH_SHORT).show();
                }
               else
                {
                BasedDatos base = new BasedDatos(this, "libros", null, 1);
                SQLiteDatabase datos = base.getWritableDatabase();
              /*
            declaracion de variables que se usaran para ingresar los datos a la base de datos
             */
                String vcodigo = codigo.getText().toString();
                String vnombre = nombre.getText().toString();
                String vautor = autor.getText().toString();
                String veditorial = editorial.getText().toString();
                String vprecio = precio.getText().toString();

                ContentValues registrar = new ContentValues();
                registrar.put("codigo", vcodigo);
                registrar.put("nombre", vnombre);
                registrar.put("autor", vautor);
                registrar.put("editorial", veditorial);
                registrar.put("precio", vprecio);
                 /*
                se ejecuta la insercion de los datos a la base de datos
             */
                datos.insert("libros", null, registrar);
                datos.close();

                codigo.setText("");
                nombre.setText("");
                autor.setText("");
                editorial.setText("");
                precio.setText("");

                Toast.makeText(this, R.string.libron, Toast.LENGTH_SHORT).show();
            }

}
        catch (Exception ex)
        {
            Toast.makeText(this, "Error: "+ ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*
    El siguiente codigo tiene la funcion de consultar los datos en la base de datos
    */

    public void consultar(View v) {
        /*
    Agregue un try para capturar el error que mandaba la aplicación cuando se quiere ejecutar la operación de consultar y el edittext del codigo(del libro)
    esta vacio
    */
        try {
            if (codigo.getText().length()==0 ) {

                Toast.makeText(this, R.string.ingreco, Toast.LENGTH_SHORT).show();
            }
            else {
                BasedDatos base = new BasedDatos(this, "libros", null, 1);
                SQLiteDatabase datos = base.getWritableDatabase();
                String vcodigo = codigo.getText().toString();
         /*
            los datos de la consulta se guardan en un array y se muestrar en los edittext
             */
                Cursor cdatos = datos.rawQuery("select nombre, autor, editorial, precio from libros where codigo=" + vcodigo, null);
                if (cdatos.moveToFirst()) {
                    nombre.setText(cdatos.getString(0));
                    autor.setText(cdatos.getString(1));
                    editorial.setText(cdatos.getString(2));
                    precio.setText(cdatos.getString(3));

                    Toast.makeText(this, R.string.libroen, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.librono, Toast.LENGTH_SHORT).show();
                }
                datos.close();
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Error: "+ ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /*
    El siguiente codigo tiene la funcion de eliminar los datos en la base de datos
    */
    public void eliminar(View v) {

          /*
    Agregue un try y una condicion para capturar el error que mandaba la aplicación cuando se quiere ejecutar la operación de eliminar y el edittext del codigo(del libro)
    esta vacio
    */
        try {
            if (codigo.getText().length() == 0) {


                Toast.makeText(this, R.string.ingreco, Toast.LENGTH_SHORT).show();
            } else {
                BasedDatos base = new BasedDatos(this, "libros", null, 1);
                SQLiteDatabase datos = base.getWritableDatabase();
                String vcodigo = codigo.getText().toString();
        /*
            se ejecuta la operacion eliminar
             */
                int num = datos.delete("libros", "codigo=" + vcodigo, null);
                datos.close();

                codigo.setText("");
                nombre.setText("");
                autor.setText("");
                editorial.setText("");
                precio.setText("");

                if (num == 1) {
                    Toast.makeText(this, R.string.borro, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.nolibro, Toast.LENGTH_SHORT).show();
                }
            }
            }
            catch(Exception ex)
            {
                Toast.makeText(this, "Error: "+ ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
    }
    /*
        El siguiente codigo tiene la funcion de modificar los datos en la base de datos
        */
    public void modificar (View v) {
        try{
            if (codigo.getText().length()==0 || nombre.getText().length()==0  || editorial.getText().length()==0  || autor.getText().length()==0 || precio.getText().length()==0 ) {


                Toast.makeText(this, R.string.camposv, Toast.LENGTH_SHORT).show();
            }
            else {
             /*
    Agregue un try para capturar el error que mandaba la aplicación cuando se quiere ejecutar la operación de modificar y el edittext del codigo(del libro)
    esta vacio
    */
                BasedDatos base = new BasedDatos(this, "libros", null, 1);
                SQLiteDatabase datos = base.getWritableDatabase();
         /*
            declaracion de variables que se usaran para ingresar los datos que se modificaran de la base de datos
             */
                String vcodigo = codigo.getText().toString();
                String vnombre = nombre.getText().toString();
                String vautor = autor.getText().toString();
                String veditorial = editorial.getText().toString();
                String vprecio = precio.getText().toString();


                ContentValues registrar = new ContentValues();

                registrar.put("codigo", vcodigo);
                registrar.put("nombre", vnombre);
                registrar.put("autor", vautor);
                registrar.put("editorial", veditorial);
                registrar.put("precio", vprecio);
             /*
            se ejecuta la modificacion de los datos
             */
                int num = datos.update("libros", registrar, "codigo=" + vcodigo, null);
                datos.close();

                if (num == 1) {
                    Toast.makeText(this, R.string.modificado, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, R.string.nolibro, Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this, "Error: "+ ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    /*
    El siguiente codigo tiene la funcion de limpiar los datos en la base de datos
    */

    public void limpiar (View v){
        codigo.setText("");
        nombre.setText("");
        autor.setText("");
        editorial.setText("");
        precio.setText("");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
