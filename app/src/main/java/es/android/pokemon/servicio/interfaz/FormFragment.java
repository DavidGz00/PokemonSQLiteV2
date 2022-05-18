package es.android.pokemon.servicio.interfaz;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import es.android.pokemon.R;
import es.android.pokemon.databinding.FragmentFormBinding;
import es.android.pokemon.entidad.Pokemon;
import es.android.pokemon.repositorio.implementacion.RepositorioSQLiteImpl;


public class FormFragment extends Fragment {

    private EditText nombre;
    private EditText descripcion;
    private Button btn;

    //Metodo 2
    RepositorioSQLiteImpl pokemondb;

    private FragmentFormBinding binding;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(inflater, container, false);


        nombre=(EditText) binding.nombre;
        descripcion=(EditText) binding.descripcion;
        btn=(Button) binding.button;



        //Metodo 2
        pokemondb = new RepositorioSQLiteImpl((getContext()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                agregarPokemon();

            }
        });

        return binding.getRoot();
    }

    public void agregarPokemon() {

        //Metodo 2
        Pokemon pokemon = new Pokemon(6, nombre.getText().toString(), "",descripcion.getText().toString());
        pokemondb.add(pokemon);

        nombre.getText().clear();
        descripcion.getText().clear();
    }

}


