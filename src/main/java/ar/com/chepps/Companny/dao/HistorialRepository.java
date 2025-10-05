package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.entity.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Long> {
}
