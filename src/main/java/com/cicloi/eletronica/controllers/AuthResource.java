package com.cicloi.eletronica.controllers;

import com.cicloi.eletronica.models.Usuario;
import com.cicloi.eletronica.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController()
public class AuthResource {

    @Autowired
    private UsuarioService service;

    @PostMapping("api/login")
    public Usuario login(@RequestBody Usuario usuario) {
        return service.login(usuario);
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        return service.register(usuario);
    }

    @GetMapping("/@me")
    public Usuario getMe() {
        return getUsuario();
    }

    @GetMapping("/foto")
    public ResponseEntity<String> getFoto() {
        String fotoBase64 = service.getFoto(getUsuario().getId());
        return fotoBase64 != null ?
                ResponseEntity.ok(fotoBase64) :
                ResponseEntity.notFound().build();
    }

    @PostMapping("/foto")
    public String saveFoto(@RequestParam MultipartFile foto) throws IOException {
        return service.saveFoto(getUsuario().getId(), foto.getBytes());
    }

    private Usuario getUsuario() {
        return (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}

