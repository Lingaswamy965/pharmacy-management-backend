package com.pharmacy.pharmacybackend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.pharmacybackend.entity.Sale;
import com.pharmacy.pharmacybackend.entity.SaleItem;
import com.pharmacy.pharmacybackend.service.SaleService;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "http://localhost:5173")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    // Get all sales
    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(saleService.getAllSales());
    }

    // Get sale by ID
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getSaleById(id));
    }

    // Get all items for a sale
    @GetMapping("/{id}/items")
    public ResponseEntity<List<SaleItem>> getSaleItems(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                saleService.getSaleItems(id)
        );
    }

    // Create a new sale
    @PostMapping
    public ResponseEntity<Sale> createSale(
            @RequestParam Long customerId,
            @RequestBody List<SaleItem> items) {

        return ResponseEntity.ok(
                saleService.createSale(customerId, items)
        );
    }
}