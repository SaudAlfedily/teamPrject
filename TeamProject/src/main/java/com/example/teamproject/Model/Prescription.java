package com.example.teamproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    private String medicineName;
    @NotNull
    private LocalDate expireDate;
    @NotNull
    private Integer doctorId;
    @NotEmpty
    private String doctorName;

    @NotNull(message = "number of piles should not be empty")
    private Integer numberOfPiles;

    @ManyToOne
    @JoinColumn(name = "patient_id",referencedColumnName = "id")
    @JsonIgnore
    private Patient patient;

    @ManyToMany
    @JsonIgnore
    List<Pharmacy> pharmacy;


}
