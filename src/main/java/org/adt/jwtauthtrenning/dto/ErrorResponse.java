package org.adt.jwtauthtrenning.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @Schema(description = "Код ошибки", example = "USER_NOT_FOUND")
    private String responseCode;
    @Schema(description = "Описание ошибки", example = "user with email - test@example.com not found")
    private String description;
    @Schema(description = "Время ошибки", example = "2026-02-23T10:15:30", format = "date-time")
    private LocalDateTime time;


}
