package com.example.edubin.service;

import com.example.edubin.dto.request.ContentRequest;
import com.example.edubin.enitity.ContentEntity1;
import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.enitity.MediaContentEntity1;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final CourseService courseService;
    private final ContentRepository contentRepository;
    private final MediaService mediaService;

    public void saveContent(ContentRequest content,int id) {
        CourseEntity1 course = courseService.findCourse(id);
        String videoName = mediaService.generateRandomName(Objects.requireNonNull(content.getVideo().getOriginalFilename()));
        String taskName = mediaService.generateRandomName(Objects.requireNonNull(content.getTask().getOriginalFilename()));
        ContentEntity1 contentEntity1 = ContentEntity1.builder()
                .title(content.getTitle())
                .definition(content.getDefinition())
                .course(course)
                .videoName(videoName)
                .taskName(taskName)
                .build();
        ContentEntity1 savedContent = contentRepository.save(contentEntity1);
        mediaService.createMediaAndSave(savedContent,content);
    }

    public void updateContent(int id, ContentRequest contentRequest) {
        ContentEntity1 content = contentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} was Not in database ", id)
        ));
        if (!contentRequest.getDefinition().equals("") && contentRequest.getDefinition()!=null){
            content.setDefinition(contentRequest.getDefinition());
        }
        if (!contentRequest.getTitle().equals("") && contentRequest.getTitle()!=null){
            content.setTitle(contentRequest.getTitle());
        }
        if (contentRequest.getVideo()!=null){
            mediaService.deleteExistFile("video/"+content.getVideoName());
            String newVideoName = mediaService.saveMultiPartFile(contentRequest.getVideo());
            content.setVideoName(newVideoName);
        }
        if (contentRequest.getTask()!=null){
            mediaService.deleteExistFile("application/"+content.getTaskName());
            String newFileName = mediaService.saveMultiPartFile(contentRequest.getTask());
            content.setTaskName(newFileName);
        }
        MediaContentEntity1 oldMedia = mediaService.findMediaById(content.getId());
        mediaService.updateMedia(oldMedia,contentRequest);
    }

    public void deleteContent(int id) {
        ContentEntity1 contentEntity1 = contentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} content was not found in database", id)
        ));
        mediaService.deleteExistFile("application/"+ contentEntity1.getTaskName());
        mediaService.deleteExistFile("video/"+ contentEntity1.getVideoName());
        contentRepository.delete(contentEntity1);
    }

    public List<ContentEntity1> getContentList() {
        return contentRepository.findAll();
    }
}
