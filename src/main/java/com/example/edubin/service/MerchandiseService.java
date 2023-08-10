package com.example.edubin.service;

import com.example.edubin.dto.request.MerchandiseRequest;
import com.example.edubin.enitity.MerchandiseEntity1;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.MerchandiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;
    private final MediaService mediaService;

    private final String PATH_IMAGE = "src/foto/";

    public void addMerchandise(MerchandiseRequest merchandise) {
        String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(merchandise.getPicture().getOriginalFilename()));
        mediaService.internalWrite(merchandise.getPicture(), Paths.get(PATH_IMAGE + newPictureName));
        MerchandiseEntity1 merchandiseEntity1 = MerchandiseEntity1.builder()
                .picture(newPictureName)
                .price(merchandise.getPrice())
                .definition(merchandise.getDefinition())
                .headline(merchandise.getHeadline())
                .build();
        mediaService.savePicture(merchandise.getPicture(), newPictureName);
        merchandiseRepository.save(merchandiseEntity1);
    }

    public void deleteMerchandise(int id) {
        MerchandiseEntity1 merchandiseEntity1 = merchandiseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} merchandise was not found in database", id)
        ));
        mediaService.deleteExistImage(merchandiseEntity1.getPicture());
        merchandiseRepository.delete(merchandiseEntity1);
    }

    public List<MerchandiseEntity1> getListMerchandise() {
        return merchandiseRepository.findAll();
    }

    public List<MerchandiseEntity1> getPageableListOfMerchandise(int id, int size) {
        PageRequest page = PageRequest.of(id - 1, size, Sort.by("id"));
        return merchandiseRepository.findAll(page).getContent();
    }

    public MerchandiseEntity1 getOneProduct(int id) {
        return merchandiseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} merchandise was not found in database", id)
        ));
    }

    public List<MerchandiseEntity1> getRecommendListOfMerchandise() {
        List<MerchandiseEntity1> allMerchandise = merchandiseRepository.findAll();
        List<MerchandiseEntity1> randomProduct= new ArrayList<>();

        Set<Integer> index= new HashSet<>();

        int size = allMerchandise.size() - 1;
        for (int i = 0; i < 4; i++) {
            int a =(int)(Math.random() * (size-1+1)+1);
            if (!index.add(a)){
                index.add((int)(Math.random() * (size-1+1)+1));
            }
        }
        for (int i : index){
            randomProduct.add(allMerchandise.get(i));
        }
        return randomProduct;
    }

    public void updateMerchandise(int id, MerchandiseRequest merchandiseRequest) {
        MerchandiseEntity1 merchandiseEntity1 = merchandiseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} merchandise was not found in database", id)
        ));
        if (merchandiseRequest.getPicture()!=null){
            mediaService.deleteExistImage(merchandiseEntity1.getPicture());
            String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(merchandiseRequest.getPicture().getOriginalFilename()));
            mediaService.savePicture(merchandiseRequest.getPicture(), newPictureName);
            mediaService.internalWrite(merchandiseRequest.getPicture(), Paths.get(PATH_IMAGE + newPictureName));
            merchandiseEntity1.setPicture(newPictureName);
        }
        if (merchandiseRequest.getDefinition()!=null && !merchandiseRequest.getDefinition().equals("")){
            merchandiseEntity1.setDefinition(merchandiseRequest.getDefinition());
        }
        if (merchandiseRequest.getPrice()!=null){
            merchandiseEntity1.setPrice(merchandiseRequest.getPrice());
        }
        if (merchandiseRequest.getHeadline()!=null && !merchandiseRequest.getHeadline().equals("")){
            merchandiseEntity1.setHeadline(merchandiseRequest.getHeadline());
        }
        merchandiseRepository.save(merchandiseEntity1);
    }
}
