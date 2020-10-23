package desaweb.tareaback.services;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class TokenService {

    @Value("${jwt.password}")
    private String jwtSecret;


    public String createToken(){
        Date now = new Date();
        Date expireDate = new Date(now.getTime()+(1000*60*60));
        return Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (UnsupportedJwtException e){
            log.error("JWT in a particular format/configuration that does not match the format");
        }catch (ExpiredJwtException e){
            log.error("JWT was accepted after it expired and must be rejected");
        }catch (MalformedJwtException e){
            log.error("JWT was not currectly constructed and should be rejected");
        }catch (SignatureException e){
            log.error("Signature ir verifying an existing signature of a JWT failed");
        }
        return false;
    }
}
