package ar.com.chepps.Companny.service.imp;

import ar.com.chepps.Companny.container.HistorialProductoDTO;
import ar.com.chepps.Companny.container.ProductoDTO;
import ar.com.chepps.Companny.dao.HistorialRepository;
import ar.com.chepps.Companny.dao.InsumoRepository;
import ar.com.chepps.Companny.dao.ProductoManufacturadoRepository;
import ar.com.chepps.Companny.entity.Historial;
import ar.com.chepps.Companny.entity.ProductoManufacturado;
import ar.com.chepps.Companny.helpers.HelperDTO;
import ar.com.chepps.Companny.service.IProductoManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductoManufacturadoServiceImp implements IProductoManufacturadoService {

    @Autowired
    private ProductoManufacturadoRepository repo;
    @Autowired
    private InsumoRepository repoInsumo;
    @Autowired
    private HistorialRepository repoHistorial;

    public ProductoManufacturadoServiceImp() {
    }

    @Transactional
    @Override
    public ProductoDTO agregarProducto(ProductoDTO dto) {
        ProductoManufacturado mnf = (ProductoManufacturado) HelperDTO.productoADTO(dto, false);
        mnf.setHistorial(dto.getHistorial());
        mnf.getHistorial().forEach(historial -> historial.setProducto(mnf));
        int contador = 0;

        for (Historial historial : mnf.getHistorial()) {
            historial.setInsumo(mnf.getInsumos().get(contador));
            contador += 1;
        }
        dto.setHistorial(new ArrayList<>());
        repo.save(mnf);
        return dto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProductoDTO actualizarProducto(ProductoDTO pr) {
        if (!Objects.isNull(pr) && pr.getIdProductoManufacturado() != null) {
            ProductoDTO p = buscarPorId(pr.getIdProductoManufacturado());
            if (!Objects.isNull(p)) {
                ProductoManufacturado producto = (ProductoManufacturado) HelperDTO.productoADTO(pr, false);
                for (Historial h : producto.getHistorial()) {
                    if (h.getIdHistorial() != null) {
                        repoHistorial.save(h);
                    }
                }
                repo.save(producto);
            }
            return pr;

        }
        return new ProductoDTO();
    }

    @Transactional
    @Override
    public ArrayList<ProductoDTO> retornaProductos() {
        ArrayList<ProductoDTO> productosdto = new ArrayList<>();
        ProductoDTO dto;
        List<ProductoManufacturado> p = repo.findByEliminadoFalse();
        for (ProductoManufacturado mnf : p) {
            dto = (ProductoDTO) HelperDTO.productoADTO(mnf, true);
            productosdto.add(dto);
        }
        return productosdto;
    }

    @Override
    public ProductoDTO buscarPorId(Long idProducto) {
        ProductoDTO dto = null;
        ProductoManufacturado mnf = repo.findById(idProducto).orElse(null);

        if (mnf != null) {
            dto = (ProductoDTO) HelperDTO.productoADTO(mnf, true);
        }
        return dto;
    }

    @Override
    public String eliminar(Long idProducto) {
        ProductoManufacturado p = repo.findById(idProducto).orElse(null);
        if (p != null && !p.isEliminado()) {
            p.setEliminado(true);
            return "Producto " + p.getDenominacion() + " Eliminado.";
        }
        return "No encontrado";
    }

    public ArrayList<HistorialProductoDTO> generarHistoorialProductoInsumo(Long idProducto) {
        ProductoManufacturado p = repo.findById(idProducto).orElse(null);
        return HelperDTO.getHistorial(p);
    }
}
