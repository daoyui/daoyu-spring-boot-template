package cn.idaoyu.project.common.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author 一条秋刀鱼zz
 * @className JwtUtil
 * @description JWT 工具类
 * @date 2022/12/13 19:50
 */
public class JwtUtil {

    /**
     * JWT 发布者
     */
    private final static String ISSUER = "daoyu-spring-boot-template";
    /**
     * 签名密钥
     */
    private final static String JWT_HASH_KEY = "c3564fdb-d7b0-4f52-b7b7-591e5217a6e9";
    private final static Algorithm ALGORITHM = Algorithm.HMAC256(JWT_HASH_KEY);

    /**
     * 生成 JWT
     *
     * @param payload        jwt payload
     * @param expirationTime 过期时间 单位：小时
     * @return jwt
     */
    public static String buildJwtToken(Map<String, ?> payload, Integer expirationTime) throws IOException {
        String jwtToken;
        try {
            jwtToken = JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(DateUtil.offset(new Date(), DateField.HOUR, expirationTime))
                    .withPayload(payload)
                    .sign(ALGORITHM);
        } catch (Exception e) {
            throw new IOException();
        }
        return jwtToken;
    }

    /**
     * 检查 jwt token 是否有效、过期
     *
     * @param token jwt token
     * @return 2：token有效且没过期，1：token有效但过期了，0：token无效
     */
    public static int checkJwtToken(String token) {
        try {
            if (null == token) {
                return 0;
            }
            // Reusable verifier instance
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJwt = verifier.verify(token);

            // verity 自定义参数
            String username = decodedJwt.getClaim("username").asString();
            if (StrUtil.isBlank(username)) {
                return 0;
            }
        } catch (TokenExpiredException e) {
            return 1;
        } catch (Exception e) {
            // token 无效
            return 0;
        }
        return 2;
    }

    public static String getUsername(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }
}
