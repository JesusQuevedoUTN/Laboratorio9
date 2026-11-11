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
    
    public Libro crearLibro(){
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
                
               libroCreado = new Libro(isbn,titulo,autor,editorial,anio);
            }
        }
        return libroCreado;
    }
    
    public void pedirYAgregarLibro(){
        Libro agregar = crearLibro();
        if(agregar != null){
            gestor.cargarLibro(agregar);
        }
    }
    
     public void pedirYBuscarLibro() {
         System.out.print("Ingrese el ISBN a buscar: ");
        String isbn = teclado.nextLine();

        Libro mostrar = gestor.buscarLibroPorISBN(isbn);
        if (mostrar != null) {
            System.out.println(
                    "\n--- Socio Encontrado ---"
                    + "\nISBN: " + mostrar.getIsbn()
                    + "\nTitulo: " + mostrar.getTitulo()
                    + "\nAutor: " + mostrar.getAutor()
                    + "\nEditorial: " + mostrar.getEditorial()
                    + "\nFecha de Publicacion: " + mostrar.getFechaPublicacion()
                    + "\n--------------------------\n");
        } else {
            System.out.println("No se encontró un socio con ese DNI.");
        }
    }
     
    public void pedirYModificarLibro(){
        
    System.out.print("Ingrese el ISBN del libro a modificar: ");
        String isbnmodificar = teclado.nextLine();

        Libro aModificar = gestor.buscarLibroPorISBN(isbnmodificar);
        if (aModificar != null) {
            System.out.print("Ingrese el nuevo titulo: ");
            String nuevoTitulo = teclado.nextLine();
            System.out.print("Ingrese el nuevo Autor: ");
            String nuevoAutor = teclado.nextLine();
            System.out.print("Ingrese la nueva editorial: ");
            String nuevaEditorial = teclado.nextLine();
            if (nuevoTitulo.isBlank() || nuevoAutor.isBlank() || nuevaEditorial.isBlank()) {
                System.out.println("Error: Los campos no pueden estar vacíos.");
            } else {
                System.out.println("Ingrese el nuevo anio de publicacion: ");
                int anioNuevo = Integer.parseInt(teclado.nextLine());
                if (anioNuevo <= 0 || anioNuevo > LocalDate.now().getYear()) {
                System.out.println("Error: Año de publicación inválido.");
            } 
                gestor.modificarLibro(aModificar);
            }
        }else{
            System.out.println("No existe ese libro.");
        }
    }
    
    public void pedirYEliminarLibro(){
        System.out.print("Ingrese el ISBN del socio a eliminar: ");
        
        String isbn = teclado.nextLine();
        Libro eliminar = gestor.buscarLibroPorISBN(isbn);
        
        if (eliminar != null) {
            gestor.eliminarLibro(eliminar);
        }else{
            System.out.println("No existe el libro.");
        }
    }
    
}
