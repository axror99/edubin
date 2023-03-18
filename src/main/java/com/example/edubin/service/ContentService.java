package com.example.edubin.service;

import com.example.edubin.dto.request.ContentRequest;
import com.example.edubin.enitity.ContentEntity;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final CourseService courseService;
    private final ContentRepository contentRepository;
    private final MediaService mediaService;

    public void saveContent(ContentRequest content,int id) {
        CourseEntity course = courseService.findCourse(id);
        String taskName = mediaService.saveMultiPartFile(content.getTask());
        String videoName = mediaService.saveMultiPartFile(content.getVideo());
        ContentEntity contentEntity=ContentEntity.builder()
                .title(content.getTitle())
                .definition(content.getDefinition())
                .course(course)
                .videoName(videoName)
                .taskName(taskName)
                .build();
        ContentEntity savedContent = contentRepository.save(contentEntity);
        mediaService.saveMedia(savedContent,content);
    }
}
