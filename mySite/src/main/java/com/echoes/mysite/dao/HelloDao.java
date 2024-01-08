package com.echoes.mysite.dao;

import com.echoes.mysite.entity.HelloEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloDao extends JpaRepository<HelloEntity, Long> {
    // 添加自定义查询方法
}

