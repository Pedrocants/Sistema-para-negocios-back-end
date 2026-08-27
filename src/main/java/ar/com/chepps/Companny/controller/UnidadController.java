package ar.com.chepps.Companny.controller;


import ar.com.chepps.Companny.entity.UnidadMedida;
import ar.com.chepps.Companny.service.IUnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UnidadController {

    @Autowired
    private IUnidadMedidaService svr;

    @GetMapping("/unidad/obtenerUnidades")
    @ResponseStatus(HttpStatus.OK)
    public List<UnidadMedida> getUnidades(){
        return svr.retornarUnidades();
    }
}
