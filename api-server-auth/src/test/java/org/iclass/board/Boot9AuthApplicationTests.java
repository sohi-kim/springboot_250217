package org.iclass.board;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest
@Slf4j
class Boot9AuthApplicationTests {

    @Test
    void contextLoads() {
        String jwtToken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwMTg4OTA4MS04ZTBkLTRiZjctOTBiNy0zMTdhYzk0OTRiMmQiLCJpc3MiOiJvcmcuaWNsYXNzIiwiaWF0IjoxNzI2MzMyNzM2LCJleHAiOjE3MjY0MTkxMzZ9.QWhWRTrvV8IiT8tggqJby5iUumeT4lha8chIeDqPwOqEw1Wu4toyeIDkk6Ghrjg7QkNV-MrXYb_Kpso6oU9oDw";
        // JWT 토큰을 "."으로 분리 (헤더, 페이로드, 서명)
        String[] parts = jwtToken.split("\\.");

        // Base64URL 디코더를 사용하여 디코딩
        Base64.Decoder decoder = Base64.getUrlDecoder();

        // 헤더 디코딩
        String header = new String(decoder.decode(parts[0]), StandardCharsets.UTF_8);
        System.out.println("Header: " + header);

        // 페이로드 디코딩
        String payload = new String(decoder.decode(parts[1]), StandardCharsets.UTF_8);
        System.out.println("Payload: " + payload);

        // 서명 부분은 보통 디코딩하지 않음, 검증용
        String signature = parts[2];
        System.out.println("Signature: " + signature);
    }


    /*
    Header: {"alg":"HS512"}
Payload: {"sub":"01889081-8e0d-4bf7-90b7-317ac9494b2d","iss":"org.iclass","iat":1726332736,"exp":1726419136}
Signature: QWhWRTrvV8IiT8tggqJby5iUumeT4lha8chIeDqPwOqEw1Wu4toyeIDkk6Ghrjg7QkNV-MrXYb_Kpso6oU9oDw
     */
    // 전자서명 값은 iat, exp 등의 날짜가 바뀌므로 토큰이 발행 될때마다 값이 달라짐.
}
