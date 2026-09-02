package com.qtrong.plantcare.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.qtrong.plantcare.dto.request.AuthenticationRequest;
import com.qtrong.plantcare.dto.response.ApiResponse;
import com.qtrong.plantcare.dto.response.AuthenticationResponse;
import com.qtrong.plantcare.exception.AppException;
import com.qtrong.plantcare.exception.ErrorCode;
import com.qtrong.plantcare.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    @Value("${spring.signer-key}")
    protected String SIGNER_KEY;

    public ApiResponse<AuthenticationResponse> authenticate(AuthenticationRequest request){
        if(!userRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        var user = userRepository.findByEmail(request.getEmail());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if(!authenticated){
            throw new AppException(ErrorCode.INVALID_CREDENTIALS);
        }

        var token = generateToken(user.getEmail());

        return ApiResponse.<AuthenticationResponse>builder()
                .result(AuthenticationResponse.builder()
                        .token(token)
                        .build())
                .code(200)
                .build();
    }

    String generateToken(String email){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .issuer("qtrong.plantcare")
                .subject(email)
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY));
            return jwsObject.serialize();
        }
        catch (JOSEException e){
            throw new RuntimeException(e);
        }
    }
}
