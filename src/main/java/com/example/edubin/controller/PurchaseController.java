package com.example.edubin.controller;

import com.example.edubin.dto.request.PurchaseRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.service.PurchasingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buy/")
@RequiredArgsConstructor
public class PurchaseController {

   private final PurchasingService purchasingService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Integer> purchaseCourse(@PathVariable int id, @RequestBody PurchaseRequest purchase){
        int userId = purchasingService.purchase(id, purchase);
        return new ApiResponse<>("transaction was performed successfully",userId);
    }
}
