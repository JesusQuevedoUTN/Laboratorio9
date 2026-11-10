package Controlador;

import Clases.Libro;
import herramientas.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ControladorLibro {

    public void agregarLibro(Libro nuevoLibro) {
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

    public void listarLibros() {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            List<Libro> libros = conexion.createQuery("FROM Libro", Libro.class).list();

            System.out.printf("%-20s %-30s %-25s %-25s %-10s%n", "ISBN", "TÍTULO", "AUTOR", "EDITORIAL", "AÑO");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------");

            for (Libro l : libros) {
                System.out.printf("%-20s %-30s %-25s %-25s %-10d%n",
                        l.getIsbn(),
                        l.getTitulo(),
                        l.getAutor(),
                        l.getEditorial(),
                        l.getFechaPublicacion());
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        }
    }

    public Libro buscarLibro(String isbn) {
        Libro buscado = null;
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            buscado = conexion.find(Libro.class, isbn);
        } catch (Exception e) {
            System.out.println("Error al buscar libro: " + e.getMessage());
        }
        return buscado;
    }

    public void modificarLibro(Libro libroModificado) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // SELECT libro
            Libro modificar = conexion.find(Libro.class, libroModificado.getIsbn());

            if (modificar != null) {
                
                // Modificamos el libro original
                modificar.setTitulo(libroModificado.getTitulo());
                modificar.setAutor(libroModificado.getAutor());
                modificar.setEditorial(libroModificado.getEditorial());
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

    public void eliminarLibro(Libro eliminar) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()){
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
            System.out.println("Error al eliminar libro: " + e.getMessage());
        } 
    }
}
