package ar.com.chepps.Companny.service;

import ar.com.chepps.Companny.entity.UnidadMedida;

import java.util.List;

public interface IUnidadMedidaService {
    public UnidadMedida agregarUnidadMedida(UnidadMedida u);
    public List<UnidadMedida> retornarUnidades();
    public boolean eliminar(Long id);
}
