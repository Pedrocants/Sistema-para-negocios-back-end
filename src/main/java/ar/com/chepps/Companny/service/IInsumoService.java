package ar.com.chepps.Companny.service;

import ar.com.chepps.Companny.container.InsumoDTO;
import ar.com.chepps.Companny.entity.Insumo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IInsumoService {
    public InsumoDTO guardar(InsumoDTO idto);
    public List<InsumoDTO> retornarInsumos(boolean esParaElaborar);
    public InsumoDTO buscarPorId(Long idInsumo);
    public String eliminar(Long idInsumo);
}
