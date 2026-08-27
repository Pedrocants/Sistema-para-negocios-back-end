package ar.com.chepps.Companny.service;

import ar.com.chepps.Companny.container.HistorialProductoDTO;
import ar.com.chepps.Companny.container.ProductoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface IProductoManufacturadoService {
    public ProductoDTO agregarProducto(ProductoDTO pr);
    public ProductoDTO actualizarProducto(ProductoDTO pr);
    public ArrayList<ProductoDTO> retornaProductos();
    public ProductoDTO buscarPorId(Long idProducto);
    public String eliminar(Long idProducto);
    public ArrayList<HistorialProductoDTO> generarHistoorialProductoInsumo(Long idProducto);

}
