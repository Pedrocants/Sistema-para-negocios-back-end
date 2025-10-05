package ar.com.chepps.Companny.service.imp;

import ar.com.chepps.Companny.container.ClienteDTO;
import ar.com.chepps.Companny.dao.ClienteRepository;
import ar.com.chepps.Companny.entity.Cliente;
import ar.com.chepps.Companny.entity.Orden;
import ar.com.chepps.Companny.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class ClienteServiceImp implements IClienteService {

    @Autowired
    private ClienteRepository repocl;
    @Override
    public String agregarCliente(ClienteDTO cl) {
        Cliente c = (Cliente)convertirADTO(cl, false);
       Cliente cliente = repocl.save(c);
        if(cliente != null){
            return "Cliente: "+ c.getNombre()+ " "+ c.getApellido() + " Guardado con exito.";
        }
        return "Puede haber ocurrido un error al guardar el cliente.";
    }

    @Override
    public ClienteDTO retornaCliente(Long idCliente) {
        Cliente c = repocl.findById(idCliente).get();
        if(c != null){
            ClienteDTO cldto = (ClienteDTO) convertirADTO(c, true);
            return cldto;
        }
        return new ClienteDTO();
    }

    @Override
    public ArrayList<ClienteDTO> mostrarTodos() {
        ClienteDTO cdto;
        ArrayList<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
        ArrayList<Cliente> clientes = (ArrayList) repocl.findAll();
        for(Cliente c : clientes){
            ClienteDTO cldto = (ClienteDTO)convertirADTO(c, true);
            clientesDTO.add(cldto);
            cldto = null;
        }
        return clientesDTO;
    }

    @Override
    public ArrayList<Orden> mostrarOrdenes(Long idCliente) {
        return null;
    }
    private Object convertirADTO(Object cobject, boolean adto){
       if(adto){
           Cliente c = (Cliente) cobject;
        if(c != null){
           ClienteDTO cldto = new ClienteDTO();
           cldto.setIdCliente(c.getIdCliente());
           cldto.setNombre(c.getNombre());
           cldto.setApellido(c.getApellido());
           cldto.setTipoCliente(c.getTipoCliente());
           cldto.setEliminado(c.getEliminado());
           return cldto;
       }

       }else {
           Cliente cliente = new Cliente();
           ClienteDTO c = (ClienteDTO)cobject;
           cliente.setIdCliente(c.getIdCliente());
           cliente.setNombre(c.getNombre());
           cliente.setApellido(c.getApellido());
           cliente.setTipoCliente(c.getTipoCliente());
           cliente.setEliminado(c.getEliminado());
           return cliente;
       }
       return new ClienteDTO();

    }
}
