package com.example.teamproject.Repositoory;


import com.example.teamproject.Model.Medicine;
import com.example.teamproject.Model.Pharmacy;
import com.example.teamproject.Model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Integer> {
    Prescription findPrescriptionById(Integer id);
    List<Prescription> findPrescriptionsByPatient_Id(Integer id);
    Prescription findPrescriptionByDoctorId(Integer id);
    Prescription findPrescriptionByDoctorName(String name);
    List<Prescription> findPrescriptionsByDoctorId(Integer id);
    List<Prescription> findPrescriptionsByDoctorName(String name);

    Prescription findPrescriptionByMedicineName(String name);

    List<Prescription> findPrescriptionByPharmacy(Pharmacy pharmacy);
}
