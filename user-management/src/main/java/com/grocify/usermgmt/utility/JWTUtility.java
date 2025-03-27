//package com.grocify.usermgmt.utility;
//
//import com.grocify.commonlibs.dto.UserDTO;
//import io.jsonwebtoken.Jwts;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//
//@Component
//public class JWTUtility {
//
//    public String buildJWT(UserDTO userDTO) {
//        return Jwts.builder()
//                .claim("firstName", userDTO.getFirstName())
//                .setIssuer(userDTO.getEmailId())
//                .setId(String.valueOf(userDTO.getId()))
//                .setIssuedAt(Date.from(Instant.now()))
//                .setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.HOURS)))
//                .compact();
//    }
//
//}

package com.grocify.usermgmt.utility;

import com.grocify.commonlibs.dto.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JWTUtility {

    private static final String SECRET_KEY = "Xq+Tv5Z3jd/ZdF36H9gM9ypzF6j8JkGHTLvxX6zU+bI="; // Ensure it is 256-bit

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String buildJWT(UserDTO userDTO) {
        return Jwts.builder()
                .setSubject(userDTO.getEmailId()) // ✅ Subject should be email/username
                .claim("firstName", userDTO.getFirstName())
                .setId(String.valueOf(userDTO.getId()))
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(3, ChronoUnit.HOURS)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)  // ✅ Sign with HS256
                .compact();
    }
}
