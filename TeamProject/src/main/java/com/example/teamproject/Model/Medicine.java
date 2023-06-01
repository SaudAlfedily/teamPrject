package com.example.teamproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "Medicine name should not be empty")
    private String name;


    @NotNull(message = "Medicine price should not be empty")
    @Column(columnDefinition = "int not null")
    @Positive
    private double price;

    @NotNull(message = "Medicine stock should not be empty")
    @Column(columnDefinition = "int not null")
    @PositiveOrZero
    private Integer stock;

    @Column(columnDefinition ="DATE NOT NULL")
    private LocalDate expiration_date;

    @ManyToMany
    @JsonIgnore
    List<Pharmacy> pharmacy;
}
