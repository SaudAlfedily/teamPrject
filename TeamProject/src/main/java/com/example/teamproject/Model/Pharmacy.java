package com.example.teamproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Pharmacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "Medicine name should not be empty")
    private String name;

    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "Pharmacy Address should not be empty")
    private String address;


    @Column(columnDefinition = "varchar(25) not null")
    @NotEmpty(message = "Pharmacy phone should not be empty")
    private String phone;

    @ManyToMany(mappedBy = "pharmacy")
    private Set<Medicine> medicines;
    @ManyToMany(mappedBy = "pharmacy")
    private Set<Prescription>prescriptions;

}
