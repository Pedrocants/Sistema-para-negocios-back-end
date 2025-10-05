package ar.com.chepps.Companny.controller;

import ar.com.chepps.Companny.container.MarcaDTO;
import ar.com.chepps.Companny.service.IMarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class MarcaController {

    @Autowired
    private IMarcaService srv;

    @GetMapping("marca/getMarcas")
    public ResponseEntity<?> getMarca() {
        List<MarcaDTO> marcas = srv.getMarcas();

        return ResponseEntity.ok(marcas);
    }
    @PostMapping("marca/save")
    @ResponseStatus(HttpStatus.CREATED)
    public MarcaDTO guardar(MarcaDTO marca) {
        return srv.guardarMarca(marca);
    }
    @GetMapping("marca/buscar/{idMarca}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long idMarca){
        return ResponseEntity.ok(srv.buscarPorId(idMarca));
    }
}
