package com.echoes.mysite.service.impl;

import cn.hutool.core.date.ChineseDate;
import com.echoes.mysite.dao.CalendarDao;
import com.nlf.calendar.Lunar;
import com.echoes.mysite.entity.MemorialDayEntity;
import com.echoes.mysite.service.CalendarService;
import com.nlf.calendar.Solar;
import com.nlf.calendar.util.SolarUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CalendarServiceImpl implements CalendarService {

    @Resource
    CalendarDao calendarDao;

    @Override
    public List<Map<String, String>> getMemorialDays() {
        List<Map<String, String>> days = new ArrayList<>();

        List<MemorialDayEntity> memorialDayList = calendarDao.findAll();
        for (MemorialDayEntity memorialDay : memorialDayList) {
            Map<String, String> map = new HashMap<>();
            // 当天年份
            LocalDate today = LocalDate.now();
            if (memorialDay.getIsLunar()) {
                // 阴历
                LocalDate memorialDate = memorialDay.getDate();

                Lunar lunarDate = Lunar.fromYmd(today.getYear(), memorialDate.getMonthValue(), memorialDate.getDayOfMonth());
                Solar solar = lunarDate.getSolar();
                // 转换为阳历
                LocalDate solarDate = LocalDate.of(solar.getYear(), solar.getMonth(), solar.getDay());

                long gap;
                if (today.isAfter(solarDate)) {
                    Lunar nextLunarDate = Lunar.fromYmd(today.getYear() + 1, memorialDate.getMonthValue(), memorialDate.getDayOfMonth());
                    Solar nextSolar = nextLunarDate.getSolar();
                    // 转换为阳历
                    LocalDate nextSolarDate = LocalDate.of(nextSolar.getYear(), nextSolar.getMonth(), nextSolar.getDay());
                    gap = ChronoUnit.DAYS.between(today, nextSolarDate);
                } else {
                    gap = ChronoUnit.DAYS.between(today, solarDate);
                }
                map.put("gap", String.valueOf(gap));
            } else {
                //阳历
                LocalDate memorialDate = memorialDay.getDate();
                if (today.isAfter(memorialDate)) {
                    memorialDate = memorialDate.plusYears(1);
                }
                long gap = ChronoUnit.DAYS.between(today, memorialDate);
                map.put("gap", String.valueOf(gap));
            }
            map.put("desc", String.valueOf(memorialDay.getDescription()));
            map.put("date", String.valueOf(memorialDay.getDate()));
            days.add(map);
        }
        // 根据剩余天数排序
        days.sort(Comparator.comparingInt(m -> Integer.parseInt(m.get("gap"))));
        return days;
    }

    @Override
    public Map<String, Object> getLunar() {
        Map<String, Object> lunar = new HashMap<>();
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        Lunar lunarDate = Lunar.fromYmd(year, month, day);
        Solar solarDate = Solar.fromYmd(year, month, day);

        // 阳历
        lunar.put("solar", LocalDate.now());
        // 阴历
        ChineseDate chineseDate = new ChineseDate(year, month, day);
        lunar.put("lunar", chineseDate.getCyclical() + chineseDate.getChineseZodiac() + "年 " + chineseDate.getChineseMonth() + chineseDate.getChineseDay());
        // 节日
        lunar.put("lunarFestivals", lunarDate.getFestivals());
        // 其他节日
        lunar.put("lunarOtherFestivals", lunarDate.getOtherFestivals());
        // 宜
        lunar.put("yi", lunarDate.getDayYi());
        // 忌
        lunar.put("ji", lunarDate.getDayJi());
        // 月相
        lunar.put("moon", lunarDate.getYueXiang());
        // 节日
        lunar.put("solarFestivals", solarDate.getFestivals());
        // 其他节日
        lunar.put("solarOtherFestivals", solarDate.getOtherFestivals());
        // 当年第几天
        int daysInYear = SolarUtil.getDaysInYear(year, month, day);
        lunar.put("daysInYear", daysInYear);
        // 恋爱多少天
        lunar.put("fallInLove", SolarUtil.getDaysBetween(2023, 5, 28, year, month, day));
        int daysOfYear = SolarUtil.getDaysOfYear(year);
        // 今年已过
        double result = (double) daysInYear / daysOfYear;
        String percent = String.format("%.5f%%", result * 100);
        lunar.put("percent", percent);
        return lunar;
    }
}
