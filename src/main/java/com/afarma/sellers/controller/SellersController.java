package com.afarma.sellers.controller;

import com.afarma.sellers.models.Sellers;
import com.afarma.sellers.models.SellersDTO;
import com.afarma.sellers.models.SellersResponse;
import com.afarma.sellers.services.SellersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/sellers")

public class SellersController {

    private final SellersService sellersService;

    public SellersController(SellersService sellersService) {
        this.sellersService = sellersService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createSeller(@RequestBody Sellers seller) {
        return sellersService.createSeller(seller);
    }

    @PutMapping("/inactivate")
    public ResponseEntity<Object> inactiveSeller(@RequestParam(required = false) Long id) {
        return sellersService.inactiveSeller(id);
    }

    @PutMapping("/approval")
    public ResponseEntity<Object> sellerApproval(@RequestParam(required = false) Long id) {
        return sellersService.sellerApproval(id);
    }

    @GetMapping("/active-search")
    public Page<Sellers> activeSellers(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable) {
        return sellersService.searchActiveSellers(pageable);
    }

    @GetMapping("/services-products")
    public Page<SellersResponse> searchForServicesAndProducts(@PageableDefault(direction = Sort.Direction.DESC) Pageable pageable,  @RequestBody SellersDTO sellersDTO) {
        return sellersService.searchForServicesAndProducts(pageable,sellersDTO);
    }


}
