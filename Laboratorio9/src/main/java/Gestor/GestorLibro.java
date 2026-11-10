package Gestor;

import Clases.Libro;
import Controlador.ControladorLibro;
import herramientas.Mensaje;
import herramientas.Pausa;
import java.util.Scanner;
import herramientas.GestorMenu;
import java.time.LocalDate;

public class GestorLibro implements GestorMenu {

    private ControladorLibro gestor = new ControladorLibro();
    private Scanner teclado = new Scanner(System.in);

    @Override
    public void ejecutarMenu() {
        int menu = -1;

        do {
            try {
                System.out.print(
                        "\n=== Libro Menú ===\n"
                        + "1. Agregar libro\n"
                        + "2. Listar libros\n"
                        + "3. Buscar libro\n"
                        + "4. Modificar libro\n"
                        + "5. Eliminar libro\n"
                        + "0. Salir\n"
                        + "Opcion: ");

                menu = Integer.parseInt(teclado.nextLine());

                switch (menu) {
                    case 1 ->
                        pedirYAgregarLibro();
                    case 2 ->
                        gestor.listarLibros();
                    case 3 ->
                        pedirYBuscarLibro();
                    case 4 ->
                        pedirYModificarLibro();
                    case 5 ->
                        pedirYEliminarLibro();
                    case 0 ->
                        System.out.println("<--\n\n");
                    default ->
                        Mensaje.opcionInvalida();
                }
            } catch (NumberFormatException e) {
                Mensaje.tipoDatoIncorrecto();
            }

            if (menu != 0) {
                Pausa.pausar();
            }
        } while (menu != 0);
    }

    private Libro crearLibro() {
        Libro libroCreado = null;

        System.out.println("Ingrese ISBN: ");
        String isbn = teclado.nextLine();

        System.out.println("Ingrese titulo: ");
        String titulo = teclado.nextLine();

        System.out.println("Ingrese autor: ");
        String autor = teclado.nextLine();

        System.out.println("Ingrese editorial: ");
        String editorial = teclado.nextLine();

        if (titulo.isBlank() || autor.isBlank() || editorial.isBlank()) {
            System.out.println("No se admiten datos varíos");
        } else {

            System.out.println("Ingrese anio de publicacion: ");
            int anio = Integer.parseInt(teclado.nextLine());

            if (anio <= 0 || anio > LocalDate.now().getYear()) {
                System.out.println("Error: Año de publicación inválido.");
            } else {
                libroCreado = new Libro(isbn, titulo, autor, editorial, anio);
            }

        }
        return libroCreado;
    }

    private void pedirYAgregarLibro() {
        Libro libroNuevo = crearLibro();
        gestor.agregarLibro(libroNuevo);
    }

    private void pedirYBuscarLibro() {
        System.out.println("Ingrese el ISBN a buscar: ");
        String isbn = teclado.nextLine();
        Libro mostrar = gestor.buscarLibro(isbn);

        if (mostrar != null) {
            System.out.println("\n--- Libro Encontrado ---");
            System.out.println("ISBN: " + mostrar.getIsbn());
            System.out.println("Titulo: " + mostrar.getTitulo());
            System.out.println("Autor: " + mostrar.getAutor());
            System.out.println("Editorial: " + mostrar.getEditorial());
            System.out.println("Anio de publicacion: " + mostrar.getFechaPublicacion());
            System.out.println("--------------------------\n");
        } else {
            System.out.println("No se encontró un libro con ese ISBN.");
        }
    }

    private void pedirYModificarLibro() {
        Libro modificar = crearLibro();
        gestor.modificarLibro(modificar);
    }

    private void pedirYEliminarLibro() {
        System.out.println("Ingrese el ISBN a eliminar: ");
        String isbn = teclado.nextLine();
        
        Libro eliminar = null;
        eliminar = gestor.buscarLibro(isbn);

        if (eliminar != null) {
            System.out.println("Estas seguro de que queres borrar: " + eliminar.getTitulo()
                    + "\n[ 0: No | 1: Si ]: ");

        int seleccion = Integer.parseInt(teclado.nextLine());
        switch (seleccion) {
            case 0 ->
                System.out.println("Libro " + eliminar.getTitulo() + " no eliminado.");
            case 1 ->
                gestor.eliminarLibro(eliminar);
            default ->
                System.out.println("Error: Estado inválido.");
        }
        }

    }

}
