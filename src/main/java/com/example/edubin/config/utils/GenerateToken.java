//package com.example.edubin.config.utils;
//
//import com.example.edubin.dto.response.TokenDTO;
//import com.example.edubin.enitity.UserEntity;
//import com.example.edubin.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.stereotype.Component;
//
//import java.text.MessageFormat;
//import java.time.Duration;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class GenerateToken {
//
//    private  final JwtEncoder accessTokenEncoder;
//    private final UserRepository userRepository;
//    private  final JwtEncoder refreshTokenEncoder;
//
//    public GenerateToken(
//            @Qualifier("jwtAccessTokenEncoder") JwtEncoder accessTokenEncoder,
//            @Qualifier("jwtRefreshTokenEncoder") JwtEncoder refreshTokenEncoder,
//            UserRepository userRepository) {
//        this.accessTokenEncoder = accessTokenEncoder;
//        this.refreshTokenEncoder = refreshTokenEncoder;
//        this.userRepository = userRepository;
//    }
//
//    private String createAccessToken(UserEntity userEntity){
//        Instant now = Instant.now();
//
//        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
//                .issuer("eduBin")
//                .issuedAt(now)
//                .expiresAt(now.plus(5, ChronoUnit.MINUTES))
//                .subject(userEntity.getUsername())
//                .claim("authorities",userEntity.getAuthorities())
//                .build();
//        return accessTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
//    }
//
//    private String createRefreshToken(UserEntity userEntity){
//        Instant now = Instant.now();
//        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
//                .issuer("EduBin")
//                .subject(userEntity.getUsername())
//                .issuedAt(now)
//                .expiresAt(now.plus(30,ChronoUnit.DAYS))
//                .claim("authorities",userEntity.getAuthorities())
//                .build();
//        return refreshTokenEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
//    }
//
//    public TokenDTO createToken(Authentication authentication){
//        if (!(authentication.getPrincipal() instanceof UserEntity user)){
//            throw new BadCredentialsException(
//                    MessageFormat.format("principle {0} is not type of UserEntity ",authentication.getAuthorities().getClass())
//            );
//        }
//        TokenDTO tokenDTO=new TokenDTO();
//        tokenDTO.setAccessToken(createAccessToken(user));
//
//        String refreshToken;
//        if (authentication.getCredentials() instanceof Jwt jwt){
//            Instant expiresAtToken = jwt.getExpiresAt();
//            Instant now = Instant.now();
//            Duration duration =Duration.between(now,expiresAtToken);
//            long leftExpiredDay = duration.toDays();
//            if (leftExpiredDay < 7){
//                refreshToken=createRefreshToken(user);
//            }else {
//                refreshToken=jwt.getTokenValue();
//            }
//        }else {
//            refreshToken=createRefreshToken(user);
//        }
//        tokenDTO.setRefreshToken(refreshToken);
//        return tokenDTO;
//    }
//}
