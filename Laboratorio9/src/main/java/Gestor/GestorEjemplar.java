package Gestor;

import Clases.Ejemplar;
import Controlador.ControladorEjemplar;
import herramientas.Estado;
import herramientas.Mensaje;
import herramientas.Pausa;
import java.util.Scanner;
import herramientas.GestorMenu;

public class GestorEjemplar implements GestorMenu {

    private ControladorEjemplar gestor = new ControladorEjemplar();
    private Scanner teclado = new Scanner(System.in);

    @Override
    public void ejecutarMenu() {
        int menu = -1;

        do {
            try {
                System.out.print(
                        "\n=== Ejemplar Menú ===\n"
                        + "1. Agregar ejemplar\n"
                        + "2. Listar ejemplares de un libro\n"
                        + "3. Modificar estado\n"
                        + "0. Salir\n"
                        + "Opcion: ");

                String entrada = teclado.nextLine();
                menu = Integer.parseInt(entrada);

                switch (menu) {
                    case 1 ->
                        pedirYAgregarEjemplar();
                    case 2 ->
                        pedirYListarEjemplares();
                    case 3 ->
                        pedirYModificarEstado();
                    case 0 ->
                        System.out.println("<--\n\n");
                    default ->
                        Mensaje.opcionInvalida();
                }

            } catch (NumberFormatException e) {
                Mensaje.tipoDatoIncorrecto();
            }

            if (menu != 0) {
                Pausa.pausar();
            }
        } while (menu != 0);
    }

    private Ejemplar crearEjemplar() {
        Ejemplar nuevo = null;

        System.out.println("Ingres ISBN: ");
        String isbn = teclado.nextLine();

        System.out.println("Ingrese nro de ejemplar: ");
        int nro = Integer.parseInt(teclado.nextLine());

        System.out.println("Que estado quiere darle al ejemplar [1: Disponible| 2:Prestado]: ");

        int estado = Integer.parseInt(teclado.nextLine());

        switch (estado) {
            case 1 ->
                nuevo = new Ejemplar(isbn, nro, Estado.Disponible);

            case 2 ->
                nuevo = new Ejemplar(isbn, nro, Estado.Prestado);
            default ->
                System.out.println("Error: Estado inválido.");
        }
        return nuevo;
    }

    private void pedirYAgregarEjemplar() {
        Ejemplar nuevoEjemplar = crearEjemplar();
        if (nuevoEjemplar != null) {
            gestor.agregarEjemplar(nuevoEjemplar);
        }
        
        
    }

    private void pedirYModificarEstado() {

        System.out.println("Ingrese el ISBN del Ejemplar a Buscar:");
        String isbn = teclado.nextLine();

        System.out.println("Ingrese nro de ejemplar: ");
/*
        int nro = Integer.parseInt(teclado.nextLine());
        System.out.println("Que estado quiere darle al ejemplar [1:Disponible|2:Prestado]: ");

        int estado = Integer.parseInt(teclado.nextLine());
        switch (estado) {
            case 1 ->
            {
                Ej
                gestor.modificarEstado(ejemplarModificado);}
            case 2 ->
            {
                gestor.agregarEjemplar(ejemplarModificado);}
            default ->
                System.out.println("Error: Estado inválido.");
        }*/
    }

    private void pedirYListarEjemplares() {
        String ISBN = null;

        System.out.println("Ingrese el ISBN del libro buscado: ");
        ISBN = teclado.nextLine();
        if (ISBN == null || ISBN.isEmpty()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
        } else {
            //gestor.listarEjemplares(ISBN);
        }

    }

}
