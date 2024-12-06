package com.app.library.adapters.resource.security;

import com.app.library.adapters.input.security.LoginRequestDto;
import com.app.library.adapters.output.security.LoginResponseDto;
import com.app.library.application.usecases.security.AuthUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/authenticate")
public class SecurityController {
    private final AuthUseCase authUseCase;

    public SecurityController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping()
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(new LoginResponseDto(authUseCase.login(loginRequestDto.cpf(), loginRequestDto.password())));
    }
}
