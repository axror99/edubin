package com.example.edubin.service;

import com.example.edubin.dto.request.ContentRequest;
import com.example.edubin.enitity.ContentEntity;
import com.example.edubin.enitity.CourseEntity;
import com.example.edubin.enitity.MediaContentEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final CourseService courseService;
    private final ContentRepository contentRepository;
    private final MediaService mediaService;

    public void saveContent(ContentRequest content,int id) {
        CourseEntity course = courseService.findCourse(id);
        String videoName = mediaService.saveMultiPartFile(content.getVideo());
        String taskName = mediaService.saveMultiPartFile(content.getTask());
        ContentEntity contentEntity=ContentEntity.builder()
                .title(content.getTitle())
                .definition(content.getDefinition())
                .course(course)
                .videoName(videoName)
                .taskName(taskName)
                .build();
        ContentEntity savedContent = contentRepository.save(contentEntity);
        mediaService.createMediaAndSave(savedContent,content);
    }

    public void updateContent(int id, ContentRequest contentRequest) {
        ContentEntity content = contentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
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
        MediaContentEntity oldMedia = mediaService.findMediaById(content.getId());
        mediaService.updateMedia(oldMedia,contentRequest);
    }

    public void deleteContent(int id) {
        ContentEntity contentEntity = contentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} content was not found in database", id)
        ));
        mediaService.deleteExistFile("application/"+ contentEntity.getTaskName());
        mediaService.deleteExistFile("video/"+ contentEntity.getVideoName());
        contentRepository.delete(contentEntity);
    }

    public List<ContentEntity> getContentList() {
        return contentRepository.findAll();
    }
}
