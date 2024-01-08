package com.echoes.mysite.dao;

import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface TodoDao extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findByIsDeleteFalseAndHasDoneTrue();

    List<TodoEntity> findByIsDeleteFalseAndHasDoneFalse();

    @Transactional
    @Modifying
    @Query("update TodoEntity set hasDone = ?2, updatedAt = ?3 where id = ?1")
    void updateHasDoneById(Long id, boolean isDelete, Date updatedAt);

    @Transactional
    @Modifying
    @Query("update TodoEntity set isDelete = ?2 where id = ?1")
    void updateIsDeleteById(Long id, boolean isDelete);

    @Transactional
    @Modifying
    @Query("update TodoEntity set isDelete = ?2 where id in ?1")
    void updateIsDeleteByIdBatch(List<Long> id, boolean isDelete);
}
