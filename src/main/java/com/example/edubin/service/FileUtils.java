package com.example.edubin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUtils {
    private static final String FILE_PATH = "src\\main\\resources\\static\\images\\";
    public static boolean saveImage(MultipartFile image, UUID imageUUID,String imgContentType) {
        File file = new File(FILE_PATH + imageUUID + imgContentType);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return false;
            }
        }

        try (FileOutputStream fos = new FileOutputStream(file)){
            fos.write(image.getBytes());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    public static byte[] getImageBytes(String image) {
        Path path = Paths.get(FILE_PATH, image);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            return null;
        }
    }

//    public static void deleteImage(TourEntity tourEntity) {
//        File file = new File(FILE_PATH + tourEntity.getImgUUID() + tourEntity.getImgContentType());
//        file.delete();
//    }
}