package com.example.teamproject.Contoller;


import com.example.teamproject.Model.Patient;
import com.example.teamproject.Service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patient")
@RequiredArgsConstructor

public class PatientController {
    private final PatientService patientService;

    @GetMapping("/get")
    public ResponseEntity getAllPatient() {
        return ResponseEntity.status(200).body(patientService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity addPatient(@Valid @RequestBody Patient patient) {
        patientService.addPatient(patient);
        return ResponseEntity.status(200).body("Patient added");


    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePatient(@Valid @RequestBody Patient patient, @PathVariable Integer id) {
        patientService.updatePatient(id, patient);
        return ResponseEntity.status(200).body("patient Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        return ResponseEntity.status(200).body("patient deleted");
    }
    @GetMapping("/get-all-prescriptions/{patient_id}")
    public ResponseEntity getAllPrescriptionsByPatientId(@PathVariable Integer patient_id){
        return ResponseEntity.status(200).body(patientService.getPatientAllPrescriptionsByPatientId(patient_id));
    }
    @GetMapping("/get-prescription-by-id/{prescription_id}")
    public ResponseEntity getPrescriptionById(@PathVariable Integer prescription_id){
        return ResponseEntity.status(200).body(patientService.getPrescriptionById(prescription_id));
    }
    @GetMapping("/get-prescription-by-name/{medicineName}")
    public ResponseEntity getPrescriptionByName(@PathVariable String medicineName){
        return ResponseEntity.status(200).body(patientService.getPrescriptionByName(medicineName));
    }
    @PutMapping("/assign_patient_to_pres/{prescription_id}/{patient_id}")
    public ResponseEntity assignPatientToPrescription(@PathVariable Integer prescription_id,@PathVariable Integer patient_id){
        patientService.assignPrescriptionToPatient(prescription_id, patient_id);
        return ResponseEntity.status(200).body("prescription assigned");
    }
    @GetMapping("/check-medicine-availability/{pharmacy_name}/{medicine_name}")
    public ResponseEntity checkMedicineAvailability(@PathVariable String pharmacy_name,@PathVariable String medicine_name){
        return ResponseEntity.status(200).body(patientService.checkMedicineAvailability(pharmacy_name,medicine_name));
    }
}