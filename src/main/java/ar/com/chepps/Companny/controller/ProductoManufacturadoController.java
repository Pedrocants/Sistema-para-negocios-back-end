package ar.com.chepps.Companny.controller;
import ar.com.chepps.Companny.container.HistorialProductoDTO;
import ar.com.chepps.Companny.container.ProductoDTO;
import ar.com.chepps.Companny.service.IProductoManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class ProductoManufacturadoController {
    @Autowired
    private IProductoManufacturadoService srv;

    @GetMapping(value = "/productos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ProductoDTO> retornaProductos(){
        return (ArrayList)srv.retornaProductos();
    }
    @PostMapping(value = "/guardarProducto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> guardar(@RequestBody ProductoDTO pr){
        ProductoDTO p = srv.agregarProducto(pr);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/buscar/")
                .buildAndExpand(p.getIdProductoManufacturado())
                .toUri();
        return ResponseEntity.created(location).body(p);
    }
    @DeleteMapping("/eliminar/{idProducto}")
    public String eliminar(@PathVariable Long idProducto){
        return srv.eliminar(idProducto);
    }
    @GetMapping(value = "/buscar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<?> buscar(@PathVariable Long id){
        ProductoDTO producto = srv.buscarPorId(id);
        return ResponseEntity.ok(producto);
    }
    @GetMapping(value = "/generarHistorial")
    public ArrayList<HistorialProductoDTO> generarHistorial(@RequestParam Long idProducto){
        return srv.generarHistoorialProductoInsumo(idProducto);
    }
    @PutMapping("/actualizarProducto")
    public ResponseEntity<?> actualizar(@RequestBody ProductoDTO pr) {
        ProductoDTO p = srv.actualizarProducto(pr);
        return ResponseEntity.noContent().build();
    }
}
