package com.wu.ln.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    private static volatile JwtUtil instance;

    private JwtUtil() {
    }

    public static JwtUtil getInstance() {
        if (instance == null) {
            synchronized (JwtUtil.class) {
                if (instance == null) {
                    instance = new JwtUtil();
                }
            }
        }
        return instance;
    }

    private static final String signPassword = "#asdfghjkl121380#";

    private static final long expiration = 1000 * 60 * 60 * 24;

    public String createToken(String userId) {
        Map<String, Object> headers = Map.of("alg", "HS256", "typ", "JWT");
        Algorithm algorithm = Algorithm.HMAC256(signPassword);
        return JWT.create().withHeader(headers)
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(algorithm);
    }

    public boolean verifyToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(signPassword)).build().verify(token);
            Date expiration = decodedJWT.getExpiresAt();
            if (expiration.after(new Date())) {
                return true;
            }
        } catch (Exception e) {
            logger.error("verifyToken exception: {}", e.getMessage());
        }
        return false;
    }

    public String getUserId(String token) {
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(signPassword)).build().verify(token);
            return decodedJWT.getClaim("userId").asString();
        } catch (Exception e) {
            logger.error("getUserId exception: {}", e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        String xwu36 = new JwtUtil().createToken("xwu36");
        System.out.println(xwu36);
    }
}
