package com.example.demo.web;

import com.example.demo.model.dto.CalendarAddDto;
import com.example.demo.service.CalendarService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CalendarController {
    private final CalendarService calendarService;
    public static Integer DAY = 0;
    public static Integer MONTH = 0;
    public static Integer YEAR = 0;


    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/calendar")
    public String getCalendar(){
        return "calendar";
    }


    @GetMapping("/calendar/{month}/{day}/{year}")
    public String calendarDetails(@PathVariable Integer day, @PathVariable Integer month, @PathVariable Integer year){
        DAY = day;
        MONTH = month;
        YEAR = year;
        return "calendar-page";
    }

    @PostMapping("/calendar/add")
    public String addDate( @Valid CalendarAddDto calendarAddDto,
                          RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("calendarAddDto", calendarAddDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.calendarAddDto", bindingResult);
            return "redirect:/";
        }
        calendarService.addDate(calendarAddDto, userDetails, DAY, MONTH, YEAR);
        return "calendar";
    }

    @ModelAttribute
    public CalendarAddDto calendarAddDto(){
        return new CalendarAddDto();
    }
}
