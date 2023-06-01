package com.example.teamproject.Repositoory;

import com.example.teamproject.Model.Medicine;
import com.example.teamproject.Model.Pharmacy;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine,Integer> {

    Medicine findMedicineById(Integer id);
    Medicine findMedicineByName(String name);


    List<Medicine> findMedicineByPharmacy(Pharmacy pharmacy);

    @Modifying
    @Transactional
    @Query("SELECT m from Medicine m where m.expiration_date <?1")
    List<Medicine> findMedicineALLExpiredMedicine(LocalDate date);


    List<Medicine> findMedicinesByName(String name);

    @Modifying
    @Transactional
    @Query("SELECT s from Medicine s where s.stock =?1")
    List<Medicine> findMedicineOutOfStockMedicine(Integer stock);









}
