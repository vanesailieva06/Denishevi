package com.example.demo.service;

import com.example.demo.model.dto.CalendarAddDto;
import com.example.demo.model.dto.CalendarViewDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CalendarService {
    List<CalendarViewDto> getDates(Integer month, Integer year);

    void addDate(CalendarAddDto calendarAddDto, UserDetails userDetails, Integer day, Integer month, Integer year);
}
