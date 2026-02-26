package com.adarsha.birthday_wisher.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.adarsha.birthday_wisher.model.Birthday;

import java.util.List;

public interface BirthdayRepository 
    extends MongoRepository<Birthday, String> {
        
        List<Birthday> findByMonthAndDay(int month, int day);
}
