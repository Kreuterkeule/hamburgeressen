package com.hamburgeressen.application.controller;

import com.hamburgeressen.application.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
    private TokenService tokenService;
    private JwtDecoder jwtDecoder;

    @Autowired
    public AuthController(TokenService tokenService, JwtDecoder jwtDecoder) {
        this.tokenService = tokenService;
        this.jwtDecoder = jwtDecoder;
    }
    @CrossOrigin
    @GetMapping
    public String hierIstDieRoot() {
        return "hierIstDieRoot";
    }

    record ResponseDto(String token) {}

    @CrossOrigin
    @PostMapping("token")
    public ResponseEntity<ResponseDto> token(HttpServletRequest request, Authentication authentication) {
        System.out.println(request.getHeader("Authorization"));
        LOG.debug("Token requestet for user: '{}'", authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted: {}", token);
        return ResponseEntity.ok(new ResponseDto(token));
    }

    @CrossOrigin
    @GetMapping("testToken")
    public ResponseEntity<String> testToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        System.out.println(token);
        System.out.println(jwtDecoder.decode(token));
        if (jwtDecoder.decode(token).getExpiresAt().isAfter(new Date().toInstant())){
            return ResponseEntity.ok(jwtDecoder.decode(token).getClaims().get("role").toString());
        }
        return ResponseEntity.ok("Token expired");
    }
}
