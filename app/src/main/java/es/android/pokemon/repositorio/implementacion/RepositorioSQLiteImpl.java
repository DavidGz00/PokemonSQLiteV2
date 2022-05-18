package es.android.pokemon.repositorio.implementacion;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import es.android.pokemon.entidad.Pokemon;
import es.android.pokemon.repositorio.interfaz.Repositorio;

public class RepositorioSQLiteImpl extends SQLiteOpenHelper implements Repositorio<Pokemon> {
    public RepositorioSQLiteImpl(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pokemon.db";

    public RepositorioSQLiteImpl(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    static class ContratoPokemon{
        private ContratoPokemon() {}
        public static class EntradaPokemon implements BaseColumns {
            public static final String NOMBRE_TABLA = "Pokemon";
            public static final String ID = "_id";
            public static final String NOMBRE = "nombre";
            public static final String FOTO = "foto";
            public static final String DESCRIPCION = "descripcion";
        }
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ContratoPokemon.EntradaPokemon.NOMBRE_TABLA + " (" +
                ContratoPokemon.EntradaPokemon.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                ContratoPokemon.EntradaPokemon.NOMBRE + " TEXT NOT NULL," +
                ContratoPokemon.EntradaPokemon.FOTO + " TEXT NOT NULL," +
                ContratoPokemon.EntradaPokemon.DESCRIPCION + " TEXT NOT NULL)");

        Pokemon p1 = new Pokemon(0, "Garchomp", "garchomp.jpg", "Es Garchomp!");
        this.save(p1, db);
        Pokemon p2 = new Pokemon(1, "Dragapult", "dragapult.jpg", "Es Dragapult!");
        this.save(p2, db);
        Pokemon p3 = new Pokemon(2, "Yveltal", "yveltal.jpg", "Es Yveltal!");
        this.save(p3, db);
        Pokemon p4 = new Pokemon(3, "Mewtwo", "mewtwo.jpg", "Es Mewtwo!");
        this.save(p4, db);
        Pokemon p5 = new Pokemon(4, "Gengar", "gengar.jpg", "Es Gengar!");
        this.save(p5, db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public Optional<Pokemon> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Pokemon> getAll() {

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                ContratoPokemon.EntradaPokemon.NOMBRE_TABLA, // Nombre de la tabla
                null, // Lista de Columnas a consultar
                null, // Columnas para la cláusula WHERE
                null, // Valores a comparar con las columnas del WHERE
                null, // Agrupar con GROUP BY
                null, // Condición HAVING para GROUP BY
                null // Cláusula ORDER BY
        );

        List<Pokemon> pokemons = new LinkedList<>();
        while(c.moveToNext()){
            @SuppressLint("Range")
            int id = c.getInt(
                    c.getColumnIndex(ContratoPokemon.EntradaPokemon.ID));
            @SuppressLint("Range")
            String name = c.getString(
                    c.getColumnIndex(ContratoPokemon.EntradaPokemon.NOMBRE));
            @SuppressLint("Range")
            String foto = c.getString(
                    c.getColumnIndex(ContratoPokemon.EntradaPokemon.FOTO));
            @SuppressLint("Range")
            String descripcion = c.getString(
                    c.getColumnIndex(ContratoPokemon.EntradaPokemon.DESCRIPCION));
            pokemons.add(new Pokemon(id, name,foto, descripcion));
        }

        return pokemons;
    }

    @Override
    public void save(Pokemon pokemon) {
        this.save(pokemon, null);
    }

    private void save(Pokemon pokemon, SQLiteDatabase db) {
        if(db == null)
            db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContratoPokemon.EntradaPokemon.ID, pokemon.getId());
        values.put(ContratoPokemon.EntradaPokemon.NOMBRE, pokemon.getName());
        values.put(ContratoPokemon.EntradaPokemon.FOTO, pokemon.getFoto());
        values.put(ContratoPokemon.EntradaPokemon.DESCRIPCION, pokemon.getDescripcion());
        db.insert(ContratoPokemon.EntradaPokemon.NOMBRE_TABLA, null, values);
    }

    @Override
    public void update(Pokemon pokemon) {
// Obtenemos la BBDD para escritura
        SQLiteDatabase db = getWritableDatabase();
        // Contenedor de valores
        ContentValues values = new ContentValues();
        // Pares clave-valor
        values.put(ContratoPokemon.EntradaPokemon.ID, pokemon.getId());
        values.put(ContratoPokemon.EntradaPokemon.NOMBRE, pokemon.getName());
        values.put(ContratoPokemon.EntradaPokemon.FOTO, pokemon.getFoto());
        values.put(ContratoPokemon.EntradaPokemon.DESCRIPCION, pokemon.getDescripcion());
        // Actualizar...
        db.update(ContratoPokemon.EntradaPokemon.NOMBRE_TABLA,
                values,
                "name=?",
                new String[] {pokemon.getName()});
    }

    @Override
    public void delete(Pokemon pokemon) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(ContratoPokemon.EntradaPokemon.NOMBRE_TABLA,
                "name=?",
                new String[] {pokemon.getName()});
    }

    //Metodo 1
    /*public void agregarPokemon(int id, String nombre, String foto,String descripcion) {
        SQLiteDatabase db = getWritableDatabase();

        if(db!=null){
            db.execSQL("INSERT INTO Pokemon VALUES('"+id+"','"+nombre+"','"+descripcion+"'");
            db.close();
        }
    }*/

    //Metodo 1 (otra manera dentro de agregarPokemon)
        /*Pokemon pokemon = new Pokemon(5, nombre.toString(), "",descripcion.toString());
        this.save(pokemon, db);*/


    public void add(Pokemon pokemon){
        SQLiteDatabase db = getWritableDatabase();
        this.save(pokemon, db);
    }
}
