package com.example.teamproject.Contoller;

import com.example.teamproject.Model.Pharmacy;
import com.example.teamproject.Service.PharmacyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @GetMapping("/get")
    public ResponseEntity getAllPharmacies() {
        List<Pharmacy> pharmacies = pharmacyService.getAllPharmacy();
        return ResponseEntity.status(200).body(pharmacies);
    }


    @PostMapping("/add")
    public ResponseEntity addPharmacy(@Valid @RequestBody Pharmacy pharmacy) {
        pharmacyService.addPharmacy(pharmacy);
        return ResponseEntity.status(200).body("Pharmacy added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatePharmacy(@Valid @RequestBody Pharmacy pharmacy, @Valid @PathVariable Integer id) {
        pharmacyService.updatePharmacy(pharmacy, id);
        return ResponseEntity.status(200).body("Pharmacy Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePharmacy(@PathVariable @Valid Integer id) {
        pharmacyService.deletePharmacy(id);
        return ResponseEntity.status(200).body("Pharmacy deleted");

    }
@PostMapping("/add-to-pharmacy/{pharmacy_id}/{medicine_id}")
    public ResponseEntity addMedicineToPharmacy(@PathVariable @Valid Integer pharmacy_id, @PathVariable @Valid Integer medicine_id){

        pharmacyService.addMedicineToPharmacy(pharmacy_id,medicine_id);
    return ResponseEntity.status(200).body("Medicine Added");
}

@PostMapping("/buy/{prescription_id}")
public ResponseEntity buyMedicine(@Valid@PathVariable Integer prescription_id){
pharmacyService.buyMedicine(prescription_id);
    return ResponseEntity.status(200).body("Buy completed");


}

    @PostMapping("/add-prescription-to-pharmacy/{pharmacy_id}/{prescription_id}")
    public ResponseEntity addPrescriptionToPharmacy(@PathVariable @Valid Integer pharmacy_id, @PathVariable @Valid Integer prescription_id){

        pharmacyService.addPrescriptionToPharmacy(pharmacy_id,prescription_id);
        return ResponseEntity.status(200).body("prescription Added");
    }

}
