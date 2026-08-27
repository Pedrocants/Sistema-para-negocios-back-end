package ar.com.chepps.Companny.components;

import ar.com.chepps.Companny.dao.UserRepository;
import ar.com.chepps.Companny.entity.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;


@Component
@Profile("!dev")
public class AuthInterceptor extends OncePerRequestFilter {

    private final UserRepository usuarioRepository;

    public AuthInterceptor(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt && path.startsWith("/user/getUserId")) {
            String sub = jwt.getSubject();
            Usuario existingUser = usuarioRepository.findByNombreUsuario(sub);
            if (Objects.isNull(existingUser)) {
                Usuario newUser = new Usuario();
                newUser.setNombreUsuario(sub);
                newUser.setRole("USER");
                newUser.setClave("");
                usuarioRepository.save(newUser);
            }
        }
        //System.out.println("null");

        filterChain.doFilter(request, response);
    }
}