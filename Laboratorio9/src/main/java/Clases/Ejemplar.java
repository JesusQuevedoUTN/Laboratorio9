package Clases;

import herramientas.Estado;

public class Ejemplar {

    private String isbn;
    private Integer nroEjemplar;
    private Estado estado;

    public Ejemplar() {
    }

    public Ejemplar(String isbn, Integer nroEjemplar, Estado estado) {
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
        this.estado = estado;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getNroEjemplar() {
        return nroEjemplar;
    }

    public void setNroEjemplar(Integer nroEjemplar) {
        this.nroEjemplar = nroEjemplar;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ejemplar{" + "isbn=" + isbn + ", nroEjemplar=" + nroEjemplar + ", estado=" + estado + '}';
    }

}
