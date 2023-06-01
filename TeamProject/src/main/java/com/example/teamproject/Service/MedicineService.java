package com.example.teamproject.Service;

import com.example.teamproject.ApiException.ApiException;
import com.example.teamproject.Model.Medicine;
import com.example.teamproject.Repositoory.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public List<Medicine> getAllMedicines() {

        List<Medicine> medicines = medicineRepository.findAll();
        return medicines;
    }

    public void addMedicine(Medicine medicine) {
        medicineRepository.save(medicine);
    }


    public void updateMedicine(Medicine medicine, Integer id) {
        Medicine oldMedicine = medicineRepository.findMedicineById(id);

        if (oldMedicine == null) {
            throw new ApiException("Medicine not found");
        }
        oldMedicine.setName(medicine.getName());
        oldMedicine.setPrice(medicine.getPrice());
        oldMedicine.setStock(medicine.getStock());
        oldMedicine.setExpiration_date(medicine.getExpiration_date());

        medicineRepository.save(oldMedicine);
    }

    public void deleteMedicine(Integer id) {
        Medicine medicine = medicineRepository.findMedicineById(id);
        if (medicine == null) {
            throw new ApiException("Medicine not found");
        }


        medicineRepository.delete(medicine);
    }

    public Integer getMedicineStock(Integer medicine_id){
        Medicine medicine = medicineRepository.findMedicineById(medicine_id);
        if (medicine==null){

            throw new ApiException("Medicine not found");

        }
        if (medicine.getStock()<=0){
            throw new ApiException("Medicine out of stuck");

        }
       return medicine.getStock();

    }

    public List<Medicine> getMedicineDetails(String medicineName){

        List<Medicine> medicines = medicineRepository.findMedicinesByName(medicineName);
        if (medicines.isEmpty()){

            throw new ApiException("Medicine not found");

        }

        return medicines;

    }

    public List<Medicine> getAllExpiredMedicine(){

        List<Medicine> expiredMedication = medicineRepository.findMedicineALLExpiredMedicine(LocalDate.now());

        if(expiredMedication.isEmpty()){

            throw new ApiException("there is no expired Medication");

        }

        return expiredMedication;
    }
public int deleteALLExpiredMedicine(){
        List<Medicine> expiredMedication =getAllExpiredMedicine();

    if(expiredMedication.isEmpty()){

        throw new ApiException("there is no expired Medication");

    }
    int numberOfExpiredMedication=0;
    for (Medicine medicine : expiredMedication){

        medicineRepository.delete(medicine);
        numberOfExpiredMedication++;

    }
    return numberOfExpiredMedication;
}

public List<Medicine>  getMedicationExpiredBeforeDate(LocalDate date){

    List<Medicine> expiredMedication = medicineRepository.findMedicineALLExpiredMedicine(date);

    if(expiredMedication.isEmpty()){

        throw new ApiException("there is no expired Medication");

    }

    return expiredMedication;
}

    public List<Medicine> getAllOutOfStockMedicine(){

        List<Medicine> OutOfStock = medicineRepository.findMedicineOutOfStockMedicine(0);

        if(OutOfStock.isEmpty()){

            throw new ApiException("there is no out of stock Medication");

        }

        return OutOfStock;
    }

    public int deleteOutOfStockMedicine(){
        List<Medicine> outOfStock =getAllOutOfStockMedicine();

        if(outOfStock.isEmpty()){

            throw new ApiException("there is no out of stock Medication");

        }
        int numberOfOutOfStockMedication=0;
        for (Medicine medicine : outOfStock){

            medicineRepository.delete(medicine);
            numberOfOutOfStockMedication++;

        }
        return numberOfOutOfStockMedication;
    }



}
