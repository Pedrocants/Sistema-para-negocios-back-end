package ar.com.chepps.Companny.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "UnidadMedida")
public class UnidadMedida {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idUnidadMedida;
    private String denominacion;

    public UnidadMedida() {
    }

    public Long getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
}