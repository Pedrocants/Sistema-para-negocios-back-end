package ar.com.chepps.Companny.controller;

import ar.com.chepps.Companny.container.ClienteDTO;
import ar.com.chepps.Companny.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class ClienteController {
    @Autowired
    private IClienteService clsrv;

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(value = "/clientes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<ClienteDTO> mostrarTodos(){
        return clsrv.mostrarTodos();
    }
    @PostMapping("/guardar")
    public String guardar(@RequestBody ClienteDTO c){
        return clsrv.agregarCliente(c);
    }
    @GetMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClienteDTO mostrarPorId(@RequestParam Long idCliente){
        return clsrv.retornaCliente(idCliente);
    }
}
