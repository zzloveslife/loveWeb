package com.echoes.mysite.service.impl;

import com.echoes.mysite.dao.TodoDao;
import com.echoes.mysite.dto.CustomRequestDto;
import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.TodoEntity;
import com.echoes.mysite.service.TodoService;
import com.echoes.mysite.utils.CustomException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    @Resource
    TodoDao todoDao;

    @Override
    public List<TodoEntity> findAllMessages() {
        List<TodoEntity> completedTodos = todoDao.findByIsDeleteFalseAndHasDoneTrue().stream()
                .sorted(Comparator.comparing(TodoEntity::getCreatedAt).reversed()) // 根据 createdAt 字段降序排序
                .toList();

        List<TodoEntity> pendingTodos = todoDao.findByIsDeleteFalseAndHasDoneFalse().stream()
                .sorted(Comparator.comparing(TodoEntity::getUpdatedAt).reversed()) // 根据 updatedAt 字段降序排序
                .toList();

        List<TodoEntity> allTodos = new ArrayList<>(pendingTodos);
        allTodos.addAll(completedTodos);
        return allTodos;
    }

    @Override
    public boolean saveTodo(CustomRequestDto customRequestDto) {
        String content = customRequestDto.getContent();

        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setContent(content);
        todoEntity.setCreatedAt(new Date());
        todoEntity.setUpdatedAt(new Date());
        todoEntity.setIsDelete(false);
        todoEntity.setHasDone(false);
        log.info("todoEntity is: {}", todoEntity);
        todoDao.save(todoEntity);
        return true;
    }

    @Override
    public boolean updateStatus(CustomRequestDto customRequestDto) {
        Long id = customRequestDto.getId();
        log.info("update status: {}", id);
        try {
            todoDao.updateHasDoneById(id, true, new Date());
        } catch (Exception e) {
            throw new CustomException(500, "更新已办失败");
        }
        return true;
    }

    @Override
    public boolean restoreTodo(CustomRequestDto customRequestDto) {
        Long id = customRequestDto.getId();
        log.info("update status: {}", id);
        try {
            todoDao.updateHasDoneById(id, false, new Date());
        } catch (Exception e) {
            throw new CustomException(500, "更新已办失败");
        }
        return true;
    }

    @Override
    public boolean deleteTodo(Long id) {
        log.info("delete todo: {}", id);
        try {
            todoDao.updateIsDeleteById(id, true);
        } catch (Exception e) {
            throw new CustomException(500, "删除待办失败");
        }
        return true;
    }

    @Override
    public boolean deleteTodoBatch(List<Long> ids) {
        log.info("delete todo: {}", ids);
        try {
            todoDao.updateIsDeleteByIdBatch(ids, true);
        } catch (Exception e) {
            throw new CustomException(500, "删除待办失败");
        }
        return true;
    }
}
