package Gestor;

import Clases.Libro;
import Controlador.ControladorLibro;
import herramientas.Mensaje;
import herramientas.Pausa;
import java.util.Scanner;
import herramientas.GestorMenu;
import java.time.LocalDate;
import org.hibernate.Session;

public class GestorLibro implements GestorMenu {

     
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
                        listarLibros();
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
    
    public void pedirYAgregarLibro(){
        try ( Session session = HibernateUtil.getCurrentSession()){
        ControladorLibro controlador = new ControladorLibro(session);
        
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
                
               Libro libro = new Libro(isbn,titulo,autor,editorial,anio);
               controlador.cargarLibro(libro);

            }
        }
        session.close();
    }
    }
    
     public void pedirYBuscarLibro() {
         try ( Session session = HibernateUtil.getCurrentSession()){
        Libro libro;
        ControladorLibro controlador = new ControladorLibro(session);
        
        System.out.println("Ingrese el ISBN a buscar: ");
        String isbn = teclado.nextLine();
        libro = controlador.buscarLibroPorISBN(isbn);
        libro.toString();
        session.close();
        }
    }
     
     public void listarLibros(){
         try ( Session session = HibernateUtil.getCurrentSession()){
             ControladorLibro controlador = new ControladorLibro(session);
             
             System.out.println("-- LIBROS --");
             System.out.println(controlador.listarLibros());
             
             session.close();
         }
     }
     
    public void pedirYModificarLibro(){
        
        try ( Session session = HibernateUtil.getCurrentSession()){
        ControladorLibro controlador = new ControladorLibro(session);
        
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
                Libro libro = new Libro(isbn,titulo,autor,editorial,anio);
                controlador.modificarLibro(libro);
            }
        }       
            session.close();
        }
    }
    
    public void pedirYEliminarLibro(){
        try ( Session session = HibernateUtil.getCurrentSession()){
        ControladorLibro controlador = new ControladorLibro(session);
        
        System.out.println("Ingrese el ISBN a eliminar: ");
        String isbn = teclado.nextLine();
        int respuesta = 0;
        
        Libro libro = controlador.buscarLibroPorISBN(isbn);
        
        String titulo = libro.getTitulo();
        
        if(titulo != null){
        System.out.println("Estas seguro de que queres borrar el libro: " + titulo + " ? [1 para SI || otro numero para NO]");
        respuesta = Integer.parseInt(teclado.nextLine());
        if (respuesta == 1) {
            controlador.eliminarLibro(libro);
            
        }else{
            System.out.println("No se borro: " + titulo);
         }
        }
        session.close();
        }
    }
}
