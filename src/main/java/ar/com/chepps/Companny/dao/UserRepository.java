package ar.com.chepps.Companny.dao;

import ar.com.chepps.Companny.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByNombreUsuario(String nombre);

    boolean existsByNombreUsuario(String nombreUsuario);
}
