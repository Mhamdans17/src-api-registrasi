package com.api.registrasi.config;

import com.api.registrasi.service.AuthService;
import com.api.registrasi.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();
        if (requestURI.equals("/login") || requestURI.equals("/request/user")) {
            chain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7); // Mengambil token setelah "Bearer "
        }

        if (token != null) {
            // Ekstrak username dan roles dari token
            String username = JwtUtil.extractUsername(token);
            Claims claims = JwtUtil.extractClaims(token);

            // Mengambil roles dari claim dan mengonversinya menjadi Set
            Set<String> roles = null;
            Object rolesObj = claims.get("roles");

            if (rolesObj instanceof java.util.ArrayList) {
                // Mengonversi ArrayList ke Set
                roles = new HashSet<>((java.util.ArrayList<String>) rolesObj);
            } else {
                // Jika roles sudah berupa Set
                roles = (Set<String>) rolesObj;
            }

            // Menyiapkan authorities berdasarkan roles dari token
            Set<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Menambahkan "ROLE_" jika perlu
                    .collect(Collectors.toSet());

            // Validasi token dengan username yang diekstrak
            if (authService.validateToken(token, username)) {
                // Menyusun objek Authentication dengan username dan roles
                User principal = new User(username, "", authorities);

                // Menyimpan Authentication ke dalam SecurityContext
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(principal, token, authorities));
                chain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization header is missing or malformed");
        }
    }
}