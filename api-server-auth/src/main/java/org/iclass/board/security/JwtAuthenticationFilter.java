package org.iclass.board.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("request url :{},{}", request.getRequestURI(),request.getRemoteAddr());
        try{
            String token = parserBearerToken(request);
            log.info("=== JwtAuthenticationFilter is running ===");
            if(token !=null && !token.equalsIgnoreCase("null")){  //대소문자 무시하고 문자열 "null" 아니면
                String userId = tokenProvider.validateToken(token);   //토큰 검증하여 user id 가져오기
                log.info("인증된 User ID : {}", userId);

                //UsernamePasswordAuthenticationToken 만들기 : 사용자에 인증 정보를 저장하고
                // SecurityContext 에 인증된 사용자를 등록한다.
                // 요청을 처리할 때 마다 사용자기 인증정보를 사용한다.
                AbstractAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId, null,
                                        AuthorityUtils.NO_AUTHORITIES);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                securityContext.setAuthentication(authentication);
                SecurityContextHolder.setContext(securityContext);
                //SecurityContextHolder 는 ThreadLocal 에 저장하고 각 스레드마다 하나의 컨텍스를 관리할 수 있도록 한다.

            }
        }catch(Exception e){
            log.error("Could not set user authentication -{}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String parserBearerToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        // 하나 이상의 공백이 아닌 문자열이고 Bearer 로 시작하는가?
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);    //Bearer 공백 이후의 문자열만 추출
        }
        return null;
    }
}
