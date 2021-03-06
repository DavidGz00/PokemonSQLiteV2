package es.android.pokemon.servicio.implementacion;

import android.content.Context;

import java.util.List;

import es.android.pokemon.entidad.Pokemon;
import es.android.pokemon.repositorio.implementacion.RepositorioSQLiteImpl;
import es.android.pokemon.repositorio.interfaz.Repositorio;
import es.android.pokemon.servicio.interfaz.GenerarPokemon;

public class GenerarPokemonSQLite implements GenerarPokemon {

  private Repositorio<Pokemon> repositorio;
  private List<Pokemon> todosLosPokemon;
  public GenerarPokemonSQLite(Context context){
      repositorio = new RepositorioSQLiteImpl(context);
  }


    @Override
    public List<Pokemon> generarPokemon(String recurso) throws Exception {
        todosLosPokemon = repositorio.getAll();
        return todosLosPokemon;
    }
}
