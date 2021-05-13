package peliculas.negocio;

import peliculas.StringUtils;
import peliculas.datos.*;
import peliculas.domain.Pelicula;
import peliculas.excepciones.*;

import java.util.List;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {
    IAccesoDatos accesoDatos;

    public CatalogoPeliculasImpl() {
        this.accesoDatos = new AccesoDatosImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula) {
        Pelicula pelicula;
        if (!nombrePelicula.isEmpty()) {
            pelicula = new Pelicula(StringUtils.capitalize(nombrePelicula));
            try {
                accesoDatos.escribir(UBICACION_ARCHIVO, pelicula, true);
                System.out.println(String.format("Pelicula: '%s' agregada con exito.", nombrePelicula));
            } catch (EscrituraDatosEx ex) {
                System.out.println("Error acceso datos");
                ex.printStackTrace(System.out);
            }
        } else {
            System.out.println("Tiene que ingresar un 'Nombre de Pelicula'");
        }
    }

    @Override
    public void listarPeliculas() {
        List<Pelicula> peliculas;

        try {
            peliculas = accesoDatos.listar(UBICACION_ARCHIVO);
            if (!peliculas.isEmpty()) {
                peliculas.stream().map(Pelicula::getNombre).forEach(System.out::println);
            } else {
                System.out.println("No hay peliculas aun");
            }
        } catch (LecturaDatosEx ex) {
            System.out.println("Error al listar peliculas");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        Pelicula peliculaEncontrada;
        String peliculaBuscar = StringUtils.capitalize(buscar);
        String resultado;
        try {
            resultado = accesoDatos.buscar(UBICACION_ARCHIVO, peliculaBuscar);

            if (!resultado.isEmpty()) {
                peliculaEncontrada = new Pelicula(peliculaBuscar);
                System.out.println("Encontrado: ".concat(peliculaEncontrada.getNombre()));
                System.out.println(peliculaEncontrada);
            } else {
                System.out.println("No se encontro la Pelicula: ".concat(buscar));
            }
        } catch (LecturaDatosEx ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void iniciarArchivo() {
        try {
            if (!accesoDatos.existe(UBICACION_ARCHIVO)) {
                accesoDatos.crear(UBICACION_ARCHIVO);
            } else {
                accesoDatos.borrar(UBICACION_ARCHIVO);
                System.out.println("*Restableciendo Archivo*");
                accesoDatos.crear(UBICACION_ARCHIVO);
            }
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace(System.out);
        }
    }
}
