package com.adarsha.birthday_wisher;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BirthdayRepository 
    extends MongoRepository<Birthday, String> {
        
        List<Birthday> findByMonthAndDay(int month, int day);
}
