package Controlador;

import Clases.Ejemplar;
import herramientas.Estado;
import herramientas.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ControladorEjemplar {

     private final Session session;

    public ControladorEjemplar(Session session) {
        this.session = session;
    }
    
    public void cargarEjemplar(Ejemplar nuevoEjemplar){
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
    
    public void listarEjemplares(){
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()){
           List<Ejemplar> ejemplares = conexion.createQuery("FROM EJEMPLAR", Ejemplar.class).getResultList();
           System.out.printf("%-5s %-25s %-15s%n", "ISBN", "Numero", "Estado");
            System.out.println("---------------------------------------------------------");

            for (Ejemplar consulta : ejemplares) {
                String ISBN = consulta.getIsbn();
                int nro = consulta.getNroEjemplar();
                Estado estado = consulta.getEstado();
                
                System.out.printf(
                        "%-5s %-25d %-15s\n",
                        ISBN, nro, estado);
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        }
    }
    
    public void modificarEstado(Ejemplar ejemplarModificado){
        try (Session conexion = HibernateUtil.getSessionFactory().openSession()) {
            conexion.beginTransaction();

            // SELECT ejemplar
            Ejemplar modificar = conexion.find(Ejemplar.class, ejemplarModificado.getIsbn());

            if (modificar != null) {

                // Modificamos el libro original
                modificar.setIsbn(ejemplarModificado.getIsbn());
                modificar.setNroEjemplar(ejemplarModificado.getNroEjemplar());
                modificar.setEstado(ejemplarModificado.getEstado());

                // UPDATE
                conexion.merge(modificar);
                conexion.getTransaction().commit();

                System.out.println("Ejemplar modificado correctamente");
            } else {
                System.out.println("No existe un ejemplar con ese ISBN.");
            }
        } catch (Exception e) {
            System.out.println("Error al modificar ejemplar: " + e.getMessage());
        }
    }
    
    
}
