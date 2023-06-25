package com.example.edubin.service;

import com.example.edubin.dto.request.ContentRequest;
import com.example.edubin.enitity.ContentEntity;
import com.example.edubin.enitity.MediaContentEntity;
import com.example.edubin.enitity.MyMedia;
import com.example.edubin.exception.GetBytesException;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.MediaRepository;
import com.example.edubin.repository.MyMediaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MediaService {
    private final MediaRepository mediaRepository;
    private final MyMediaRepository myMediaRepository;
    private final String pathForImage = "src/foto/";
    private ScheduledExecutorService taskScheduler;

    private final String pathCategory = "src/main/resources/static/assets/";
    private String pathForVideo = "src/main/resources/static/assets/video/";

    public String savePicture(MultipartFile picture, String randomName) {

        String originalFileName = picture.getOriginalFilename();
        long size = picture.getSize();
        String contentType = picture.getContentType();
        byte[] bytes;
        try {
            bytes = picture.getBytes();
        } catch (IOException e) {
            throw new GetBytesException(e);
        }
        MyMedia media = new MyMedia();
        media.setName(randomName);
        media.setSize(size);
        media.setBytes(bytes);
        media.setFileOriginalName(originalFileName);
        media.setContentType(contentType);
        myMediaRepository.save(media);
        return randomName;
    }

//    public void updatePictureWithNewWay(MultipartFile picture, String name){
//        Optional<MyMedia> myMedia = myMediaRepository.findByName(name);
//        if (myMedia.isPresent()){
//            MyMedia media = myMedia.get();
//            media.setBytes(getBytes(picture));
//            media.setSize(picture.getSize());
//            media.setContentType(picture.getContentType());
//            media.setFileOriginalName(picture.getOriginalFilename());
//            media.setName(name);
//            myMediaRepository.save(media);
//        }
//    }

    @SneakyThrows
    public String saveMultiPartFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        String randomName = generateRandomName(originalFilename);

        if (contentType!=null && !contentType.equals("")) {
            writeImageToFile(file, randomName);
        } else {
            throw new IOException("File not saved in File !!!");
        }
        return randomName;
    }


    public String generateRandomName(String originalName) {
        String[] split = originalName.split("\\.");
        return UUID.randomUUID() + "." + split[split.length - 1];
    }

    private void writeImageToFile(MultipartFile file, String randomName) {
            String contentType = getContentType(file);
            if (contentType.startsWith("video")){
                Path download_Path = Paths.get(pathForVideo+randomName);
                internalWrite(file,download_Path);
            }
            if (contentType.startsWith("application")){
            Path download_Path = Paths.get(pathCategory+"application"+"/"+randomName);
            internalWrite(file,download_Path);
            }
            if (contentType.startsWith("image")){
            Path download_Path = Paths.get(pathForImage+randomName);
            internalWrite(file,download_Path);
        }
    }
    public void internalWrite(MultipartFile file,Path download_Path){
        try {
            Files.copy(file.getInputStream(), download_Path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getBytes(MultipartFile file) {
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
    public void deleteExistImage(String imageName) {
        File existImage = new File(pathForImage, imageName);
        if (existImage.delete()) {
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
}
