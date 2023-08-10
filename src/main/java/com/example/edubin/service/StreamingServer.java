package com.example.edubin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StreamingServer {

    private static final String FORMAT="classpath:static/assets/video/%s";

//    private final ResourceLoader resourceLoader;
//
//    public Mono<Resource> getVideo(String title){
//        return Mono.fromSupplier(()-> resourceLoader.getResource(String.format(FORMAT,title)));
//    }

    private final ResourceLoader resourceLoader;

    public Mono<Resource> getVideo(byte[] videoData) {
        return Mono.just(new ByteArrayResource(videoData));
    }
}
