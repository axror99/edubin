package com.example.edubin.service;

import com.example.edubin.dto.request.ContactRequest;
import com.example.edubin.enitity.ContactEntity;
import com.example.edubin.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    public void saveSMS(ContactRequest request) {

        ContactEntity contactEntity1 = ContactEntity.builder()
                .name(request.getName())
                .email(request.getEmail())
                .text(request.getText())
                .subject(request.getSubject())
                .phone(request.getPhone())
                .checked(false)
                .build();
        contactRepository.save(contactEntity1);
    }

    public List<ContactEntity> getList() {
        return contactRepository.findAll();
    }
}
