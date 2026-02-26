package com.adarsha.birthday_wisher.service;

import com.adarsha.birthday_wisher.model.Birthday;
import com.adarsha.birthday_wisher.repository.BirthdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BirthdayService {
    
    @Autowired
    private BirthdayRepository repository;

    public Birthday addBirthday(Birthday birthday) {
        return repository.save(birthday);
    }

    public List<Birthday> getAllBirthdays() {
        return repository.findAll();
    }

    public List<Birthday> getTodayBirthdays() {
        LocalDate today = LocalDate.now();
        return repository.findByMonthAndDay(
            today.getMonthValue(),
            today.getDayOfMonth()
        );
    }

    public void deleteBirthday(String id) {
        repository.deleteById(id);
    }
}
