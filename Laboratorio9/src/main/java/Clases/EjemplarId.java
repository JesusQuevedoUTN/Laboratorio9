package Clases;

import java.io.Serializable;
public class EjemplarId implements Serializable {

    private String isbn;
    private Integer nroEjemplar;

    public EjemplarId() {
    }

    public EjemplarId(String isbn, Integer nroEjemplar) {
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
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
}
