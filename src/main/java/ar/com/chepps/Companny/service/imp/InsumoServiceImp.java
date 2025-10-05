package ar.com.chepps.Companny.service.imp;

import ar.com.chepps.Companny.container.InsumoDTO;
import ar.com.chepps.Companny.dao.InsumoRepository;
import ar.com.chepps.Companny.dao.MarcaRepository;
import ar.com.chepps.Companny.entity.Insumo;
import ar.com.chepps.Companny.entity.Marca;
import ar.com.chepps.Companny.helpers.HelperDTO;
import ar.com.chepps.Companny.service.IInsumoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InsumoServiceImp implements IInsumoService {
    @Autowired
    private InsumoRepository repo;
    @Autowired
    private MarcaRepository repoMarca;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public InsumoDTO guardar(InsumoDTO idto) {
        if (idto != null) {
            Insumo i = (Insumo) HelperDTO.convertirADtoOEntity(idto, false);

            if (i.getMarca() != null) {
                Marca marcaInput = i.getMarca();
                Marca marcaProcesada = null;

                if (marcaInput.getIdMarca() != null) {
                    marcaProcesada = repoMarca.findById(marcaInput.getIdMarca())
                            .orElseThrow(() -> new EntityNotFoundException("Marca no encontrada " +
                                    "con id: " + marcaInput.getIdMarca()));
                } else {
                    if (marcaInput.getNombre() != null && !marcaInput.getNombre().isBlank()) {
                        marcaProcesada = repoMarca.save(marcaInput);
                    }
                }

                i.setMarca(marcaProcesada);
            }

            Insumo in = repo.save(i);
            return (InsumoDTO) HelperDTO.convertirADtoOEntity(in, true);
        }
        return new InsumoDTO();
    }

    @Transactional
    @Override
    public List<InsumoDTO> retornarInsumos(boolean esParaElaborar) {
        List<Insumo> insumos = (!esParaElaborar) ? repo.findByEliminadoFalse() :
                repo.findByEliminadoFalseAndEsParaElaborarTrue();
        ;
        List<InsumoDTO> insumosDTO = new ArrayList<>();
        for (Insumo i : insumos) {
            if (i != null) {
                insumosDTO.add((InsumoDTO) HelperDTO.convertirADtoOEntity(i, true));

            }
        }
        return insumosDTO;
    }

    @Override
    public InsumoDTO buscarPorId(Long idInsumo) {
        Optional op = repo.findById(idInsumo);
        if (op.isPresent()) {
            InsumoDTO dto = (InsumoDTO) HelperDTO.convertirADtoOEntity(op.get(), true);
            return dto;
        }
        return new InsumoDTO();
    }

    @Override
    public String eliminar(Long idInsumo) {
        InsumoDTO i = buscarPorId(idInsumo);
        if (i != null && !i.getEliminado()) {
            Insumo insumoEntity = (Insumo) HelperDTO.convertirADtoOEntity(i, false);
            insumoEntity.setEliminado(true);
            repo.save(insumoEntity);
            return "Insumo " + i.getDenominacion() + ", Eliminado";
        }
        return "No encontrado";
    }
}
