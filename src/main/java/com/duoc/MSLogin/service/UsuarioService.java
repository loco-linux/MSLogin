package com.duoc.MSLogin.service;

import com.duoc.MSLogin.model.Usuario;
import com.duoc.MSLogin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public Usuario registrar(Usuario usuario) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        return repo.save(usuario);
    }

    public Usuario login(String email, String rawPassword) {
        Usuario u = repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no existe"));
        if (!new BCryptPasswordEncoder().matches(rawPassword, u.getPassword())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        return u;
    }
}
