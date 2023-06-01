package com.example.teamproject.Service;

import com.example.teamproject.ApiException.ApiException;

import com.example.teamproject.Model.Medicine;
import com.example.teamproject.Model.Patient;
import com.example.teamproject.Model.Pharmacy;
import com.example.teamproject.Model.Prescription;
import com.example.teamproject.Repositoory.MedicineRepository;
import com.example.teamproject.Repositoory.PatientRepository;
import com.example.teamproject.Repositoory.PharmacyRepository;
import com.example.teamproject.Repositoory.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;

    public List<Pharmacy> getAllPharmacy() {

        List<Pharmacy> pharmacies = pharmacyRepository.findAll();
        return pharmacies;
    }

    public void addPharmacy(Pharmacy pharmacy) {
        pharmacyRepository.save(pharmacy);
    }


    public void updatePharmacy(Pharmacy pharmacy, Integer id) {
        Pharmacy oldPharmacy = pharmacyRepository.findPharmacyById(id);

        if (oldPharmacy == null) {
            throw new ApiException("Pharmacy not found");
        }
        oldPharmacy.setAddress(pharmacy.getAddress());
        oldPharmacy.setName(pharmacy.getName());
        oldPharmacy.setPhone(pharmacy.getPhone());


        pharmacyRepository.save(oldPharmacy);
    }

    public void deletePharmacy(Integer id) {
        Pharmacy pharmacy = pharmacyRepository.findPharmacyById(id);
        if (pharmacy == null) {
            throw new ApiException("Pharmacy not found");
        }
        List<Medicine> medicines = medicineRepository.findMedicineByPharmacy(pharmacy);
        for (Medicine medicine : medicines) {
            medicine.getPharmacy().remove(pharmacy);
            medicineRepository.save(medicine);
        }

        List<Prescription> prescriptions = prescriptionRepository.findPrescriptionByPharmacy(pharmacy);

        for (Prescription prescription : prescriptions) {
            prescription.getPharmacy().remove(pharmacy);
            prescriptionRepository.save(prescription);
        }


        pharmacyRepository.delete(pharmacy);
    }

    public void addMedicineToPharmacy(Integer pharmacy_id, Integer medicine_id) {

        Pharmacy pharmacy = pharmacyRepository.findPharmacyById(pharmacy_id);
        Medicine medicine = medicineRepository.findMedicineById(medicine_id);
        if (pharmacy == null || medicine == null) {

            throw new ApiException("one id or more wrong");


        }


        pharmacy.getMedicines().add(medicine);
        medicine.getPharmacy().add(pharmacy);
        pharmacyRepository.save(pharmacy);
        medicineRepository.save(medicine);


    }

    public void buyMedicine(Integer prescription_id) {
        Prescription prescription = prescriptionRepository.findPrescriptionById(prescription_id);
        List<Medicine> medicines = medicineRepository.findMedicinesByName(prescription.getMedicineName());


        if (prescription == null) {

            throw new ApiException("prescription no found");
        }

        if (medicines.isEmpty()) {

            throw new ApiException("medicine not found");


        }
        for (Medicine medicine : medicines) {
            if (prescription.getPatient().getBalance() > 0 && prescription.getPatient().getBalance() >= ((medicine.getPrice()) * prescription.getNumberOfPiles())) {

                for (Medicine medicine1 : medicines) {
                    if (prescription.getNumberOfPiles() <= medicine1.getStock()) {

                        prescription.getPatient().setBalance(prescription.getPatient().getBalance() - (medicine1.getPrice()) * prescription.getNumberOfPiles());
                        medicine1.setStock(medicine1.getStock() - prescription.getNumberOfPiles());
                        medicineRepository.save(medicine1);
                        prescriptionRepository.save(prescription);
                        prescriptionRepository.delete(prescription);
                        return;


                    }

                }

                throw new ApiException("we do not have enough in stock our stock are " + medicine.getStock());


            }
        }
        throw new ApiException("patient do not have enough money");
    }


    public void addPrescriptionToPharmacy(Integer pharmacy_id, Integer prescription_id) {

        Pharmacy pharmacy = pharmacyRepository.findPharmacyById(pharmacy_id);
        Prescription prescription = prescriptionRepository.findPrescriptionById(prescription_id);
        if (pharmacy == null || prescription == null) {

            throw new ApiException("one id or more wrong");


        }
        pharmacy.getPrescriptions().add(prescription);
        prescription.getPharmacy().add(pharmacy);
        pharmacyRepository.save(pharmacy);
        prescriptionRepository.save(prescription);

    }


}
