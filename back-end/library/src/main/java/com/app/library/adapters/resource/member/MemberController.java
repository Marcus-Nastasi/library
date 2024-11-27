package com.app.library.adapters.resource.member;

import com.app.library.adapters.input.member.MemberRequestDto;
import com.app.library.adapters.mapper.member.MemberDtoMapper;
import com.app.library.adapters.output.member.MemberResponseDto;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.member.Member;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/member")
public class MemberController {
    private final MemberUseCase memberUseCase;
    private final MemberDtoMapper memberDtoMapper;

    public MemberController(MemberUseCase memberUseCase, MemberDtoMapper memberDtoMapper) {
        this.memberUseCase = memberUseCase;
        this.memberDtoMapper = memberDtoMapper;
    }

    @GetMapping()
    public ResponseEntity<List<MemberResponseDto>> getAll() {
        return ResponseEntity
            .ok(memberUseCase.getAll().stream().map(memberDtoMapper::mapToResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(memberDtoMapper.mapToResponse(memberUseCase.get(id)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        Member created = memberUseCase.create(memberDtoMapper.mapFromRequest(memberRequestDto));
        return ResponseEntity
            .created(URI.create("/api/book/" + created.getId()))
            .body(memberDtoMapper.mapToResponse(created));
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<MemberResponseDto> update(
            @PathVariable UUID id,
            @RequestBody @Valid MemberRequestDto memberRequestDto
    ) {
        return ResponseEntity
            .ok(memberDtoMapper.mapToResponse(memberUseCase.update(id, memberDtoMapper.mapFromRequest(memberRequestDto)))
        );
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<MemberResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(memberDtoMapper.mapToResponse(memberUseCase.delete(id)));
    }
}