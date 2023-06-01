package com.example.teamproject.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PrescriptionUpdateDTO {
    private String medicine_name;
    @NotNull
    private LocalDate expireDate;
    @NotNull
    private Integer doctorId;
    @NotEmpty
    private String doctorName;

    @NotNull
    private Integer numberOfPiles;
}
