package com.echoes.mysite.service;

import com.echoes.mysite.dto.CustomRequestDto;
import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.TodoEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TodoService {
    List<TodoEntity> findAllMessages();

    boolean saveTodo(CustomRequestDto customRequestDto);

    boolean updateStatus(CustomRequestDto customRequestDto);

    boolean restoreTodo(CustomRequestDto customRequestDto);

    boolean deleteTodo(Long id);

    boolean deleteTodoBatch(List<Long> ids);
}
