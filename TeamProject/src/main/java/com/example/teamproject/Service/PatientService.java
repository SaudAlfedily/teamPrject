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
public class PatientService {

    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public void addPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public void updatePatient(Integer id, Patient patient) {
        Patient oldPatient = patientRepository.findPatientById(id);
        if (oldPatient == null) {
            throw new ApiException("Patient not found");
        }
        oldPatient.setFirstName(patient.getFirstName());
        oldPatient.setLastName(patient.getLastName());
        oldPatient.setAge(patient.getAge());
        oldPatient.setGender(patient.getGender());
        oldPatient.setBalance(patient.getBalance());
        patientRepository.save(oldPatient);
    }

    public void deletePatient(Integer id) {
        Patient oldPatient = patientRepository.getById(id);
        if (oldPatient == null) {
            throw new ApiException("Patient not found");
        }
        patientRepository.delete(oldPatient);
    }

    public void assignPrescriptionToPatient(Integer prescription_id, Integer patient_id) {
        Patient patient = patientRepository.findPatientById(patient_id);
        Prescription prescription = prescriptionRepository.findPrescriptionById(prescription_id);
        if (prescription == null || patient == null)
            throw new ApiException("id not found");
        if (prescription.getPatient() != null)
            throw new ApiException("prescription already assigned");
        prescription.setPatient(patient);
        prescriptionRepository.save(prescription);
    }

    public List<Prescription> getPatientAllPrescriptionsByPatientId(Integer patient_id) {
        return prescriptionRepository.findPrescriptionsByPatient_Id(patient_id);
    }

    public Prescription getPrescriptionById(Integer prescription_id) {
        return prescriptionRepository.findPrescriptionById(prescription_id);
    }

    public Prescription getPrescriptionByName(String medicine_name) {
        return prescriptionRepository.findPrescriptionByMedicineName(medicine_name);
    }

    public String checkMedicineAvailability(String pharmacy_name, String medicine_name) {
        Pharmacy pharmacy = pharmacyRepository.findPharmacyByName(pharmacy_name);
        List<Medicine> medicines = medicineRepository.findMedicinesByName(medicine_name);
        if (pharmacy == null)
            throw new ApiException("Pharmacy name is incorrect");
        if (medicines.isEmpty())
            throw new ApiException("Medicine name is incorrect");

        for (Medicine medicine : medicines) {
            if (medicine.getStock() > 0) {
                return "Medicine:" + medicine.getName() + " is available, with " + medicine.getStock() + " in stock.";
            }

        }
        return "Medicine stock is unavailable at the moment, please check in another pharmacy.";
}
}


