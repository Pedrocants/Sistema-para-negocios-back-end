package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.entity.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactoRepository extends JpaRepository<Contacto, Long> {
}
