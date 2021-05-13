package peliculas.datos;

import peliculas.domain.Pelicula;
import peliculas.excepciones.*;

import java.util.List;

public interface IAccesoDatos {
    Boolean existe(String nombreArchivo) throws AccesoDatosEx;

    String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx;

    List<Pelicula> listar(String nombre) throws LecturaDatosEx;

    void escribir(String nombreArchivo, Pelicula pelicula, boolean anexar) throws EscrituraDatosEx;

    void crear(String nombreArchivo) throws EscrituraDatosEx;

    void borrar(String nombreArchivo) throws AccesoDatosEx;
}
