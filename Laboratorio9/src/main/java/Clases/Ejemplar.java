package Clases;

import herramientas.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "EJEMPLAR")
@IdClass(EjemplarId.class)
public class Ejemplar {

    @Id
    @Column(name = "ISBN", length = 20, nullable = false)
    private String isbn;

    @Column(name = "nro_ejemplar", length = 11, nullable = false)
    private Integer nroEjemplar;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 20, nullable = false)
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
