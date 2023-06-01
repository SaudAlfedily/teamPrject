package com.example.teamproject.Contoller;

import com.example.teamproject.Model.Medicine;
import com.example.teamproject.Service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/medicine")
public class MedicineController {
    private final MedicineService medicineService;

    @GetMapping("/get")
    public ResponseEntity getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.status(200).body(medicines);
    }


    @PostMapping("/add")
    public ResponseEntity addMedicine(@Valid @RequestBody Medicine medicine) {
        medicineService.addMedicine(medicine);
        return ResponseEntity.status(200).body("Medicine added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMedicine(@Valid @RequestBody Medicine medicine, @Valid @PathVariable Integer id) {
        medicineService.updateMedicine(medicine, id);
        return ResponseEntity.status(200).body("Medicine Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMedicine(@PathVariable @Valid Integer id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.status(200).body("Medicine deleted");

    }
    @GetMapping("/get-stock/{medicineId}")
    public ResponseEntity getStock(@PathVariable@Valid Integer medicineId){

       Integer stock= medicineService.getMedicineStock(medicineId);
        return ResponseEntity.status(200).body(stock);
    }


    @GetMapping("/get-medicine/{medicineName}")
    public ResponseEntity getMedicineDetails(@PathVariable@Valid String medicineName){

        List <Medicine> medicines= medicineService.getMedicineDetails(medicineName);
        return ResponseEntity.status(200).body(medicines);
    }
@GetMapping("/get-expired")
    public ResponseEntity getAllExpiredMedicine(){

        List<Medicine> expiredMedicine=medicineService.getAllExpiredMedicine();
    return ResponseEntity.status(200).body(expiredMedicine);
}

@DeleteMapping("/delete-all-expired")
public ResponseEntity deleteALLExpiredMedicine(){
     int numberOfExpiredMedicine  = medicineService.deleteALLExpiredMedicine();
    return ResponseEntity.status(200).body("ALL Expired Medicine hase been deleted the System delete "+numberOfExpiredMedicine+" Expired Medicine ");
}

@GetMapping("/check-expired/{date}")
public ResponseEntity getMedicationExpiredBeforeDate(@Valid@PathVariable LocalDate date){
        List<Medicine > expiredMedicine = medicineService.getMedicationExpiredBeforeDate(date);
    return ResponseEntity.status(200).body(expiredMedicine);



}

    @GetMapping("/get-out-stock")
    public ResponseEntity getAllOutOFStockMedicine(){

        List<Medicine> outOfStock=medicineService.getAllOutOfStockMedicine();
        return ResponseEntity.status(200).body(outOfStock);
    }


    @DeleteMapping("/delete-all-out-stock")
    public ResponseEntity deleteOutOfStockMedicine(){
        int numberOfExpiredMedicine  = medicineService.deleteOutOfStockMedicine();
        return ResponseEntity.status(200).body("ALL Out of Stock Medicine hase been deleted the System delete "+numberOfExpiredMedicine+" Medicine ");
    }



}
