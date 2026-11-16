package Controlador;

import Clases.Ejemplar;
import herramientas.HibernateUtil;
import herramientas.Mensaje;
import java.util.List;
import org.hibernate.Session;

public class ControladorEjemplar {

    public void agregarEjemplar(Ejemplar nuevoEjemplar) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // INSERT 
            conexion.persist(nuevoEjemplar);
            conexion.getTransaction().commit();

            System.out.println("Ejemplar agregado correctamente");

        } catch (Exception e) {
            System.out.println("Error al agregar el ejemplar: " + e.getMessage());
        }
    }

    public void cargarEjemplar(Ejemplar nuevoEjemplar) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // INSERT 
            conexion.persist(nuevoEjemplar);
            conexion.getTransaction().commit();

            System.out.println("Ejemplar agregado correctamente");

        } catch (Exception e) {
            System.out.println("Error al agregar el ejemplar: " + e.getMessage());
        }
    }

    public List listarEjemplares(String isbnBuscado) {
        List<Ejemplar> ejemplares = null;
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            ejemplares = conexion.createQuery("FROM Ejemplar e WHERE e.isbn = :isbn", Ejemplar.class)
                    .setParameter("isbn", isbnBuscado)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("Error al listar ejemplares: " + e.getMessage());
        }
        return ejemplares;
    }

    public void modificarEstado(Ejemplar ejemplarModificado) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // Buscar ejemplar
            Ejemplar modificar = buscarEjemplar(ejemplarModificado.getIsbn(), ejemplarModificado.getNroEjemplar());

            if (modificar != null) {
                // UPDATE
                modificar.setEstado(ejemplarModificado.getEstado());
                conexion.getTransaction().commit();

                System.out.println("Ejemplar modificado correctamente");
            } else {
                Mensaje.NO_ENCONTRADO.mostrar("Ejemplar");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar ejemplar: " + e.getMessage());
        }
    }

    public static Ejemplar buscarEjemplar(String isbn, Integer nro) {
        Ejemplar buscado = null;
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            buscado = conexion.createQuery("FROM Ejemplar e WHERE e.isbn = :isbn AND e.nroEjemplar = :nro", Ejemplar.class)
                    .setParameter("isbn", isbn)
                    .setParameter("nro", nro)
                    .uniqueResult();
        } catch (Exception e) {
            System.out.println("Error al modificar ejemplar: " + e.getMessage());
        }
        return buscado;
    }
}
