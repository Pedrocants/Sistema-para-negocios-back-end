package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.container.PrecioDTO;
import ar.com.chepps.Companny.entity.ProductoManufacturado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoManufacturadoRepository extends JpaRepository<ProductoManufacturado,
        Long> {
    List<ProductoManufacturado> findByEliminadoFalse();

    @Query("""
            SELECT p.idProductoManufacturado as id,
                   p.precio as precio
            FROM ProductoManufacturado p
            WHERE p.idProductoManufacturado IN :ids
            """)
    List<PrecioDTO> buscarPrecios(List<Long> ids);
}
