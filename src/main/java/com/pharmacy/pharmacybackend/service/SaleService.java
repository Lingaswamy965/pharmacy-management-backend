package com.pharmacy.pharmacybackend.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pharmacy.pharmacybackend.entity.Customer;
import com.pharmacy.pharmacybackend.entity.Medicine;
import com.pharmacy.pharmacybackend.entity.Sale;
import com.pharmacy.pharmacybackend.entity.SaleItem;
import com.pharmacy.pharmacybackend.repository.CustomerRepository;
import com.pharmacy.pharmacybackend.repository.MedicineRepository;
import com.pharmacy.pharmacybackend.repository.SaleItemRepository;
import com.pharmacy.pharmacybackend.repository.SaleRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final CustomerRepository customerRepository;
    private final MedicineRepository medicineRepository;

    public SaleService(
            SaleRepository saleRepository,
            SaleItemRepository saleItemRepository,
            CustomerRepository customerRepository,
            MedicineRepository medicineRepository) {

        this.saleRepository = saleRepository;
        this.saleItemRepository = saleItemRepository;
        this.customerRepository = customerRepository;
        this.medicineRepository = medicineRepository;
    }

    // Get all sales
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    // Get sale by ID
    public Sale getSaleById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sale not found with id: " + id));
    }

    // Create a sale
    public Sale createSale(Long customerId, List<SaleItem> items) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + customerId));

        if (items == null || items.isEmpty()) {
            throw new RuntimeException(
                    "Sale must contain at least one item");
        }

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setSaleDate(LocalDateTime.now());

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SaleItem item : items) {

            if (item.getQuantity() == null || item.getQuantity() <= 0) {
                throw new RuntimeException(
                        "Quantity must be greater than zero");
            }

            if (item.getMedicine() == null
                    || item.getMedicine().getId() == null) {

                throw new RuntimeException(
                        "Medicine ID is required");
            }

            Long medicineId = item.getMedicine().getId();

            Medicine medicine = medicineRepository.findById(medicineId)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Medicine not found with id: "
                                            + medicineId));

            if (medicine.getQuantity() < item.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for medicine: "
                                + medicine.getMedicineName());
            }

            BigDecimal price = medicine.getPrice();

            BigDecimal subtotal = price.multiply(
                    BigDecimal.valueOf(item.getQuantity())
            );

            item.setSale(sale);
            item.setMedicine(medicine);
            item.setPrice(price);
            item.setSubtotal(subtotal);

            totalAmount = totalAmount.add(subtotal);

            // Reduce medicine stock
            medicine.setQuantity(
                    medicine.getQuantity() - item.getQuantity()
            );

            medicineRepository.save(medicine);
        }

        sale.setTotalAmount(totalAmount);

        Sale savedSale = saleRepository.save(sale);

        // Save all sale items
        for (SaleItem item : items) {
            saleItemRepository.save(item);
        }

        return savedSale;
    }

    // Get all items for a sale
    public List<SaleItem> getSaleItems(Long saleId) {
        return saleItemRepository.findBySaleId(saleId);
    }
}