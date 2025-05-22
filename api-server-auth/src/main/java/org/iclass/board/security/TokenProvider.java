package org.iclass.board.security;

// User 정보를 받아서 JWT 생성하기

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.model.UsersEntity;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class TokenProvider {

    private static final String  SECRET_KEY ="756be4cf9581add13ddb3ab3e2f1e75f27a0661af1c1225a89ef9a1d44d3f03b";

    // 서버가 클라이언트에서 토큰을 만들어 주는 메소드
    public String generateToken(UsersEntity user) {
//        LocalDateTime oneDayLater = LocalDateTime.now().plusDays(1);
        Date expireDate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));
        log.info("@@@@ expiry date : {}", expireDate);
        // 임의의 문자열로 개인키를 생성해 준다.hmac-Sha  개인키 만드는 알고리즘
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); 
        return Jwts.builder()
                .signWith(secretKey)    // 아래의 정보들을 개인키로 암호화한 전자서명 생성
                .subject(user.getId())  //여기서부터 토큰과 관련된 정보 저장
                .issuer("org.iclass")
                .issuedAt(new Date())
                .expiration(expireDate)
                .compact();


    }

    // 클라이언트가 보낸 토큰(메소드 인자 String token)을 검증하는 메소드
    public String validateToken(String token) {
        // 위의 29번라인과 동일하게 서버의 개인키를 만든다.
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

//     토큰을 분해합니다. {헤더}.{페이로드} 와 {전자서명값} ->  {헤더}.{페이로드} 를 secretKey 로 암호화 -> 이 값을 {전자서명값} 와 비교
//      비교 결과 동일한 값이 아니면 예외 발생.
        Claims claims =
        Jwts.parser()
                .verifyWith(secretKey).build().parseSignedClaims(token).getPayload();


//       유효한 토큰이면 subject 리턴                 // 만료날짜 검사   return !claims.getExpiration().before(new Date());
//       subject 는 32번 라인에서 user의 id를 저장했으므로 토큰 값을 분해해서 얻은 subject 는 user의 id 이다.
         return claims.getSubject();
    }
}
