package com.example.edubin.service;

import com.example.edubin.dto.request.ContentRequest;
import com.example.edubin.enitity.ContentEntity;
import com.example.edubin.enitity.MediaContentEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private String pathForImage = "D:\\EduBin\\edubin\\src\\main\\resources\\static\\images\\courseImage\\";
    private String pathForApplication = "D:\\EduBin\\edubin\\src\\main\\resources\\static\\application\\";
    private String pathForVideo = "D:\\EduBin\\edubin\\src\\main\\resources\\static\\video\\";
    private String pathForAudio = "D:\\EduBin\\edubin\\src\\main\\resources\\static\\audio\\";
    private String pathCategory = "D:\\EduBin\\edubin\\src\\main\\resources\\static\\category\\";


    @SneakyThrows
    public String saveMultiPartFile(MultipartFile file, String courseName, String categoryName) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        String randomName = generateRandomName(originalFilename);
        if (contentType!=null && !contentType.equals("")) {
            writeImageToFile(file, randomName, categoryName, courseName);
        } else {
            throw new IOException("File not saved in File !!!");
        }
        return randomName;
    }


    private String generateRandomName(String originalName) {
        String[] split = originalName.split("\\.");
        return UUID.randomUUID() + "." + split[split.length - 1];
    }

    private void writeImageToFile(MultipartFile file, String randomName, String categoryName, String courseName) {
        File videoFolder = new File(pathCategory + categoryName + "\\" + courseName+"\\"+"video");
        File applicationFolder = new File(pathCategory + categoryName + "\\" + courseName+"\\"+"application");
        File imageFolder = new File(pathCategory + categoryName + "\\" + courseName+"\\"+"image");
        if (!videoFolder.exists() && !applicationFolder.exists() && !imageFolder.exists()) {
            videoFolder.mkdirs();
            applicationFolder.mkdirs();
            imageFolder.mkdirs();
            internalWrite(file,Paths.get(imageFolder.getAbsolutePath()+"\\"+randomName));
        } else {
            String contentType = getContentType(file);
            if (contentType.startsWith("video")){
                Path download_Path = Paths.get(pathCategory +"\\"+categoryName+"\\"+courseName+"\\"+"video"+"\\"+randomName);
                internalWrite(file,download_Path);
            }
            if (contentType.startsWith("application")){
                Path download_Path = Paths.get(pathCategory +"\\"+categoryName+"\\"+courseName+"\\"+"application"+"\\"+randomName);
                internalWrite(file,download_Path);
            }
            if (contentType.startsWith("image")){
                Path download_Path = Paths.get(pathCategory +"\\"+categoryName+"\\"+courseName+"\\"+"image"+"\\"+randomName);
                internalWrite(file,download_Path);
            }
        }
    }
    private void internalWrite(MultipartFile file,Path download_Path){
        try {
            Files.copy(file.getInputStream(), download_Path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getBytes(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getContentType(MultipartFile file) {
        return file.getContentType();
    }

    public void createMediaAndSave(ContentEntity savedContent, ContentRequest contentRequest) {
        MediaContentEntity mediaContent = MediaContentEntity.builder()
                .content(savedContent)
                .videoBytes(getBytes(contentRequest.getVideo()))
                .fileBytes(getBytes(contentRequest.getTask()))
                .build();
        saveMedia(mediaContent);
    }

    private void saveMedia(MediaContentEntity mediaContent) {
        mediaRepository.save(mediaContent);
    }

    public void deleteExistFile(String taskName) {
        File existTask = new File(pathCategory, taskName);
        if (existTask.delete()) {
            System.out.println("file was deleted !!!");
        } else {
            System.out.println("file was NOT deleted and check it");
        }
    }

    public MediaContentEntity findMediaById(int id) {
        return mediaRepository.findByContent_Id(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} content_id was not found in database", id)
        ));
    }

    public void updateMedia(MediaContentEntity oldMedia, ContentRequest contentRequest) {
        oldMedia.setFileBytes(getBytes(contentRequest.getTask()));
        oldMedia.setVideoBytes(getBytes(contentRequest.getVideo()));
        saveMedia(oldMedia);
    }

    public void createFolder(String folderName) {
        try {
            Files.createDirectories(Paths.get(pathCategory + folderName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteFolder(String name) {
        try {
            FileUtils.deleteDirectory(new File(pathCategory+name));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
