package ar.com.chepps.Companny.controller;

import ar.com.chepps.Companny.container.OrdenDTO;
import ar.com.chepps.Companny.container.OrdenDetalleDTO;
import ar.com.chepps.Companny.container.PaginacionDTO;
import ar.com.chepps.Companny.container.SumaOrdenesDTO;
import ar.com.chepps.Companny.service.IOrdenService;
import ar.com.chepps.Companny.service.OrdenClienteProjection;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/")
public class OrdenController {
    @Autowired
    private IOrdenService srv;

    @GetMapping("/orden/mostrarTodas")
    public ResponseEntity<PaginacionDTO<OrdenDetalleDTO>> mostrarOrdenes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(srv.mostrarOrdenes(null, page, size));
    }

    @PostMapping("/orden/guardar")
    public ResponseEntity<?> guardar(@RequestBody OrdenDTO or) {
        URI location;
        OrdenDetalleDTO oddto;
        try {
            oddto = srv.agregarOrden(or, false);

            location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/orden/buscarPorId?idOrden=")
                    .buildAndExpand(oddto.getIdOrden())
                    .toUri();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
        return ResponseEntity.created(location).body(oddto);
    }

    @PutMapping("/orden/update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> actualizar(@RequestBody OrdenDTO or) {
        srv.agregarOrden(or, true);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/orden/buscarPorId")
    @ResponseStatus(HttpStatus.OK)
    public OrdenDTO buscar(@RequestParam Long idOrden) {
        try {

            return srv.buscarPorId(idOrden);
        } catch (EntityNotFoundException e) {
            System.err.println("Error al buscar una orden.");
        }
        return null;
    }

    @GetMapping("/orden/sumarOrdenes")
    @ResponseStatus(HttpStatus.OK)
    public SumaOrdenesDTO suma() {
        return srv.sumarOrdenes();
    }

    @DeleteMapping("/orden/eliminar/{idOrden}")
    public ResponseEntity<?> eliminar(@PathVariable Long idOrden) {
        try {
            String mensaje = srv.eliminarOrden(idOrden);
            return ResponseEntity.ok(mensaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la orden con "
                    + "ID " + idOrden);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al " +
                    "eliminar la orden.");
        }
    }

    @GetMapping("/orden/cliente/{idCliente}")
    public OrdenClienteProjection obtenerDatosCliente(@PathVariable Long idCliente) {
        return srv.obtenerDatosPorCliente(idCliente);
    }
}