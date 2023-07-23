package com.wu.ln.util;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.AlgorithmUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Date;

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

    private static final String signPassword = "SDFGjhdsfalshdfHFdsjkdsfds121232131afasdfac";

    private static final long expiration = 1000 * 60 * 60 * 24 * 3;

    private final JWTSigner sign = JWTSignerUtil.createSigner(AlgorithmUtil.getId("HS256"), signPassword.getBytes());

    public String createToken(String userId) {
        return JWT.create().setHeader("alg", "HS256")
                .setHeader("typ", "JWT")
                .setPayload("userId", userId)
                .setJWTId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(sign);
    }

    public boolean verifyToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }
        try {
            JWTValidator jwtValidator = JWTValidator.of(token);
            jwtValidator.validateAlgorithm(sign);
            jwtValidator.validateDate();
            return true;
        } catch (Exception e) {
            logger.error("verifyToken exception: {}", e.getMessage());
        }
        return false;
    }

    public JSONObject parseToken(String token) {
        try {
            boolean verify = JWTUtil.verify(token, signPassword.getBytes());
            if (verify) {
                return JWTUtil.parseToken(token).getPayloads();
            }
        } catch (Exception e) {
            logger.error("parseToken exception: {}", e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        String xwu36 = new JwtUtil().createToken("xwu36");
        System.out.println(xwu36);
    }
}
