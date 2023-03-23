package com.example.edubin.service;

import com.example.edubin.dto.request.MerchandiseRequest;
import com.example.edubin.enitity.MerchandiseEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.MerchandiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MerchandiseService {

    private final MerchandiseRepository merchandiseRepository;
    private final MediaService mediaService;

    private String PATH_IMAGE = "D:\\EduBin\\edubin\\src\\main\\resources\\static\\images\\";
    public void addMerchandise(MerchandiseRequest merchandise) {
        String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(merchandise.getPicture().getOriginalFilename()));
        mediaService.internalWrite(merchandise.getPicture(), Paths.get(PATH_IMAGE+newPictureName));
        MerchandiseEntity merchandiseEntity=MerchandiseEntity.builder()
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
}
