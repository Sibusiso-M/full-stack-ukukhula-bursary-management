package com.ukukhula.bursaryapi.controllers;

import com.ukukhula.bursaryapi.assemblers.UniversityAllocationAssembler;
import com.ukukhula.bursaryapi.entities.UniversityAllocation;
import com.ukukhula.bursaryapi.services.UniversityAllocationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/allocations")
public class UniversityAllocationController {
    @Autowired
    private UniversityAllocationService universityAllocationService;
    private UniversityAllocationAssembler universityAssembler;

    UniversityAllocationController(UniversityAllocationAssembler universityAssembler,
            UniversityAllocationService universityAllocationService) {
        this.universityAssembler = universityAssembler;
        this.universityAllocationService = universityAllocationService;
    }

    @GetMapping("/{id}")
    public EntityModel<UniversityAllocation> getUniversityAllocationById(@PathVariable int id) {

        UniversityAllocation universityAllocation = universityAllocationService.findUniversityAllocationById(id);

        return universityAssembler.toModel(universityAllocation);
    }

    @GetMapping("/all")
    public List<UniversityAllocation> getAllUniversityAllocations() {
        return universityAllocationService.getAllUniversityAllocations();
    }

    @PutMapping("/allocate-to-all")
    public ResponseEntity<String> allocateFundsEvenlyToApproved() {
        Integer updatedRows = universityAllocationService.allocateFundsToAllUniversities();

        if (updatedRows > 0) {
            return ResponseEntity.ok("Funds allocated evenly to approved universities.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to allocate funds.");
        }
    }

    @PostMapping("/addnew")
    public String addNewAllocation(@RequestBody Map<String, Object> allocationDetails) {
        int universityId = Integer.parseInt(allocationDetails.get("universityId").toString());
        BigDecimal amount = new BigDecimal(allocationDetails.get("amount").toString());
        int bursaryDetailsId = Integer.parseInt(allocationDetails.get("bursaryDetailsId").toString());

        try {
            Integer result = universityAllocationService.addNewAllocation(universityId, amount, bursaryDetailsId);
            return "Allocation added successfully. Rows affected: " + result;
        } catch (IllegalStateException e) {
            throw e;
        }
    }

    @GetMapping("/totalspent/{year}")
    public ResponseEntity<Object> getTotalSpentInYear(@PathVariable int year) {
        try {
            BigDecimal totalSpent = universityAllocationService.getTotalSpentInYear(year);
            if (totalSpent == null) {
                throw new RuntimeException("No allocations for that year");
            }
            return ResponseEntity.ok(totalSpent);
        } catch (RuntimeException e) {
            throw e;
        }

    }
}