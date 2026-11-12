package Controlador;

import Clases.Socio;
import herramientas.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

public class ControladorSocio {

    // 4.1. Agregar socio
    public void agregarSocio(Socio nuevoSocio) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // INSERT 
            conexion.persist(nuevoSocio);
            conexion.getTransaction().commit();

            System.out.println("Socio agregado correctamente");

        } catch (Exception e) {
            System.out.println("Error al agregar el libro: " + e.getMessage());
        }
    }

    // 4.2. Listar socios
    public void listarSocios() {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            List<Socio> socios = conexion.createQuery("FROM Socio", Socio.class).list();

            System.out.printf("%-5s %-25s %-15s %-15s%n", "ID", "NOMBRE", "DNI", "TELÉFONO");
            System.out.println("---------------------------------------------------------");

            for (Socio consulta : socios) {
                int id = consulta.getIdSocio();
                String nombre = consulta.getNombreCompleto();
                String dni = consulta.getDni();
                String telefono = consulta.getTelefono();

                System.out.printf(
                        "%-5d %-25s %-15s %-15s\n",
                        id, nombre, dni, telefono);
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error al listar socios: " + e.getMessage());
        }
    }

    // 4.3. Buscar socio por DNI
    public Socio buscarSocioPorDni(String dni) {
        Socio buscado = null;

        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            buscado = conexion.createQuery("FROM Socio s WHERE s.dni = :dniBuscado", Socio.class).setParameter("dniBuscado", dni).uniqueResult();
        } catch (Exception e) {
            System.out.println("Error al buscar socio: " + e.getMessage());
        }

        return buscado;
    }

    public Socio buscarSocioPorId(Integer id) {
        Socio buscado = null;

        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            buscado = conexion.find(Socio.class, id);
        } catch (Exception e) {
            System.out.println("Error al buscar socio: " + e.getMessage());
        }

        return buscado;
    }

    // 4.4. Modificar socio (Solo Nombre y Teléfono)
    public void modificarSocio(Socio socioModificado) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // SELECT libro
            Socio modificar = conexion.find(Socio.class, socioModificado.getIdSocio());

            if (modificar != null) {

                // Modificamos el libro original
                //niModificar, nuevoNombre, nuevoTelefono);
                modificar.setDni(socioModificado.getDni());
                modificar.setNombreCompleto(socioModificado.getNombreCompleto());
                modificar.setTelefono(socioModificado.getTelefono());

                // UPDATE
                conexion.merge(modificar);
                conexion.getTransaction().commit();

                System.out.println("Socio modificado correctamente");
            } else {
                System.out.println("No existe un socio con ese DNI.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar libro: " + e.getMessage());
        }
    }

    // 4.5. Eliminar socio (Validando préstamos)
    public void eliminarSocio(Socio eliminar) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();
            boolean sePuedeEliminar = true;

            // 1. Verificamos si tiene préstamos usando HQL para contar[cite: 3]
            Long totalPrestamos = conexion.createQuery("SELECT COUNT(p) FROM Prestamo p WHERE p.idSocio = :id", Long.class)
                    .setParameter("id", eliminar.getIdSocio())
                    .uniqueResult();

            if (totalPrestamos != null && totalPrestamos > 0) {
                System.out.println("Error: No se puede eliminar el socio porque tiene préstamos asociados.");
                conexion.getTransaction().rollback(); // Cancelamos la transacción
                sePuedeEliminar = false;
            }

            if (sePuedeEliminar) {
                Socio socio = conexion.find(Socio.class, eliminar.getIdSocio());

                if (socio != null) {
                    //DELETE
                    conexion.remove(socio);
                    conexion.getTransaction().commit();
                    System.out.println("Socio eliminado correctamente");
                } else {
                    System.out.println("No existe un socio con ese ID.");
                }

            }
        } catch (Exception e) {
            System.out.println("Error al eliminar socio: " + e.getMessage());
        }
    }
}
