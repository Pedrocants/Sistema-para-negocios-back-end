package ar.com.chepps.Companny.container;

import ar.com.chepps.Companny.entity.Insumo;

import java.util.List;

public class MarcaDTO {

    private Long idMarca;
    private String nombre;
    private List<Insumo> insumos;

    public MarcaDTO() {
    }

    public MarcaDTO(Long idMarca, String nombre, List<Insumo> insumos) {
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.insumos = insumos;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumos(List<Insumo> insumos) {
        this.insumos = insumos;
    }
}

