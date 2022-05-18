package es.android.pokemon.servicio.interfaz;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.android.pokemon.R;
import es.android.pokemon.entidad.Pokemon;
import es.android.pokemon.repositorio.implementacion.RepositorioSQLiteImpl;

public class AgregarFragment extends AppCompatActivity {

    private EditText nombre;
    private EditText descripcion;
    private Button btn;

    //Metodo 2
    RepositorioSQLiteImpl pokemondb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_form);

        nombre=(EditText)findViewById(R.id.nombre);
        descripcion=(EditText)findViewById(R.id.descripcion);
        btn=(Button)findViewById(R.id.button);

        //Metodo 1
        /*final RepositorioSQLiteImpl pokemondb = new RepositorioSQLiteImpl((getApplicationContext()));*/

        //Metodo 2
        pokemondb = new RepositorioSQLiteImpl((getApplicationContext()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Metodo 1
                /*pokemondb.agregarPokemon(5, nombre.getText().toString(),"", descripcion.getText().toString());

                nombre.getText().clear();
                descripcion.getText().clear();*/

                //Metodo 2
                agregarPokemon();


            }
        });
    }

    public void agregarPokemon() {

        //Metodo 2
        Pokemon pokemon = new Pokemon(5, nombre.toString(), "",descripcion.toString());
        pokemondb.add(pokemon);

        nombre.getText().clear();
        descripcion.getText().clear();
    }
}


