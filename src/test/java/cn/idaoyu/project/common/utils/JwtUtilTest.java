package cn.idaoyu.project.common.utils;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class JwtUtilTest {

    @Test
    void buildJWTToken() throws IOException {
        Map<String, Object> map = Maps.newHashMap();
        map.put("username", "一条秋刀鱼");
        map.put("account", "qchenzexuan");
        String jwt = JwtUtil.buildJwtToken(map, 10);
        System.out.println(jwt);
    }
}
