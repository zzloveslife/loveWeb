package com.echoes.mysite.dao;

import com.echoes.mysite.entity.MemorialDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarDao extends JpaRepository<MemorialDayEntity, Long> {
}
