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
    private static final String FILE_PATH = "src/foto/";

    public static byte[] getImageBytes(String image) {
        Path path = Paths.get(FILE_PATH, image);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            return null;
        }
    }
}