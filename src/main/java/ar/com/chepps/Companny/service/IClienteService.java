package ar.com.chepps.Companny.service;

import ar.com.chepps.Companny.container.ClienteDTO;
import ar.com.chepps.Companny.entity.Cliente;
import ar.com.chepps.Companny.entity.Orden;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface IClienteService {
    public String agregarCliente(ClienteDTO cl);
    public ClienteDTO retornaCliente(Long idCliente);
    public ArrayList<ClienteDTO> mostrarTodos();
    public ArrayList<Orden> mostrarOrdenes(Long idCliente);
}
