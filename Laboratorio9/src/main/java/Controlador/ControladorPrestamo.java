package Controlador;

import Clases.Ejemplar;
import Clases.Libro;
import Clases.Prestamo;
import Clases.Socio;
import herramientas.Estado;
import herramientas.HibernateUtil;
import herramientas.Mensaje;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;

public class ControladorPrestamo {

    public void registrarPrestamo(Prestamo nuevoPrestamo) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // Buscar Socio 
            Socio socio = conexion.get(Socio.class, nuevoPrestamo.getIdSocio());
            if (socio == null) {
                Mensaje.NO_ENCONTRADO.mostrar("Socio");
            } else {

                // Buscar Libro
                Libro libro = ControladorLibro.buscarLibroPorISBN(nuevoPrestamo.getIsbn());
                if (libro == null) {
                    Mensaje.NO_ENCONTRADO.mostrar("Libro");
                } else {

                    // Buscar Ejemplar 
                    Ejemplar ejemplar = ControladorEjemplar.buscarEjemplar(nuevoPrestamo.getIsbn(), nuevoPrestamo.getNroEjemplar());
                    if (ejemplar == null) {
                        Mensaje.NO_ENCONTRADO.mostrar("Ejemplar");
                    } else {
                        
                        if (!ejemplar.getEstado().equals(Estado.Disponible)) {
                            System.out.println("Error: El ejemplar no está disponible para préstamo.");
                        } else {
                            ejemplar.setEstado(Estado.Prestado);
                            conexion.persist(nuevoPrestamo);

                            conexion.getTransaction().commit();
                            System.out.println("Prestamo registrado.");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al registrar prestamo: " + e.getMessage());
        }
    }

    public void registrarDevolucion(int idPrestamo, LocalDate fechaDev) {
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // Buscar el préstamo
            Prestamo prestamo = conexion.get(Prestamo.class, idPrestamo);

            if (prestamo == null) {
                Mensaje.NO_ENCONTRADO.mostrar("Prestamo");
            } else if (prestamo.getFechaReal() != null) {
                System.out.println("Aviso: Este prestamo ya fue devuelto previamente.");
            } else {
                // Actualizamos la fecha de devolución
                prestamo.setFechaReal(fechaDev);

                // Buscamos el ejemplar
                Ejemplar ejemplar = ControladorEjemplar.buscarEjemplar(prestamo.getIsbn(), prestamo.getNroEjemplar());

                if (ejemplar == null) {
                    Mensaje.NO_ENCONTRADO.mostrar("Ejemplar");
                } else {
                    //Todo en orden, commiteamos
                    ejemplar.setEstado(Estado.Disponible);
                    conexion.getTransaction().commit();
                    System.out.println("El ejemplar vuelve a estar disponible.");
                }
            }

        } catch (Exception e) {
            System.out.println("Error al modificar prestamo: " + e.getMessage());
        }
    }

    public List<Prestamo> listarPrestamos() {
        List<Prestamo> prestamos = null;
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            prestamos = conexion.createQuery("FROM Prestamo", Prestamo.class).list();
        } catch (Exception e) {
            System.out.println("Error al listar prestamos: " + e.getMessage());
        }
        return prestamos;
    }
}
