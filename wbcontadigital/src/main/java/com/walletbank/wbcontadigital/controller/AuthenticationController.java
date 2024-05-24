package com.walletbank.wbcontadigital.controller;


import com.walletbank.wbcontadigital.domain.*;
import com.walletbank.wbcontadigital.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.walletbank.wbcontadigital.repositories.UserAuthRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    UserAuthRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = TokenService.generateToken((UserAuth) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if(this.repository.findByLogin(data.login()) !=null) return ResponseEntity.badRequest().build();
        String encryptedPassaword = new BCryptPasswordEncoder().encode(data.password());
        UserAuth newUser = new UserAuth(data.login(), encryptedPassaword, data.roles());

        this.repository.save(newUser);
        return ResponseEntity.ok().build();


    }



}
