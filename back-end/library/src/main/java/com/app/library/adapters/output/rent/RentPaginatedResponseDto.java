package com.app.library.adapters.output.rent;

import java.io.Serializable;
import java.util.List;

public record RentPaginatedResponseDto(
        int page,
        int size,
        int total,
        List<RentResponseDto>data
) implements Serializable {}
