package com.example.edubin.service;

import com.example.edubin.dto.request.EventRequest;
import com.example.edubin.dto.request.OrderEvent;
import com.example.edubin.enitity.EventEntity;
import com.example.edubin.enitity.UserEntity;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final MediaService mediaService;
    private final UserService userService;
    private final String PATH_IMAGE="src/foto/";

    public void addEvent(EventRequest eventRequest) {
        String pictureName = mediaService.generateRandomName(Objects.requireNonNull(eventRequest.getPicture().getOriginalFilename()));
        mediaService.internalWrite(eventRequest.getPicture(), Paths.get(PATH_IMAGE+pictureName));
        EventEntity eventEntity= EventEntity.builder()
                .about(eventRequest.getAbout())
                .startTime(eventRequest.getStartTime())
                .finishTime(eventRequest.getFinishTime())
                .address(eventRequest.getAddress())
                .headline(eventRequest.getHeadline())
                .date(eventRequest.getDate())
                .picture(pictureName)
                .seats(eventRequest.getSeats())
                .build();
        eventRepository.save(eventEntity);
    }

    public void deleteEvent(int id) {
        EventEntity eventEntity = eventRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} event was Not found in database", id)
        ));
        mediaService.deleteExistImage(eventEntity.getPicture());
        eventRepository.delete(eventEntity);
    }

    public void updateEvent(int id, EventRequest eventRequest) {
        EventEntity eventEntity = eventRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} event was Not found in database", id)
        ));
        if (eventRequest.getHeadline()!=null && !eventRequest.getHeadline().equals(""))
        {
            eventEntity.setHeadline(eventRequest.getHeadline());
        }
        if (eventRequest.getAbout()!=null && !eventRequest.getAbout().equals("")){
            eventEntity.setAbout(eventRequest.getAbout());
        }
        if (eventRequest.getAddress()!=null && !eventRequest.getAddress().equals("")){
            eventEntity.setAddress(eventRequest.getAddress());
        }
        if (eventRequest.getDate()!=null){
            eventEntity.setDate(eventRequest.getDate());
        }
        if (eventRequest.getFinishTime()!=null){
            eventEntity.setFinishTime(eventRequest.getFinishTime());
        }
        if (eventRequest.getStartTime()!=null){
            eventEntity.setStartTime(eventRequest.getStartTime());
        }
        if (eventRequest.getPicture()!=null){
            mediaService.deleteExistImage(eventEntity.getPicture());
            String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(eventRequest.getPicture().getOriginalFilename()));
            mediaService.internalWrite(eventRequest.getPicture(),Paths.get(PATH_IMAGE+newPictureName));
            eventEntity.setPicture(newPictureName);
        }
        if (eventRequest.getSeats()!=null){
            eventEntity.setSeats(eventRequest.getSeats());
        }
        eventRepository.save(eventEntity);
    }

    public EventEntity getEventById(int id) {
        return eventRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} event was Not found in database", id)
        ));
    }

    public List<EventEntity> getList() {
        return eventRepository.findAllByOrderByDate();
    }


}
