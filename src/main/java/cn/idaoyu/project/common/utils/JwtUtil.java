package cn.idaoyu.project.common.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

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

    /**
     * 生成 JWT
     *
     * @param payload        jwt payload
     * @param expirationTime 过期时间 单位：小时
     * @return jwt
     */
    public static String buildJwtToken(Map<String, ?> payload, Integer expirationTime) throws IOException {
        String jwtToken = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_HASH_KEY);
            jwtToken = JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(new Date())
                    .withExpiresAt(DateUtil.offset(new Date(), DateField.HOUR, expirationTime))
                    .withPayload(payload)
                    .sign(algorithm);
        } catch (Exception e) {
            throw new IOException();
        }
        return jwtToken;
    }

}
