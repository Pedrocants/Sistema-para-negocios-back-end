package ar.com.chepps.Companny.controller;

import ar.com.chepps.Companny.entity.Usuario;
import ar.com.chepps.Companny.service.imp.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserDetailService srv;

    @GetMapping("/user/getUserId")
    public String getId(@RequestParam String userName) {
        return (srv.loadUserByUsername(userName).getUserId() != null) ?
                srv.loadUserByUsername(userName).getUserId().toString() :
                "No encontrado";
    }

    @PostMapping("/user/save")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) {
        return srv.guardar(usuario);

    }
}
