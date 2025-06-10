package com.tfggestioncalorias.tfggestioncalorias.config;

import com.tfggestioncalorias.tfggestioncalorias.entity.UserApp;
import com.tfggestioncalorias.tfggestioncalorias.repository.UserAppRepository;
import com.tfggestioncalorias.tfggestioncalorias.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    //Esta clase la usamos para generar los tokens

    private static final String SECRET_KEY = "1234156asdadayudhaiuwyhd9178husdh1dhusadhuwadsdASDWQSUHDUWD&!&!&%";
    private final UserAppRepository userAppRepository;


    //Genera el token pasándole el email del usuario
    public String generateToken(String email){

        UserApp user = userAppRepository
                .findByEmailContaining(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", user.getId())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) //10 horas
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    //Devuelve el email si le pasamos el token
    public String extractEmail(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    //Valida el token
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    //Comprueba que el token no haya expirado
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //Devuelve fecha de expiración
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //Es un métod0 genérico, que devuelve cualquier dato del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //Descifra el token mediante la clave secreta
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
