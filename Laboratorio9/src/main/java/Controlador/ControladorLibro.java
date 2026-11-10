package Controlador;

import Clases.Libro;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ControladorLibro {
  
    private final Session session;

    public ControladorLibro(Session session) {
        this.session = session;
    }
    
   public void cargarLibro(Libro libro){
        Transaction trx = session.beginTransaction();
        try {
        session.persist(libro);
        trx.commit();
        } catch (Exception e) {
        trx.rollback();
        System.out.println("Error al guardar el libro: " + e.getMessage());
        }
    }
   
   public List<Libro> listarLibros(){
       Transaction trx = session.beginTransaction();
     
       List<Libro> libros = session.createQuery("FROM LIBRO", Libro.class).getResultList();
       
       trx.commit();
       return libros;
   }
   
   public Libro buscarLibroPorISBN(String isbn){
       Transaction trx = session.beginTransaction();

        Libro libroEncontrado = session.createQuery("FROM LIBRO where ISBN =: isbn", Libro.class).setParameter("isbn", isbn).getSingleResult();

        trx.commit();
        return libroEncontrado;
   }
   
   public void modificarLibro(Libro libro){
       Transaction trx = session.beginTransaction();
       
       try{
       session.merge(libro);
       System.out.println("Libro modificado con exito");
       
       trx.commit();
       }catch (Exception e) {
        trx.rollback();
        System.out.println("Error al modificar el libro: " + e.getMessage());
        }
   }
   
   public void eliminarLibro(Libro libro){
       Transaction trx = session.beginTransaction();
       
       try{
       session.remove(libro);
       System.out.println("Libro eliminado con exito");
       
       trx.commit();
       }catch (Exception e) {
        trx.rollback();
        System.out.println("Error al eliminar el libro: " + e.getMessage());
        }
   }
}
