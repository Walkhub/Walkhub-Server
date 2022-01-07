package com.walkhub.walkhub.global.security.jwt;

import com.walkhub.walkhub.global.exception.ExpiredJwtException;
import com.walkhub.walkhub.global.exception.InvalidJwtException;
import com.walkhub.walkhub.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;

    public String generateAccessToken(String id) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setHeaderParam("typ", "JWT")
                .setSubject(id)
                .claim("type", "access_token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessExp() * 1000))
                .compact();

    }

    public String generateRefreshToken(String id) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .setHeaderParam("typ", "JWT")
                .setSubject(id)
                .claim("type", "refresh_token")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp() * 1000))
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader(jwtProperties.getHeader());
        if(bearer != null && bearer.startsWith(jwtProperties.getPrefix())
                && bearer.length() > jwtProperties.getPrefix().length() + 1)
            return bearer.substring(jwtProperties.getPrefix().length() + 1);
        return null;
    }

    public boolean validateToken(String token) {
        return getTokenBody(token)
                .getExpiration().after(new Date());
    }

    public Authentication authentication(String token) {
        UserDetails userDetails = authDetailsService
                .loadUserByUsername(getTokenSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Claims getTokenBody(String token) {

        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException e) {
            throw ExpiredJwtException.EXCEPTION;
        }catch (Exception e) {
            throw InvalidJwtException.EXCEPTION;
        }
    }

    private String getTokenSubject(String token) {
        return getTokenBody(token).getSubject();
    }

}


