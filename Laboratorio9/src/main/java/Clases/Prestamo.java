package Clases;

import java.time.LocalDate;

public class Prestamo {

    private Integer idPrestamo;
    private Integer idSocio;
    private String isbn;
    private Integer nroEjemplar;
    private LocalDate fechaPrestamo;
    private LocalDate fechaPrevista;
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
