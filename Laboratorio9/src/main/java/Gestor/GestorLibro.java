package Gestor;

import Clases.Libro;
import Controlador.ControladorLibro;
import herramientas.Mensaje;
import java.util.Scanner;
import herramientas.GestorMenu;
import herramientas.Pausa;
import java.time.LocalDate;
import java.util.List;

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
                        listrarLibros();
                    case 3 ->
                        pedirYBuscarLibro();
                    case 4 ->
                        pedirYModificarLibro();
                    case 5 ->
                        pedirYEliminarLibro();
                    case 0 ->
                        Mensaje.SALIR.mostrar();
                    default ->
                        Mensaje.OPCION_INVALIDA.mostrar();
                }
            } catch (NumberFormatException e) {
                Mensaje.TIPO_DATO_INCORRECTO.mostrar();
            }

            if (menu != 0) {
                Pausa.pausar(teclado);
            }
        } while (menu != 0);
    }

    // METODOS
    private void pedirYAgregarLibro() {
        Libro agregar = crearLibro();
        if (agregar != null) {
            gestor.cargarLibro(agregar);
        }
    }

    private void listrarLibros() {
        List<Libro> libros = gestor.listarLibros();

        if (libros == null) {
            Mensaje.NO_ENCONTRADO.mostrar("Libros");
        } else {
            System.out.printf("%-15s %-40s %-30s %-20s %-15s%n", "ISBN", "Titulo", "Autor", "Editorial", "Fecha Publicacion");
            System.out.println("------------------------------------------------------------------------------------------------------------------");

            for (Libro consulta : libros) {
                System.out.println(consulta);
            }
        }
    }

    private void pedirYBuscarLibro() {
        System.out.print("Ingrese el ISBN a buscar: ");
        String isbn = teclado.nextLine();

        Libro mostrar = gestor.buscarLibroPorISBN(isbn);
        if (mostrar != null) {
            System.out.println("\n------ Libro  Encontrado ------");
            System.out.println(mostrar);
            System.out.println("--------------------------------\n");
        } else {
            Mensaje.NO_ENCONTRADO.mostrar("Libro");
        }
    }

    private void pedirYModificarLibro() {

        System.out.print("Ingrese el ISBN del libro a modificar: ");
        String isbnModificar = teclado.nextLine();

        Libro aModificar = gestor.buscarLibroPorISBN(isbnModificar);

        if (aModificar == null) {
            Mensaje.NO_ENCONTRADO.mostrar("Libro");
        } else {
            System.out.print("Ingrese el nuevo titulo: ");
            String nuevoTitulo = teclado.nextLine();
            System.out.print("Ingrese el nuevo Autor: ");
            String nuevoAutor = teclado.nextLine();
            System.out.print("Ingrese la nueva editorial: ");
            String nuevaEditorial = teclado.nextLine();

            if (campoVacio(nuevoTitulo, nuevoAutor, nuevaEditorial)) {
                Mensaje.CAMPOS_VACIOS.mostrar();
            } else {
                System.out.print("Ingrese el nuevo anio de publicacion: ");
                int anioNuevo = Integer.parseInt(teclado.nextLine());

                if (anioValido(anioNuevo)) {
                    System.out.println("Error: Año debe ser mayor a 0 y no puede ser posterior a la fecha actual.");
                } else {
                    aModificar.setAutor(nuevoAutor);
                    aModificar.setTitulo(nuevoTitulo);
                    aModificar.setEditorial(nuevaEditorial);
                    aModificar.setFechaPublicacion(anioNuevo);
                    gestor.modificarLibro(aModificar);
                }
            }
        }
    }

    public void pedirYEliminarLibro() {
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = teclado.nextLine();
        Libro eliminar = gestor.buscarLibroPorISBN(isbn);

        if (eliminar == null) {
            Mensaje.NO_ENCONTRADO.mostrar("Libro");

        } else {
            System.out.println("¿Está seguro de eliminar el libro " + eliminar.getTitulo() + "?\n[No: 0 / Si: 1]");
            int seleccion = Integer.parseInt(teclado.nextLine());
            switch (seleccion) {
                case 0 ->
                    System.out.println("Libro no eliminado.");
                case 1 ->
                    gestor.eliminarLibro(eliminar);
                default ->
                    Mensaje.OPCION_INVALIDA.mostrar();
            }
        }
    }

    // CREAR LIBRO
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
        if (campoVacio(titulo, autor, editorial)) {
            Mensaje.CAMPOS_VACIOS.mostrar();
        } else {

            System.out.println("Ingrese anio de publicacion: ");
            int anio = Integer.parseInt(teclado.nextLine());

            if (anioValido(anio)) {
                System.out.println("Error: Año de publicación inválido.");
            } else {
                libroCreado = new Libro(isbn, titulo, autor, editorial, anio);
            }
        }
        return libroCreado;
    }

    // VERIFICACIONES
    private boolean campoVacio(String titulo, String autor, String editorial) {
        return titulo.isBlank() || autor.isBlank() || editorial.isBlank();
    }

    private boolean anioValido(int anio) {
        return anio <= 0 || anio > LocalDate.now().getYear();
    }

}
