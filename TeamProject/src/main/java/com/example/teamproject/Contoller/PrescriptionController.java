package com.example.teamproject.Contoller;

import com.example.teamproject.DTO.PrescriptionExpireDateDTO;
import com.example.teamproject.DTO.PrescriptionUpdateDTO;
import com.example.teamproject.Model.Prescription;
import com.example.teamproject.Service.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/prescription")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(prescriptionService.getAll());
    }
    @PostMapping("/add")
    public ResponseEntity add(@Valid @RequestBody Prescription prescription){
        prescriptionService.add(prescription);
        return ResponseEntity.status(200).body("prescription added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@Valid @RequestBody PrescriptionUpdateDTO dto){
        prescriptionService.update(id, dto);
        return ResponseEntity.status(200).body("prescription updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        prescriptionService.delete(id);
        return ResponseEntity.status(200).body("prescription added");
    }
    @PutMapping("/update-expiration/{id}")
    public ResponseEntity updateExpirationDate(@PathVariable Integer id, @Valid @RequestBody PrescriptionExpireDateDTO dto){
        prescriptionService.updateExpirationDate(id, dto);
        return ResponseEntity.status(200).body("prescription expiration date updated");
    }
}
