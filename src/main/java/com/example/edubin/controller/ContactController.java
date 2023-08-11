package com.example.edubin.controller;

import com.example.edubin.dto.request.ContactRequest;
import com.example.edubin.enitity.ContactEntity1;
import com.example.edubin.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/email/")
@RequiredArgsConstructor
@CrossOrigin
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/sms")
    public void addContactSms(@RequestBody ContactRequest contactEntity){
        contactService.saveSMS(contactEntity);
    }

    @GetMapping("/AllSms")
    public List<ContactEntity1> getContactList(){
        return contactService.getList();
    }

}
