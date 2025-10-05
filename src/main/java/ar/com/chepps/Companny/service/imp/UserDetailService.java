package ar.com.chepps.Companny.service.imp;

import ar.com.chepps.Companny.dao.UserRepository;
import ar.com.chepps.Companny.entity.SecurityUser;
import ar.com.chepps.Companny.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository usrRepo;

    public UserDetailService() {
    }

    @Override
    public SecurityUser loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario us = usrRepo.findByNombreUsuario(username);
        if (us == null) {
            //throw new UsernameNotFoundException("Usuario no encontrado");
            return new SecurityUser(new Usuario());
        }
        return new SecurityUser(us);
    }

    public Usuario guardar(Usuario usuario) {
        if (usuario == null) {
            new RuntimeException("Usuario vacio.");
        }
        return usrRepo.save(usuario);
    }
}
