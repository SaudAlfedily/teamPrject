package com.example.teamproject.Service;

import com.example.teamproject.ApiException.ApiException;
import com.example.teamproject.DTO.PrescriptionExpireDateDTO;
import com.example.teamproject.DTO.PrescriptionUpdateDTO;
import com.example.teamproject.Model.Prescription;
import com.example.teamproject.Repositoory.PatientRepository;
import com.example.teamproject.Repositoory.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PatientRepository patientRepository;

    public List<Prescription> getAll(){
        return prescriptionRepository.findAll();
    }
    public void add(Prescription prescription){
        prescriptionRepository.save(prescription);
    }
    public void update(Integer id, PrescriptionUpdateDTO dto){
        Prescription old = prescriptionRepository.findPrescriptionById(id);
        if (old == null)
            throw new ApiException("id not found");
        old.setExpireDate(dto.getExpireDate());
        old.setMedicineName(dto.getMedicine_name());
        old.setDoctorId(dto.getDoctorId());
        old.setDoctorName(dto.getDoctorName());
        old.setNumberOfPiles(dto.getNumberOfPiles());
        prescriptionRepository.save(old);
    }
    public void delete(Integer id){
        Prescription check = prescriptionRepository.findPrescriptionById(id);
        if (check==null)
            throw new ApiException("prescription not found");
        prescriptionRepository.delete(check);
    }
    public void updateExpirationDate(Integer id, PrescriptionExpireDateDTO dto){
        Prescription prescription = prescriptionRepository.findPrescriptionById(id);
        if (prescription==null)
            throw new ApiException("prescription not found");
        prescription.setExpireDate(dto.getExpiration_date());
        prescriptionRepository.save(prescription);
    }
}
