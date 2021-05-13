import peliculas.negocio.ICatalogoPeliculas;
import peliculas.negocio.CatalogoPeliculasImpl;

import java.util.Scanner;

/*
 * @Creator: Brenda Sánchez
 * TP del curso de Universidad Java, version propia
 * */

public class CPJLaboratorioFinal {
    public static void main(String[] args) {
        Scanner scaner = new Scanner(System.in);
        ICatalogoPeliculas catalogoPeliculas = new CatalogoPeliculasImpl();

        opcionVisualizar(scaner, catalogoPeliculas);
    }

    private static void opcionVisualizar(Scanner scanner, ICatalogoPeliculas catalogoPeliculas) {
        menuVisualizar();
        int opcion = Integer.parseInt(scanner.nextLine());
        switch (opcion) {
            case 0:
                System.out.println("\nSaliendo....");
                break;
            case 1:
                System.out.println("\nIniciando catalogo de peliculas");
                catalogoPeliculas.iniciarArchivo();
                opcionVisualizar(scanner, catalogoPeliculas);
                break;
            case 2:
                System.out.println("\nIntroduce el nombre de una pelicula a agregar");
                String nombrePeliculaAgregar = scanner.nextLine();
                catalogoPeliculas.agregarPelicula(nombrePeliculaAgregar);
                break;
            case 3:
                System.out.println("\nListado de peliculas:");
                catalogoPeliculas.listarPeliculas();
                break;
            case 4:
                System.out.println("\nIntroduce pelicula a buscar");
                String nombrePeliculaBuscar = scanner.nextLine();
                catalogoPeliculas.buscarPelicula(nombrePeliculaBuscar);
                break;
            default:
                System.out.println(String.format("\nOpcion: %s no es valida.... visualizando denuevo el menu:", opcion));
                opcionVisualizar(scanner, catalogoPeliculas);
        }
    }

    private static void menuVisualizar() {
        System.out.println("1.-Iniciar catalogo peliculas\n"
                + "2.-Agregar Pelicula\n"
                + "3.-Listar Peliculas\n"
                + "4.-Buscar Pelicula\n"
                + "0.-Salir \n"
        );
    }
}
