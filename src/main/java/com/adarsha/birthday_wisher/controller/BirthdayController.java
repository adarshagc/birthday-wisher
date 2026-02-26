package com.adarsha.birthday_wisher.controller;

import com.adarsha.birthday_wisher.model.Birthday;
import com.adarsha.birthday_wisher.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/birthdays")
public class BirthdayController {
    @Autowired
    private BirthdayService service;

    @PostMapping
    public ResponseEntity<Birthday> addBirthday(@Valid@RequestBody Birthday birthday) {
        return ResponseEntity.ok(service.addBirthday(birthday));
    }

    @GetMapping
    public ResponseEntity<List<Birthday>> getAllBirthdays() {
        return ResponseEntity.ok(service.getAllBirthdays());
    }

    @GetMapping("/today")
    public ResponseEntity<List<Birthday>> getTodayBirthdays() {
        return ResponseEntity.ok(service.getAllBirthdays());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBirthday(@PathVariable String id) {
        service.deleteBirthday(id);
        return ResponseEntity.noContent().build();
    }
}
