package com.example.edubin.service;

import com.example.edubin.dto.request.MerchandiseRequest;
import com.example.edubin.enitity.MerchandiseEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.MerchandiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private String PATH_IMAGE = "src/main/resources/static/assets/images/";

    public void addMerchandise(MerchandiseRequest merchandise) {
        String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(merchandise.getPicture().getOriginalFilename()));
        mediaService.internalWrite(merchandise.getPicture(), Paths.get(PATH_IMAGE + newPictureName));
        MerchandiseEntity merchandiseEntity = MerchandiseEntity.builder()
                .picture(newPictureName)
                .price(merchandise.getPrice())
                .definition(merchandise.getDefinition())
                .headline(merchandise.getHeadline())
                .build();
        merchandiseRepository.save(merchandiseEntity);
    }

    public void deleteMerchandise(int id) {
        MerchandiseEntity merchandiseEntity = merchandiseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} merchandise was not found in database", id)
        ));
        mediaService.deleteExistImage(merchandiseEntity.getPicture());
        merchandiseRepository.delete(merchandiseEntity);
    }

    public List<MerchandiseEntity> getListMerchandise() {
        return merchandiseRepository.findAll();
    }

    public List<MerchandiseEntity> getPageableListOfMerchandise(int id, int size) {
        PageRequest page = PageRequest.of(id - 1, size, Sort.by("id"));
        return merchandiseRepository.findAll(page).getContent();
    }

    public MerchandiseEntity getOneProduct(int id) {
        return merchandiseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} merchandise was not found in database", id)
        ));
    }

    public List<MerchandiseEntity> getRecommendListOfMerchandise() {
        List<MerchandiseEntity> allMerchandise = merchandiseRepository.findAll();
        List<MerchandiseEntity> randomProduct= new ArrayList<>();

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
}
