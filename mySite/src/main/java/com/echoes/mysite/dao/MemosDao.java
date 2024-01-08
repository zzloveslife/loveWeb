package com.echoes.mysite.dao;

import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.PhotoBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MemosDao extends JpaRepository<MemosEntity, Long> {
    Page<MemosEntity> findByIsDeleteFalse(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update MemosEntity set isDelete = ?2 where id = ?1")
    void updateIsDeleteById(Long id, boolean isDelete);

    @Transactional
    @Modifying
    @Query("update MemosEntity set isDelete = ?2 where id in ?1")
    void updateIsDeleteByIdBatch(List<Long> id, boolean isDelete);
}
