package com.example.edubin.controller;

import com.example.edubin.enitity.VideoEntity1;
import com.example.edubin.repository.VideoRepository;
import com.example.edubin.service.StreamingServer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class VideoController {

    private final StreamingServer streamingServer;
    private final VideoRepository videoRepository;
//    private final StreamingServer streamingServer;
//    @GetMapping(value = "/video/{title}" , produces = "video/mp4")
//    public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range){
//        System.out.println("range in bytes() : "+range);
//        return streamingServer.getVideo(title);
//    }
    @GetMapping(value = "/video/{title}" , produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title) {
        VideoEntity1 video = videoRepository.findByTitle(title);
        if (video != null) {
            return streamingServer.getVideo(video.getVideoData());
        } else {
            // Handle video not found case
            // Return an appropriate response or throw an exception
            throw  new RuntimeException("scsdcscdscsc   aaaaaaaaaaaaaaa");
        }
    }

    @PostMapping("/video")
    public void uploadVideo(@RequestParam("title") String title, @RequestParam("file") MultipartFile file) throws IOException, IOException {
        VideoEntity1 video = new VideoEntity1();
        video.setTitle(title);
        video.setVideoData(file.getBytes());
        videoRepository.save(video);
    }
}
