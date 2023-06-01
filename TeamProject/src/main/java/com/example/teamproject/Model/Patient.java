package com.example.teamproject.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotNull
    private  Integer age;
    @NotEmpty
    private String gender;
    @NotNull
    private double balance;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "patient")
    private Set<Prescription> prescriptions;


}
