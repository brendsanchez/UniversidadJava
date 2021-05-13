package peliculas.negocio;

public interface ICatalogoPeliculas {
    String UBICACION_ARCHIVO = "src/peliculas/peliculas.txt";

    void agregarPelicula( String nombrePelicula);

    void listarPeliculas();

    void buscarPelicula(String buscar);

    void iniciarArchivo();
}
