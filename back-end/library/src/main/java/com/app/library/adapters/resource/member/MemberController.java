package com.app.library.adapters.resource.member;

import com.app.library.adapters.input.member.MemberRequestDto;
import com.app.library.adapters.mapper.member.MemberDtoMapper;
import com.app.library.adapters.output.member.MemberResponseDto;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberPaginated;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/member")
@SecurityRequirement(name = "Bearer Authentication")
public class MemberController {

    private final MemberUseCase memberUseCase;
    private final MemberDtoMapper memberDtoMapper;

    public MemberController(MemberUseCase memberUseCase, MemberDtoMapper memberDtoMapper) {
        this.memberUseCase = memberUseCase;
        this.memberDtoMapper = memberDtoMapper;
    }

    @GetMapping()
    @Cacheable("members")
    public MemberPaginated getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        if (page < 0) page = 0;
        if (size < 10) size = 10;
        return memberUseCase.getAll(page, size);
    }

    @GetMapping("/{id}")
    @Cacheable("members")
    public MemberResponseDto get(@PathVariable UUID id) {
        return memberDtoMapper.mapToResponse(memberUseCase.get(id));
    }

    @GetMapping("/cpf/{cpf}")
    @Cacheable("members")
    public MemberResponseDto getByCpf(@PathVariable String cpf) {
        return memberDtoMapper.mapToResponse(memberUseCase.getByCpf(cpf));
    }

    @GetMapping("/name/{name}")
    @Cacheable("members")
    public MemberPaginated getByName(@PathVariable String name, @RequestParam("page") int page, @RequestParam("size") int size) {
        return memberUseCase.getByName(name, page, size);
    }

    @PostMapping(value = "/register")
    @CacheEvict(value = "members", allEntries = true)
    public ResponseEntity<MemberResponseDto> register(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        Member created = memberUseCase.create(memberDtoMapper.mapFromRequest(memberRequestDto));
        return ResponseEntity.created(URI.create("/api/member/" + created.getId())).body(memberDtoMapper.mapToResponse(created));
    }

    @PatchMapping(value = "/update/{id}")
    @CacheEvict(value = "members", allEntries = true)
    public ResponseEntity<MemberResponseDto> update(@PathVariable UUID id, @RequestBody @Valid MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberDtoMapper.mapToResponse(memberUseCase.update(id, memberDtoMapper.mapFromRequest(memberRequestDto))));
    }

    @DeleteMapping(value = "/delete/{id}")
    @CacheEvict(value = "members", allEntries = true)
    public ResponseEntity<MemberResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(memberDtoMapper.mapToResponse(memberUseCase.delete(id)));
    }
}
