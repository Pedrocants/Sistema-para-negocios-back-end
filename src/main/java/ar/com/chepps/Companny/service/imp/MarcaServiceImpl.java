package ar.com.chepps.Companny.service.imp;

import ar.com.chepps.Companny.container.MarcaDTO;
import ar.com.chepps.Companny.dao.MarcaRepository;
import ar.com.chepps.Companny.entity.Marca;
import ar.com.chepps.Companny.helpers.HelperDTO;
import ar.com.chepps.Companny.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MarcaServiceImpl implements IMarcaService {

    @Autowired
    protected MarcaRepository repo;


    @Override
    public List<MarcaDTO> getMarcas() {
        MarcaDTO dto = null;
        List<Marca> marcas = repo.findAll();
        List<MarcaDTO> marcasDTO = new ArrayList<>();

        for (Marca marca : marcas) {
            try {

                dto = (MarcaDTO) HelperDTO.convertirMarca(marca, true);
                marcasDTO.add(dto);
            } catch (NullPointerException e) {
                System.err.println(e.getMessage());
                return new ArrayList<>();
            }
        }
        return marcasDTO;
    }

    @Override
    public MarcaDTO guardarMarca(MarcaDTO marca) {
        if (Objects.isNull(marca)) {
            return null;
        }
        Marca m = (Marca) HelperDTO.convertirMarca(marca, false);
        m = repo.save(m);
        return (MarcaDTO) HelperDTO.convertirMarca(m, true);
    }

    @Override
    public MarcaDTO eliminar(Long idMarca) {
        return null;
    }

    @Override
    public MarcaDTO buscarPorId(Long idMarca) {
        if (idMarca == null){
            return null;
        }
        Optional<Marca> o = repo.findById(idMarca);
        Marca m = (o.isPresent()) ? o.get() : null;
        MarcaDTO dto = (!Objects.isNull(m)) ? (MarcaDTO) HelperDTO.convertirMarca(m, true): null;
        return dto;
    }
}
