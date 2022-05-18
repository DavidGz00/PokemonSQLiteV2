package es.android.pokemon.entidad;

public class Pokemon {
    private int id;
    private String name;
    private String foto;
    private String descripcion;


    public Pokemon(int id, String name, String foto, String descripcion) {
        this.id = id;
        this.name= name;
        this.foto= foto;
        this.descripcion = descripcion;

    }
    public int getId(){ return id; }

    public String getName() {
        return name;
    }

    public String getFoto() {
        return foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}