package com.pharmacy.pharmacybackend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pharmacy.pharmacybackend.entity.Medicine;
import com.pharmacy.pharmacybackend.repository.MedicineRepository;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    // Add a new medicine
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // Get all medicines
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    // Get medicine by ID
    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    // Update medicine
    public Medicine updateMedicine(Long id, Medicine medicineDetails) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Medicine not found with id: " + id));

        medicine.setMedicineName(medicineDetails.getMedicineName());
        medicine.setCategory(medicineDetails.getCategory());
        medicine.setBatchNumber(medicineDetails.getBatchNumber());
        medicine.setManufacturer(medicineDetails.getManufacturer());
        medicine.setPrice(medicineDetails.getPrice());
        medicine.setQuantity(medicineDetails.getQuantity());
        medicine.setExpiryDate(medicineDetails.getExpiryDate());

        return medicineRepository.save(medicine);
    }

    // Delete medicine
    public void deleteMedicine(Long id) {
        medicineRepository.deleteById(id);
    }

    // Get low-stock medicines
    public List<Medicine> getLowStockMedicines(Integer threshold) {
        return medicineRepository.findByQuantityLessThan(threshold);
    }

    // Get expired medicines
    public List<Medicine> getExpiredMedicines() {
        return medicineRepository.findByExpiryDateBefore(
                LocalDate.now()
        );
    }
}