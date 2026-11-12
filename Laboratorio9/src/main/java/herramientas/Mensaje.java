package herramientas;

public enum Mensaje {
    NO_ENCONTRADO("\n%s no encontrado.\n"),
    OPCION_INVALIDA("\nEl número ingresado es inválido\n"),
    TIPO_DATO_INCORRECTO("\nError: Tipo de dato incorrecto.\n"),
    CAMPOS_VACIOS("\nError: Los campos no pueden estar vacíos.\n"),
    FECHA_INVALIDA("\nError: Formato de fecha inválido.\n"),
    SALIR("<--\n\n");

    private final String texto;

    Mensaje(String texto) {
        this.texto = texto;
    }

    public void mostrar() {
        System.out.println(this.texto);
    }
    
    public void mostrar(String entidad) {
        System.out.printf(this.texto, entidad);
    }
}