package Controlador;

import Clases.Libro;
import herramientas.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ControladorLibro {
    
   public void cargarLibro(Libro nuevoLibro){
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // INSERT 
            conexion.persist(nuevoLibro);
            conexion.getTransaction().commit();

            System.out.println("Libro agregado correctamente");

        } catch (Exception e) {
            System.out.println("Error al agregar el libro: " + e.getMessage());
        }
    }
   
   public void listarLibros(){
       try (Session conexion = HibernateUtil.getSessionFactory().openSession()){
           List<Libro> libros = conexion.createQuery("FROM LIBRO", Libro.class).getResultList();
           System.out.printf("%-5s %-25s %-15s %-15s -%15s%n", "ISBN", "Titulo", "Autor", "Editorial" , "Fecha Publicacion");
            System.out.println("---------------------------------------------------------");

            for (Libro consulta : libros) {
                String ISBN = consulta.getIsbn();
                String titulo = consulta.getTitulo();
                String autor = consulta.getAutor();
                String editorial = consulta.getEditorial();
                int fecha_publicacion = consulta.getFechaPublicacion();
                

                System.out.printf(
                        "%-5s %-25s %-15s %-15s -%15d\n",
                        ISBN, titulo, autor, editorial, fecha_publicacion);
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        }
       }
   
   public Libro buscarLibroPorISBN(String isbn){
       Libro buscado = null;

        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            buscado = conexion.find(Libro.class, isbn);
        } catch (Exception e) {
            System.out.println("Error al buscar socio: " + e.getMessage());
        }

        return buscado;
   }
   
   public void modificarLibro(Libro libroModificado){
       try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // SELECT libro
            Libro modificar = conexion.find(Libro.class, libroModificado.getIsbn());

            if (modificar != null) {

                // Modificamos el libro original
                modificar.setIsbn(libroModificado.getIsbn());
                modificar.setTitulo(libroModificado.getTitulo());
                modificar.setEditorial(libroModificado.getEditorial());
                modificar.setAutor(libroModificado.getAutor());
                modificar.setFechaPublicacion(libroModificado.getFechaPublicacion());

                // UPDATE
                conexion.merge(modificar);
                conexion.getTransaction().commit();

                System.out.println("Libro modificado correctamente");
            } else {
                System.out.println("No existe un libro con ese ISBN.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar libro: " + e.getMessage());
        }
   }
   
   public void eliminarLibro(Libro eliminar){
       try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            Libro libro = conexion.find(Libro.class, eliminar.getIsbn());

            if (libro != null) {
                //DELETE
                conexion.remove(libro);
                conexion.getTransaction().commit();
                System.out.println("Libro eliminado correctamente");
            } else {
                System.out.println("No existe un libro con ese ISBN.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Libro: " + e.getMessage());
        }

   }

}


