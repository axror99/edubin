package com.example.edubin.service;

import com.example.edubin.dto.request.EventRequest;
import com.example.edubin.enitity.EventEntity1;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final MediaService mediaService;

    private final String PATH_IMAGE="src/foto/";

    public void addEvent(EventRequest eventRequest) {
        String pictureName = mediaService.generateRandomName(Objects.requireNonNull(eventRequest.getPicture().getOriginalFilename()));
//        mediaService.internalWrite(eventRequest.getPicture(), Paths.get(PATH_IMAGE+pictureName));
        EventEntity1 eventEntity1 = EventEntity1.builder()
                .about(eventRequest.getAbout())
                .startTime(eventRequest.getStartTime())
                .finishTime(eventRequest.getFinishTime())
                .address(eventRequest.getAddress())
                .headline(eventRequest.getHeadline())
                .date(eventRequest.getDate())
                .picture(pictureName)
                .seats(eventRequest.getSeats())
                .build();
        mediaService.savePicture(eventRequest.getPicture(),pictureName);
        eventRepository.save(eventEntity1);
    }

    public void deleteEvent(int id) {
        EventEntity1 eventEntity1 = eventRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} event was Not found in database", id)
        ));
        mediaService.deleteExistImage(eventEntity1.getPicture());
        eventRepository.delete(eventEntity1);
    }

    public void updateEvent(int id, EventRequest eventRequest) {
        EventEntity1 eventEntity1 = eventRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} event was Not found in database", id)
        ));
        if (eventRequest.getHeadline()!=null && !eventRequest.getHeadline().equals(""))
        {
            eventEntity1.setHeadline(eventRequest.getHeadline());
        }
        if (eventRequest.getAbout()!=null && !eventRequest.getAbout().equals("")){
            eventEntity1.setAbout(eventRequest.getAbout());
        }
        if (eventRequest.getAddress()!=null && !eventRequest.getAddress().equals("")){
            eventEntity1.setAddress(eventRequest.getAddress());
        }
        if (eventRequest.getDate()!=null){
            eventEntity1.setDate(eventRequest.getDate());
        }
        if (eventRequest.getFinishTime()!=null){
            eventEntity1.setFinishTime(eventRequest.getFinishTime());
        }
        if (eventRequest.getStartTime()!=null){
            eventEntity1.setStartTime(eventRequest.getStartTime());
        }
        if (eventRequest.getPicture()!=null){
            mediaService.deleteExistImage(eventEntity1.getPicture());
            String newPictureName = mediaService.generateRandomName(Objects.requireNonNull(eventRequest.getPicture().getOriginalFilename()));
            mediaService.savePicture(eventRequest.getPicture(),newPictureName);
//            mediaService.internalWrite(eventRequest.getPicture(),Paths.get(PATH_IMAGE+newPictureName));
            eventEntity1.setPicture(newPictureName);
        }
        if (eventRequest.getSeats()!=null){
            eventEntity1.setSeats(eventRequest.getSeats());
        }
        eventRepository.save(eventEntity1);
    }

    public EventEntity1 getEventById(int id) {
        return eventRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(
                MessageFormat.format("id = {0} event was Not found in database", id)
        ));
    }

    public List<EventEntity1> getList() {
        return eventRepository.findAllByOrderByDate();
    }


}
