package cn.idaoyu.project.common.utils;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class JwtUtilTest {


    @Test
    void buildJwtToken() throws IOException {
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", "一条秋刀鱼");
        String jwt = JwtUtil.buildJwtToken(map, 0);
        System.out.println(jwt);
    }

    @Test
    void checkJwtToken() {
        int num = JwtUtil.checkJwtToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjpbXSwiaWF0IjoxNjcxODE4MDk2LCJleHAiOjE2NzI0MTU5OTksImF1ZCI6IiIsImlzcyI6ImRhb3l1LXNwcmluZy1ib290LXRlbXBsYXRlIiwic3ViIjoiIn0.Cz3t0lR899DyRo26I_A3DG2bogikF2zmYydDGAp6Il0", "username");
        System.out.println(num);
    }
}
