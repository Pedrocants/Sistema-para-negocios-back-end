package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.container.PrecioDTO;
import ar.com.chepps.Companny.entity.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    List<Insumo> findByEliminadoFalse();
    List<Insumo> findByEliminadoFalseAndEsParaElaborarTrue();

    @Query("""
            SELECT p.idInsumo as id,
                   p.precio as precio
            FROM Insumo p
            WHERE p.idInsumo IN :ids
            """)
    List<PrecioDTO> buscarPrecios(List<Long> ids);
}
