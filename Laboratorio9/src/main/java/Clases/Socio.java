package Clases;

import java.time.LocalDate;

public class Socio {

    private Integer idSocio;
    private String nombreCompleto;
    private String dni;
    private String telefono;
    private LocalDate fechaAlta;

    public Socio() {
    }

    public Socio(Integer idSocio, String nombreCompleto, String dni, String telefono, LocalDate fechaAlta) {
        this.idSocio = idSocio;
        this.nombreCompleto = nombreCompleto;
        this.dni = dni;
        this.telefono = telefono;
        this.fechaAlta = fechaAlta;
    }

    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Integer idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public String toString() {
        return "Socio{" + "idSocio=" + idSocio + ", nombreCompleto=" + nombreCompleto + ", dni=" + dni + ", telefono=" + telefono + ", fechaAlta=" + fechaAlta + '}';
    }

}
