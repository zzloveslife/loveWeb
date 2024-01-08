package com.echoes.mysite.controller;

import com.echoes.mysite.service.CalendarService;
import com.echoes.mysite.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class CalendarController {

    @Resource
    CalendarService calendarService;

    @GetMapping("/memory")
    @ResponseBody
    public Result Memory() {
        List<Map<String, String>> memorialDays;
        try {
            memorialDays = calendarService.getMemorialDays();
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", memorialDays);
    }

    @GetMapping("/lunar")
    @ResponseBody
    public Result Lunar() {
        Map<String,Object> lunar;
        try {
            lunar = calendarService.getLunar();
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", lunar);
    }
}
