package com.example.edubin.service;

import com.example.edubin.dto.request.PurchaseRequest;
import com.example.edubin.enitity.CourseEntity1;
import com.example.edubin.enitity.FinanceEntity1;
import com.example.edubin.enitity.MerchandiseEntity1;
import com.example.edubin.enitity.UserEntity1;
import com.example.edubin.exception.RecordNotFoundException;
import com.example.edubin.repository.CourseRepository;
import com.example.edubin.repository.FinanceRepository;
import com.example.edubin.repository.MerchandiseRepository;
import com.example.edubin.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchasingService {

    private final CourseRepository courseRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final FinanceRepository financeRepository;

    @Transactional(rollbackOn = {UsernameNotFoundException.class, RecordNotFoundException.class})
    public int purchase(int id, PurchaseRequest purchase) {
        CourseEntity1 course = courseRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(
                MessageFormat.format("course = {0} was not found in database",id)
        ));
        UserEntity1 user = userService.findUserEmail(purchase.getEmail());
        FinanceEntity1 financeEntity1 =new FinanceEntity1();
        financeEntity1.setCard(purchase.getCard());
        financeEntity1.setExpiredDate(purchase.getExpiredDate());
        financeEntity1.setUser(user);
        financeEntity1.setCourse(course);
        financeEntity1.setPrice(course.getPrice());
        financeEntity1.setTransactionDate(LocalDate.now());
        financeEntity1.setTransactionTime(LocalTime.now());
        financeRepository.save(financeEntity1);
        List<UserEntity1> clients = course.getTeacher();
        clients.add(user);
        course.setTeacher(clients);
        courseRepository.save(course);
        return user.getId();
    }
    @Transactional(rollbackOn = {UsernameNotFoundException.class, RecordNotFoundException.class})
    public int purchaseBook(int id, PurchaseRequest purchase) {
        MerchandiseEntity1 merchandise = merchandiseRepository.findById(id).orElseThrow(()-> new RecordNotFoundException(
                MessageFormat.format("id = {0} product was not found in database", id)
        ));
        UserEntity1 user = userService.findUserEmail(purchase.getEmail());
        FinanceEntity1 financeEntity1 =new FinanceEntity1();
        financeEntity1.setCard(purchase.getCard());
        financeEntity1.setExpiredDate(purchase.getExpiredDate());
        financeEntity1.setUser(user);
        financeEntity1.setMerchandise(merchandise);
        financeEntity1.setPrice(merchandise.getPrice()*purchase.getCount());
        financeEntity1.setTransactionDate(LocalDate.now());
        financeEntity1.setTransactionTime(LocalTime.now());
        financeRepository.save(financeEntity1);
        List<MerchandiseEntity1> merchandiseList = user.getMerchandiseList();
        merchandiseList.add(merchandise);
        user.setMerchandiseList(merchandiseList);
        userRepository.save(user);
        return user.getId();
    }
}
