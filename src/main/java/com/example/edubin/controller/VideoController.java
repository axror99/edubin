package com.example.edubin.controller;

import com.example.edubin.service.FileUtils;
import com.example.edubin.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/video")
@RequiredArgsConstructor
public class VideoController {


    @GetMapping(
            value = "/{video}",
            produces = {MediaType.IMAGE_PNG_VALUE , MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<?> getImage(
            @PathVariable("video") String video
    ) {
        byte[] imageBytes = FileUtils.getVideoBytes(video);
        assert imageBytes != null;
        ByteArrayResource resource = new ByteArrayResource(imageBytes);
        return ResponseEntity.ok().contentLength(imageBytes.length).body(resource);
    }
}
