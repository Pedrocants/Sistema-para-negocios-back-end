package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.entity.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, Long> {
    List<Insumo> findByEliminadoFalse();
    List<Insumo> findByEliminadoFalseAndEsParaElaborarTrue();
}
