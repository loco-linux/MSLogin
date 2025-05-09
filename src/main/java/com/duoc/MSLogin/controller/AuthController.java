package com.duoc.MSLogin.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.MSLogin.model.Usuario;
import com.duoc.MSLogin.service.UsuarioService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService servicio;

    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        servicio.registrar(usuario);
        return "Usuario registrado con éxito.";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> datos, HttpSession session) {
        Usuario u = servicio.login(datos.get("email"), datos.get("password"));
        session.setAttribute("usuario", u);
        return "Login exitoso.";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Sesión cerrada.";
    }

    @GetMapping("/check")
    public String checkSession(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            return "Sesión activa: " + usuario.getEmail() + " (Rol: " + usuario.getRol() + ")";
        } else {
            return "Sesión no iniciada.";
    }
}


}

/* Acceso a endpoints restringidos
@GetMapping("/protegido")
public String protegido(HttpSession session) {
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || !usuario.getRol().equals("EMPLEADO")) {
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Acceso restringido");
    }
    return "Contenido para empleados.";
}

*/
