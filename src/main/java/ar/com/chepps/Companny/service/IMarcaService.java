package ar.com.chepps.Companny.service;

import ar.com.chepps.Companny.container.MarcaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IMarcaService {
    public List<MarcaDTO> getMarcas();

    public MarcaDTO guardarMarca(MarcaDTO marca);

    public MarcaDTO eliminar(Long idMarca);

    public MarcaDTO buscarPorId(Long idMarca);
}
