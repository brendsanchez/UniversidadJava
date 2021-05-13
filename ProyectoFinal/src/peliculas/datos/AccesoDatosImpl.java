package peliculas.datos;

import peliculas.domain.Pelicula;
import peliculas.excepciones.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosImpl implements IAccesoDatos {
    @Override
    public Boolean existe(String nombreArchivo){
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }

    @Override
    public List<Pelicula> listar(String nombreArchivo) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        List<Pelicula> peliculasList = new ArrayList<>();
        Pelicula pelicula;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea = reader.readLine();
            while (linea != null) {
                String lineNombrePelicula = linea.split("'")[1];
                pelicula = new Pelicula(lineNombrePelicula);
                peliculasList.add(pelicula);
                linea = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new LecturaDatosEx("Excepcion listar peliculas" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion listar peliculas" + e.getMessage());
        }

        return peliculasList;
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        String pelicula = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea = reader.readLine();
            while (linea != null) {
                String lineNombrePelicula = linea.split("'")[1];
                if (!buscar.isEmpty() && lineNombrePelicula.equalsIgnoreCase(buscar)) {
                    pelicula = lineNombrePelicula;
                    break;
                }
                linea = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al buscar pelicula " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepcion al buscar pelicula " + e.getMessage());
        }
        return (!pelicula.isEmpty()) ? pelicula : "";
    }

    @Override
    public void escribir(String nombreArchivo, Pelicula pelicula, boolean anexar) throws EscrituraDatosEx {
        File file = new File(nombreArchivo);
        try {
            FileWriter writer = new FileWriter(file, anexar);
            PrintWriter escribir = new PrintWriter(writer);

            escribir.println(pelicula);
            escribir.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new EscrituraDatosEx("Excepcion al escribir peliculas " + e.getMessage());
        }
    }

    @Override
    public void crear(String nombreArchivo) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            salida.close();
            System.out.println("Archivo creado con existo:".concat(nombreArchivo));
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
            throw new EscrituraDatosEx("Excepcion al crear archivo " + e.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        archivo.delete();
    }
}
