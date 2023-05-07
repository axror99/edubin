package com.example.edubin.controller;

import com.example.edubin.service.StreamingServer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class VideoController {

    private final StreamingServer streamingServer;
    @GetMapping(value = "/video/{title}" , produces = "video/mp4")
    public Mono<Resource> getVideos(@PathVariable String title, @RequestHeader("Range") String range){
        System.out.println("range in bytes() : "+range);
        return streamingServer.getVideo(title);
    }

//    @GetMapping("/{attachmentContentId}")
//    private ResponseEntity getFile(@PathVariable String attachmentContentId) {
//        AttachmentContent attachmentContent = attachmentContentRepository.findById(Long.parseLong(attachmentContentId)).orElseThrow();
//        Attachment attachment = attachmentContent.getAttachment();
//        try {
//            ByteArrayResource resource = new ByteArrayResource(attachmentContent.getData());
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + attachment.getName())
//                    .contentType(MediaType.valueOf(attachment.getContentType()))
//                    .contentLength(attachmentContent.getData().length)
//                    .body(resource);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
}
