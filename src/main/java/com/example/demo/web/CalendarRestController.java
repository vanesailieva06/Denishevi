package com.example.demo.web;


import com.example.demo.model.dto.CalendarViewDto;
import com.example.demo.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalendarRestController {
    private final CalendarService calendarService;

    public CalendarRestController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/api/calendar/{month}/{year}")
    public ResponseEntity<List<CalendarViewDto>> getDates(@PathVariable Integer month, @PathVariable Integer year){
        return ResponseEntity.ok(calendarService.getDates(month, year));
    }
}
