package Controlador;

import Clases.Ejemplar;
import herramientas.Estado;
import herramientas.HibernateUtil;
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

    public void listarEjemplares(String isbnBuscado) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            List<Ejemplar> ejemplares = conexion.createQuery("FROM Ejemplar e WHERE e.isbn = :isbn", Ejemplar.class)
                    .setParameter("isbn", isbnBuscado)
                    .getResultList();

            System.out.printf("%-15s %-15s %-15s%n", "ISBN", "Numero", "Estado");
            System.out.println("---------------------------------------------------------");

            for (Ejemplar consulta : ejemplares) {
                System.out.printf(
                        "%-15s %-15d %-15s\n",
                        consulta.getIsbn(), consulta.getNroEjemplar(), consulta.getEstado().name());
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error al listar ejemplares: " + e.getMessage());
        }
    }

    public void modificarEstado(Ejemplar ejemplarModificado) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // Buscar ejemplar
            Ejemplar modificar = conexion.createQuery("FROM Ejemplar e WHERE e.isbn = :isbn AND e.nroEjemplar = :nro", Ejemplar.class)
                    .setParameter("isbn", ejemplarModificado.getIsbn())
                    .setParameter("nro", ejemplarModificado.getNroEjemplar())
                    .uniqueResult();

            if (modificar != null) {
                // UPDATE
                modificar.setEstado(ejemplarModificado.getEstado());
                conexion.merge(modificar);
                conexion.getTransaction().commit();

                System.out.println("Ejemplar modificado correctamente");
            } else {
                System.out.println("No existe un ejemplar con ese ISBN y Número.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar ejemplar: " + e.getMessage());
        }
    }
}