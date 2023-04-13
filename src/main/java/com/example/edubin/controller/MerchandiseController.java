package com.example.edubin.controller;

import com.example.edubin.dto.request.MerchandiseRequest;
import com.example.edubin.dto.response.ApiResponse;
import com.example.edubin.enitity.MerchandiseEntity;
import com.example.edubin.service.MerchandiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
@RequiredArgsConstructor
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> addMerchandise(@ModelAttribute MerchandiseRequest merchandise){
        merchandiseService.addMerchandise(merchandise);
        return new ApiResponse<>("Merchandise was saved successfully in database");
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<Void> deleteMerchandise(@PathVariable("id") int id){
        merchandiseService.deleteMerchandise(id);
        return new ApiResponse<>("Merchandise was deleted successfully in database");
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<MerchandiseEntity>> getListOfMerchandise(){
        return new ApiResponse<>("Merchandise was taken successfully in database",merchandiseService.getListMerchandise());
    }

    @GetMapping("/list/page/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<List<MerchandiseEntity>> getPageableListOfMerchandise(@PathVariable("id") int id){
        return new ApiResponse<>("pageable Merchandise was taken successfully in database",merchandiseService.getPageableListOfMerchandise(id,7));
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ApiResponse<MerchandiseEntity> getOneProduct(@PathVariable("id") int id){
        return new ApiResponse<>("one Merchandise was taken successfully in database",merchandiseService.getOneProduct(id));
    }
}
