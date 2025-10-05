package ar.com.chepps.Companny.service.imp;

import ar.com.chepps.Companny.dao.UnidadMedidaRepository;
import ar.com.chepps.Companny.entity.UnidadMedida;
import ar.com.chepps.Companny.service.IUnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaServiceImp implements IUnidadMedidaService {

    @Autowired
    private UnidadMedidaRepository repo;

    @Override
    public UnidadMedida agregarUnidadMedida(UnidadMedida u) {
        return null;
    }

    @Override
    public List<UnidadMedida> retornarUnidades() {
        return repo.findAll();
    }

    @Override
    public boolean eliminar(Long id) {
        UnidadMedida u = repo.findById(id).orElse(null);
        if(u != null){
            repo.delete(u);
            return true;
        }
        return false;
    }
}
