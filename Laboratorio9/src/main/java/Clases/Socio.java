package Clases;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity 
@Table(name = "SOCIO")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio", length = 11, nullable = false)
    private Integer idSocio;
    
    
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombreCompleto;
    
    @Column(name = "DNI", length = 50, nullable = false)
    private String dni;
    
    @Column(name = "telefono", length = 20, nullable = true)
    private String telefono;
    
    @Column(name = "fecha_alta", nullable = false)
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
