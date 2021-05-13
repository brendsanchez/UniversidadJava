package peliculas.domain;

public class Pelicula {
    private String nombre;

    public Pelicula(String name) {
        this.nombre = name;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
