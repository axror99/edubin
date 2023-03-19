package com.example.edubin.service;

import com.example.edubin.dto.request.ContentRequest;
import com.example.edubin.enitity.ContentEntity;
import com.example.edubin.enitity.MediaContentEntity;
import com.example.edubin.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private  String pathForImage="D:\\EduBin\\edubin\\src\\main\\resources\\static\\images\\courseImage\\";
    private  String pathForApplication="D:\\EduBin\\edubin\\src\\main\\resources\\static\\application\\";
    private  String pathForVideo="D:\\EduBin\\edubin\\src\\main\\resources\\static\\video\\";
    private  String pathForAudio="D:\\EduBin\\edubin\\src\\main\\resources\\static\\audio\\";

    @SneakyThrows
    public String saveMultiPartFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        String randomName=generateRandomName(originalFilename);
        if (contentType.startsWith("application")){
            writeImageToFile(file,randomName,pathForApplication);
        }else if (contentType.startsWith("image")){
            writeImageToFile(file,randomName,pathForImage);
        }else if (contentType.startsWith("audio")){
            writeImageToFile(file,randomName,pathForAudio);
        }else if (contentType.startsWith("video")){
            writeImageToFile(file,randomName,pathForVideo);
        }else {
            throw new IOException("File not saved in File !!!");
        }
        return randomName;
    }


    private String generateRandomName(String originalName){
        String[] split = originalName.split("\\.");
        return UUID.randomUUID()+"."+split[split.length-1];
    }
    private void writeImageToFile(MultipartFile file, String randomName,String path){
        Path download_Path= Paths.get(path+randomName);
        try {
            Files.copy(file.getInputStream(),download_Path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private byte[] getBytes(MultipartFile file){
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveMedia(ContentEntity savedContent, ContentRequest contentRequest) {
        MediaContentEntity mediaContent=MediaContentEntity.builder()
                .content(savedContent)
                .videoBytes(getBytes(contentRequest.getVideo()))
                .fileBytes(getBytes(contentRequest.getTask()))
                .build();
        mediaRepository.save(mediaContent);
    }

    public void deleteExistImage(String image) {
        File existFile= new File(pathForImage+image);
        if (existFile.delete()){
            System.out.println("image was deleted !!!");
        }else {
            System.out.println("image was NOT deleted and check it");
        }

    }
}
