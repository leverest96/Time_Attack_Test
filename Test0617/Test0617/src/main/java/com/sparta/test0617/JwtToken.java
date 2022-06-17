package com.sparta.test0617;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

import static io.jsonwebtoken.SignatureAlgorithm.*;

public class JwtToken {
    private final String secretKey = "SECRET_KEY";
    private final Long accessExpireTime = 1*60*1000L;
    private final Long refreshExpireTime = 1*60*2000L;

    public String createToken(AuthDto.LoginDto loginDto) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "token");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", loginDto.getEmail());

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + accessExpireTime);

        String jwt = Jwts
                .builder()
                .setHeader(headers)
                .setSubject("user")
                .setExpiration(expiration)
                .signWith(HS256, secretKey)
                .compact();
        return jwt;
    }
    public Map<String, String> createRefreshToken(AuthDTO.LoginDTO loginDTO) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("type", "token");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("email", loginDTO.getEmail());

        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + refreshExpireTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String refreshTokenExpirationAt = simpleDateFormat.format(expiration);

        String jwt = Jwts
                .builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setSubject("user")
                .setExpiration(expiration)
                .signWith(HS256, secretKey)
                .compact();

        Map<String, String> result = new HashMap<>();
        result.put("refreshToken", jwt);
        result.put("refreshTokenExpirationAt", refreshTokenExpirationAt);
        return result;
    }
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("token");
    }

    public boolean validateJwtToken(ServletRequest request, String authToken) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            request.setAttribute("exception", "MalformedJwtException");
        } catch (ExpiredJwtException e) {
            request.setAttribute("exception", "ExpiredJwtException");
        } catch (UnsupportedJwtException e) {
            request.setAttribute("exception", "UnsupportedJwtException");
        } catch (IllegalArgumentException e) {
            request.setAttribute("exception", "IllegalArgumentException");
        }
        return false;
    }
}
