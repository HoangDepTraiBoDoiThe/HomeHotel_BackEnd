package dev.hoang.homehotel.authentication;

import dev.hoang.homehotel.user.serivce.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
public class AuthUtils {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(AuthUtils.class);

    @Value("auth.security.token.expirationInMillis")
    private long expirationMillis;
    @Value("auth.security.token.securityKey")
    private String securityKey;

    public SecretKey getSecurityKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey));
    }

    public String generateToken(String userEmail) {
        HashMap<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userEmail).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() * expirationMillis * 1000)).signWith(getSecurityKey(), SignatureAlgorithm.HS256).compact();
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String GetEmailFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSecurityKey()).build().parse(token);
            return true;
        }catch(MalformedJwtException e){
            logger.error("Invalid jwt token : {} ", e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("Expired token : {} ", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("This token is not supported : {} ", e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("No  claims found : {} ", e.getMessage());
        }
        return false;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        Claims claims = Jwts.parserBuilder().setSigningKey(securityKey).build().parseClaimsJws(token).getBody();
        return claimResolver.apply(claims);
    }
}
