package com.example.demo.service.impl;

import com.example.demo.model.dto.CalendarAddDto;
import com.example.demo.model.dto.CalendarViewDto;
import com.example.demo.model.entity.Calendar;
import com.example.demo.repository.CalendarRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CalendarService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.calendarRepository = calendarRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public List<CalendarViewDto> getDates(Integer month, Integer year) {
        return calendarRepository.findAllByMonthAndYear(month, year)
                .stream().map(calendar -> modelMapper.map(calendar, CalendarViewDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public void addDate(CalendarAddDto calendarAddDto, UserDetails userDetails, Integer day, Integer month, Integer year) {
        Calendar calendar = modelMapper.map(calendarAddDto, Calendar.class);
        calendar.setUser(userRepository.findByUsername(userDetails.getUsername()).orElseThrow());
        calendar.setDay(day);
        calendar.setMonth(month);
        calendar.setYear(year);
        calendarRepository.save(calendar);
    }
}
