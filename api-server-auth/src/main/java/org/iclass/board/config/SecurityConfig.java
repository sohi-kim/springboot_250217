package org.iclass.board.config;

import java.util.Arrays;
import java.util.stream.Stream;

import org.iclass.board.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableMethodSecurity(securedEnabled = true,prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig  {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${springdoc.swagger-ui.enabled}")
    private boolean swaggerUiEnabled;
    
    @Value("${springdoc.api-docs.enabled}")
    private boolean apiDocsEnabled;

    //예시
    private static String[] DEFAULT_WHITELIST = {
      "/bookables/**", "/signin","/signup","/upload/**",
      "/checkout/**" 
//            "/users","/bookings","/upload/**","/profile","/auth",
    };

    private  String[] AUTH_WHITELIST=null;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("=== SecurityConfig filterChain ===");
        //CSRF, CORS
//        http.csrf((csrf) -> csrf.disable());
        http.csrf(AbstractHttpConfigurer::disable);    // 토큰 인증을 할것이므로 csrf 토큰은 비활성화
        http.cors(Customizer.withDefaults());           // cors 보안 설정

//        http.httpBasic(Customizer.withDefaults());
        //FormLogin, BasicHttp 비활성화
//        http.formLogin((form) -> form.disable());
        http.formLogin(AbstractHttpConfigurer::disable);    //스프링 시큐리티 자동 로그인화면 비활성화
        http.httpBasic(AbstractHttpConfigurer::disable);    //헤더에 사용자계정과 암호를 보내는 방식

        //세션 관리 상태 없음으로 구성, 기존 아이디와 패스워드 인증으로 세션을 쿠키에 유지하는 방식을 사용하지 않음
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS));

        // 권한 규칙 작성
       
        if (swaggerUiEnabled && apiDocsEnabled) {
                AUTH_WHITELIST =Stream.concat(Arrays.stream(DEFAULT_WHITELIST), 
                Stream.of("/swagger-ui/**","/h2-console/**",
                "/swagger-ui.html",
                "/v3/api-docs/**",      // 중요: API 문서 JSON
                "/v3/api-docs",         // 중요: 루트 API 문서
                "/swagger-resources/**",
                "/swagger-resources",
                "/webjars/**",
                "/configuration/ui",    // Swagger UI 설정
                "/configuration/security")) // Swagger 보안 설정)
                .toArray(String[]::new);
                
        }else {
                AUTH_WHITELIST = DEFAULT_WHITELIST;
        }
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, AUTH_WHITELIST)  //AUTH_WHITELIST 사용도 가능
                        .permitAll()
                         .anyRequest().authenticated()
        );
        log.info("🔥white list:{}", Arrays.toString(AUTH_WHITELIST));

        //  CorsFilter  import 패키지 org.springframework.web.filter.CorsFilter
        http.addFilterBefore(jwtAuthenticationFilter, CorsFilter.class);

        // h2-console
        http.headers(headersConfigurer -> headersConfigurer.frameOptions(
                HeadersConfigurer.FrameOptionsConfig::sameOrigin
        ));
        return http.build();
    }

}
