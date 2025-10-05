package ar.com.chepps.Companny.controller;

import ar.com.chepps.Companny.container.InsumoDTO;
import ar.com.chepps.Companny.service.IInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class InsumoController {
    @Autowired
    private IInsumoService srv;

    @PostMapping(value = "/insumo/guardar", produces = MediaType.APPLICATION_JSON_VALUE)
    public InsumoDTO guardarInsumo(@RequestBody InsumoDTO in) {
        return srv.guardar(in);
    }

    @GetMapping(value = "/insumo/retornarTodos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InsumoDTO> retornaTodos() {
        return srv.retornarInsumos(false);
    }

    @GetMapping("/insumo/retornarTodosParaElaborar")
    public ResponseEntity<List<InsumoDTO>> retornarParaElaborar() {
        return ResponseEntity.ok(srv.retornarInsumos(true));
    }

    @DeleteMapping("insumo/eliminar")
    public String eliminar(@RequestParam Long idInsumo) {
        return srv.eliminar(idInsumo);
    }

    @GetMapping("insumo/buscarPorId/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        InsumoDTO dto = srv.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("insumo/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody InsumoDTO i) {
        srv.guardar(i);
        return ResponseEntity.noContent().build();
    }
}
