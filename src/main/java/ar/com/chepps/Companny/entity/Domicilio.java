package ar.com.chepps.Companny.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "Domicilio")
public class Domicilio {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idDomicilio;
    private String direccion;
    private String provincia;

    public Domicilio() {
    }

    public Long getIdDomicilio() {
        return idDomicilio;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
