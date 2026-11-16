package Controlador;

import Clases.Libro;
import herramientas.HibernateUtil;
import herramientas.Mensaje;
import java.util.List;
import org.hibernate.Session;

public class ControladorLibro {

    public void cargarLibro(Libro nuevoLibro) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // INSERT VALUES
            conexion.persist(nuevoLibro);
            conexion.getTransaction().commit();

            System.out.println("Libro agregado correctamente");

        } catch (Exception e) {
            System.out.println("Error al agregar el libro: " + e.getMessage());
        }
    }

    public List<Libro> listarLibros() {
        List<Libro> libros = null;
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            //SELECT *
            libros = conexion.createQuery("FROM Libro", Libro.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener libros: " + e.getMessage());
        }
        return libros;
    }

    public static Libro buscarLibroPorISBN(String isbn) {
        Libro buscado = null;

        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            //SELECT POR ID
            buscado = conexion.find(Libro.class, isbn);
        } catch (Exception e) {
            System.out.println("Error al buscar socio: " + e.getMessage());
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
                modificar.setEditorial(libroModificado.getEditorial());
                modificar.setAutor(libroModificado.getAutor());
                modificar.setFechaPublicacion(libroModificado.getFechaPublicacion());

                // UPDATE
                conexion.getTransaction().commit();

                System.out.println("Libro modificado correctamente");
            } else {
                Mensaje.NO_ENCONTRADO.mostrar("ISBN");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar libro: " + e.getMessage());
        }
    }

    public void eliminarLibro(Libro eliminar) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            Libro libro = conexion.find(Libro.class, eliminar.getIsbn());

            if (libro != null) {
                //DELETE
                conexion.remove(libro);
                conexion.getTransaction().commit();
                System.out.println("Libro eliminado correctamente");
            } else {
                Mensaje.NO_ENCONTRADO.mostrar("ISBN");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar Libro: " + e.getMessage());
        }

    }

}
