package com.echoes.mysite.dao;

import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagDao extends JpaRepository<TagEntity, Long> {
}
