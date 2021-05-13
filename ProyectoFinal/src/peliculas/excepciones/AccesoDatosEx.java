package peliculas.excepciones;

public abstract class AccesoDatosEx extends Exception {
    public AccesoDatosEx(String mensaje) {
        super(mensaje);
    }
}
