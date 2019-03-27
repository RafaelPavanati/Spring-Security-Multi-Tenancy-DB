package com.cicloi.eletronica.services;

import com.cicloi.eletronica.models.Usuario;
import com.cicloi.eletronica.repositorys.UsuarioRepository;
import com.cicloi.eletronica.security.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.util.Objects;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    public Usuario login(Usuario usuario) {

        Objects.requireNonNull(usuario.getEmail());
        Objects.requireNonNull(usuario.getSenha());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuario.getEmail(), usuario.getSenha())
        );

        Usuario base = usuRepo.findByEmail(usuario.getEmail());

        base.setToken(jwtTokenService.generateToken(base));

        usuario.setToken(base.getToken());
        usuario.setTenant(base.getTenant());
        return usuario;

    }

    public Usuario register(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        Usuario base = usuRepo.save(usuario);
        base.setToken(jwtTokenService.generateToken(base));

        return base;
    }

    public Usuario findByEmail(String email) {
        return usuRepo.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuRepo.findByEmail(username);
    }

    public Usuario loadUser(String username) throws UsernameNotFoundException {
        return usuRepo.findByEmail(username);
    }
    public String saveFoto(Long id, byte[] foto) {

        Usuario usuario = usuRepo.findById(id).get();
        usuario.setFoto(foto);
        usuRepo.save(usuario);

        return Base64Utils.encodeToString(foto);

    }

    public String getFoto(Long id) {

        byte[] foto = usuRepo.findById(id).get().getFoto();

        return foto != null ? Base64Utils.encodeToString(
                foto
        ) : null;
    }


}
