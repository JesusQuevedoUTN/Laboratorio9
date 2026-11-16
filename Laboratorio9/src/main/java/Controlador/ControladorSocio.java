package Controlador;

import Clases.Socio;
import herramientas.HibernateUtil;
import herramientas.Mensaje;
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
    public List<Socio> listarSocios() {
        List<Socio> socios = null;
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            socios = conexion.createQuery("FROM Socio", Socio.class).list();
        } catch (Exception e) {
            System.out.println("Error al listar socios: " + e.getMessage());
        }
        return socios;
    }

    // 4.3. Buscar socio por DNI
    public static Socio buscarSocio(String dni) {
        Socio buscado = null;

        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            buscado = conexion.createQuery("FROM Socio s WHERE s.dni = :dniBuscado", Socio.class).setParameter("dniBuscado", dni).uniqueResult();
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
                modificar.setNombreCompleto(socioModificado.getNombreCompleto());
                modificar.setTelefono(socioModificado.getTelefono());

                // UPDATE
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
    public void eliminarSocio(Integer id) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {

            //Buscar socio por ID
            Socio eliminar = eliminar = conexion.find(Socio.class, id);

            if (eliminar == null) {
                Mensaje.NO_ENCONTRADO.mostrar("Socio");
            } else {
                //Consultar tabla prestamos
                Long totalPrestamos = conexion.createQuery("SELECT COUNT(p) FROM Prestamo p WHERE p.idSocio = :id", Long.class)
                        .setParameter("id", eliminar.getIdSocio())
                        .uniqueResult();
                if (totalPrestamos != null && totalPrestamos > 0) {
                    System.out.println("Error: No se puede eliminar el socio porque tiene préstamos asociados.");
                } else {
                    //DELETE
                    conexion.beginTransaction();
                    conexion.remove(eliminar);
                    conexion.getTransaction().commit();
                    System.out.println("Socio eliminado correctamente");
                }

            }
        } catch (Exception e) {
            System.out.println("Error al eliminar socio: " + e.getMessage());
        }

    }
}
