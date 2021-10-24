package com.example.raif.service;

import com.example.raif.dto.RequestDto;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public interface SocksService {
    boolean addSocks(@NonNull RequestDto request);
    boolean deleteSocks(@NonNull RequestDto request);
    @Nullable
    Integer getQuantity(@NonNull String color, @NonNull String operation, @NonNull Integer cottonPart);
}
