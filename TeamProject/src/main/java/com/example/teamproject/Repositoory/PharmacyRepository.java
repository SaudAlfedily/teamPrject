package com.example.teamproject.Repositoory;

import com.example.teamproject.Model.Medicine;
import com.example.teamproject.Model.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy,Integer> {

    Pharmacy findPharmacyById(Integer id);
//    List<Pharmacy> findPharmacyByMedicines(Medicine medicine);

    Pharmacy findPharmacyByName(String name);
}
