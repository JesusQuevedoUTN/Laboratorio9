package Clases;

import java.io.Serializable;
import java.util.Objects;

// Vibecodeado aún

public class EjemplarId implements Serializable {
    private String isbn;
    private Integer nroEjemplar;

    public EjemplarId() {}

    public EjemplarId(String isbn, Integer nroEjemplar) {
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EjemplarId that = (EjemplarId) o;
        return Objects.equals(isbn, that.isbn) && Objects.equals(nroEjemplar, that.nroEjemplar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn, nroEjemplar);
    }
}