package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.entity.ProductoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoDetalleRepository extends JpaRepository<ProductoDetalle, Long> {
}
