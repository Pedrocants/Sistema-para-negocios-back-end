package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.entity.ProductoManufacturado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoManufacturadoRepository extends JpaRepository<ProductoManufacturado,
        Long> {
    List<ProductoManufacturado> findByEliminadoFalse();
}
