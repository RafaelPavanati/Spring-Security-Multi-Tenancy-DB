package com.cicloi.eletronica.security;


import com.cicloi.eletronica.multitenant.TenantContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    public static String token;

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;

    public JwtAuthorizationTokenFilter(JwtTokenService jwtTokenService, UserDetailsService userDetailsService) {
        this.jwtTokenService = jwtTokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");

        String username = null;

        if (requestHeader != null
                && requestHeader.startsWith("Bearer ")) {
            String authToken = requestHeader.substring(7);
            username = jwtTokenService.getUsernameFromToken(authToken);
            token = authToken;
        }

        String tenantId = request.getHeader("tenantId");

        TenantContext.setCurrentTenant(tenantId);

        if (username != null
                && SecurityContextHolder
                .getContext()
                .getAuthentication() == null) {
            UserDetails usuario = userDetailsService.loadUserByUsername(username);
            if (usuario == null) {
                response.setHeader("X-Token", requestHeader.substring(7));
                filterChain.doFilter(request, response);
                return;
            }

            response.setHeader("X-Token", jwtTokenService.generateToken(username));


            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
