package com.echoes.mysite.service;

import java.util.List;
import java.util.Map;

public interface CalendarService {
    List<Map<String, String>> getMemorialDays();

    Map<String, Object> getLunar();
}
