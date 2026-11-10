package Clases;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;


@Entity 
@Table(name = "PRESTAMO")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo", length = 11, nullable = false)
    private Integer idPrestamo;
    
    @Column(name = "id_socio", length = 11, nullable = false)
    private Integer idSocio;
    
    @Column(name = "ISBN", length = 20, nullable = false)
    private String isbn;
    
    @Column(name = "nro_ejemplar", length = 11, nullable = false)
    private Integer nroEjemplar;
    
    @Column(name = "fecha_prestamo", nullable = false)
    private LocalDate fechaPrestamo;
    
    @Column(name = "fecha_devolucion_prevista", nullable = false)
    private LocalDate fechaPrevista;
    
    @Column(name = "fecha_devolucion_real", nullable = true)
    private LocalDate fechaReal;

    public Prestamo() {
    }

    public Prestamo(Integer idPrestamo, Integer idSocio, String isbn, Integer nroEjemplar, LocalDate fechaPrestamo, LocalDate fechaPrevista, LocalDate fechaReal) {
        this.idPrestamo = idPrestamo;
        this.idSocio = idSocio;
        this.isbn = isbn;
        this.nroEjemplar = nroEjemplar;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaPrevista = fechaPrevista;
        this.fechaReal = fechaReal;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Integer idSocio) {
        this.idSocio = idSocio;
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

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaPrevista() {
        return fechaPrevista;
    }

    public void setFechaPrevista(LocalDate fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    public LocalDate getFechaReal() {
        return fechaReal;
    }

    public void setFechaReal(LocalDate fechaReal) {
        this.fechaReal = fechaReal;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "idPrestamo=" + idPrestamo + ", idSocio=" + idSocio + ", isbn=" + isbn + ", nroEjemplar=" + nroEjemplar + ", fechaPrestamo=" + fechaPrestamo + ", fechaPrevista=" + fechaPrevista + ", fechaReal=" + fechaReal + '}';
    }

}
