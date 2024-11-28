package com.app.library.adapters.resource.member;

import com.app.library.adapters.input.member.MemberRequestDto;
import com.app.library.adapters.mapper.member.MemberDtoMapper;
import com.app.library.adapters.output.member.MemberResponseDto;
import com.app.library.application.usecases.member.MemberUseCase;
import com.app.library.domain.entity.member.Member;
import com.app.library.domain.entity.member.MemberPaginated;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity<MemberPaginated> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(memberUseCase.getAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(memberDtoMapper.mapToResponse(memberUseCase.get(id)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<MemberResponseDto> register(@RequestBody @Valid MemberRequestDto memberRequestDto) {
        Member created = memberUseCase.create(memberDtoMapper.mapFromRequest(memberRequestDto));
        return ResponseEntity
            .created(URI.create("/api/member/" + created.getId()))
            .body(memberDtoMapper.mapToResponse(created));
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<MemberResponseDto> update(
            @PathVariable UUID id,
            @RequestBody @Valid MemberRequestDto memberRequestDto
    ) {
        return ResponseEntity
            .ok(memberDtoMapper.mapToResponse(memberUseCase.update(id, memberDtoMapper.mapFromRequest(memberRequestDto))));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<MemberResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(memberDtoMapper.mapToResponse(memberUseCase.delete(id)));
    }
}
