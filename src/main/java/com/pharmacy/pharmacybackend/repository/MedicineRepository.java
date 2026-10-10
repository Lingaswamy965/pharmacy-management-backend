package com.pharmacy.pharmacybackend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmacy.pharmacybackend.entity.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    List<Medicine> findByQuantityLessThan(Integer quantity);

    List<Medicine> findByExpiryDateBefore(LocalDate date);
}