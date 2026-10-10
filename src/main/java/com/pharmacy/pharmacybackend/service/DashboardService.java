package com.pharmacy.pharmacybackend.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pharmacy.pharmacybackend.repository.CustomerRepository;
import com.pharmacy.pharmacybackend.repository.MedicineRepository;
import com.pharmacy.pharmacybackend.repository.SaleRepository;

@Service
public class DashboardService {

    private final MedicineRepository medicineRepository;
    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;

    public DashboardService(
            MedicineRepository medicineRepository,
            CustomerRepository customerRepository,
            SaleRepository saleRepository) {

        this.medicineRepository = medicineRepository;
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
    }

    public Map<String, Object> getDashboardSummary() {

        Map<String, Object> summary = new HashMap<>();

        long totalMedicines = medicineRepository.count();
        long totalCustomers = customerRepository.count();
        long totalSales = saleRepository.count();

        long lowStockCount =
                medicineRepository.findByQuantityLessThan(10).size();

        long expiredMedicineCount =
                medicineRepository.findByExpiryDateBefore(
                        LocalDate.now()
                ).size();

        summary.put("totalMedicines", totalMedicines);
        summary.put("totalCustomers", totalCustomers);
        summary.put("totalSales", totalSales);
        summary.put("lowStockCount", lowStockCount);
        summary.put("expiredMedicineCount", expiredMedicineCount);

        return summary;
    }
}