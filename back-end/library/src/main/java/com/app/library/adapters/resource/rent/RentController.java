package com.app.library.adapters.resource.rent;

import com.app.library.adapters.input.rent.RentRequestDto;
import com.app.library.adapters.mapper.rent.RentDtoMapper;
import com.app.library.adapters.output.rent.RentResponseDto;
import com.app.library.application.usecases.rent.RentUseCase;
import com.app.library.domain.entity.rent.Rent;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/rent")
public class RentController {
    private final RentUseCase rentUseCase;
    private final RentDtoMapper rentDtoMapper;

    public RentController(RentUseCase rentUseCase, RentDtoMapper rentDtoMapper) {
        this.rentUseCase = rentUseCase;
        this.rentDtoMapper = rentDtoMapper;
    }

    @GetMapping()
    public ResponseEntity<List<RentResponseDto>> getAll() {
        return ResponseEntity
                .ok(rentUseCase.getAll().stream().map(rentDtoMapper::mapToResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentResponseDto> get(@PathVariable UUID id) {
        return ResponseEntity.ok(rentDtoMapper.mapToResponse(rentUseCase.get(id)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RentResponseDto> register(@RequestBody @Valid RentRequestDto rentRequestDto) {
        Rent created = rentUseCase.create(rentDtoMapper.mapFromRequest(rentRequestDto));
        return ResponseEntity
            .created(URI.create("/api/rent/" + created.getId()))
            .body(rentDtoMapper.mapToResponse(created));
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<RentResponseDto> update(
            @PathVariable UUID id,
            @RequestBody @Valid RentRequestDto rentRequestDto
    ) {
        return ResponseEntity
            .ok(rentDtoMapper.mapToResponse(rentUseCase.update(id, rentDtoMapper.mapFromRequest(rentRequestDto))));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<RentResponseDto> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(rentDtoMapper.mapToResponse(rentUseCase.delete(id)));
    }
}
