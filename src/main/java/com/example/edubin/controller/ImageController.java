package com.example.edubin.controller;

import com.example.edubin.enitity.MyMedia1;
import com.example.edubin.repository.MyMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {

    private final MyMediaRepository myMediaRepository;

    @GetMapping(
            value = "/{image}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<?> getImage(
            @PathVariable("image") String image
    ) {

        Optional<MyMedia1> media = myMediaRepository.findByName(image);

        byte[] imageBytes = media.get().getBytes();
//        byte[] imageBytes = FileUtils.getImageBytes(image);
        assert imageBytes != null;
        ByteArrayResource resource = new ByteArrayResource(imageBytes);
        return ResponseEntity.ok().contentLength(imageBytes.length).body(resource);
    }
}
