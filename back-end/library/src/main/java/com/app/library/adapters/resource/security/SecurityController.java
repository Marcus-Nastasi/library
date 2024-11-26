package com.app.library.adapters.resource.security;

import com.app.library.application.usecases.security.AuthUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class SecurityController {
    private final AuthUseCase authUseCase;

    public SecurityController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestParam("cpf") String cpf, @RequestParam("password") String password) {
        return ResponseEntity.ok(authUseCase.login(cpf, password));
    }
}
