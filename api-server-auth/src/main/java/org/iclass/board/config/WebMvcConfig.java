package org.iclass.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS=3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        // .allowedOrigins("http://10.100.0.108:80","http://192.168.0.253:80","http://172.17.0.1:80")
        .allowedOrigins("*")      //모든 ip 허용. 단,토큰인증이 실행되는 상태.
        .allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
        .allowedHeaders("*")
        // .allowCredentials(true)
        .maxAge(MAX_AGE_SECS);
    
    }

}
