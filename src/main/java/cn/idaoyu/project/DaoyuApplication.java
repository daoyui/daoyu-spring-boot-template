package cn.idaoyu.project;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 一条秋刀鱼zz
 * @className DaoyuApplication
 * @description Spring Boot 启动类
 * @date 2022/12/10 20:24
 */
@SpringBootApplication
@MapperScan("cn.idaoyu.project.action.*.mapper")
public class DaoyuApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaoyuApplication.class, args);
    }

    /**
     * 加密算法
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
