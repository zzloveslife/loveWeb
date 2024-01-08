package com.echoes.mysite.controller;

import com.echoes.mysite.dto.CustomRequestDto;
import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.TodoEntity;
import com.echoes.mysite.service.MemosService;
import com.echoes.mysite.service.TodoService;
import com.echoes.mysite.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/todo")
public class TodoController {
    @Resource
    private TodoService todoService;

    @GetMapping("/list")
    public Result listMessages() {
        List<TodoEntity> allMessages;
        try {
            allMessages = todoService.findAllMessages();
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", allMessages);
    }

    @PostMapping("/saveTodo")
    public Result saveTodo(@RequestBody CustomRequestDto customRequestDto) {
        try {
            todoService.saveTodo(customRequestDto);
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "待办保存成功");
    }

    @PostMapping("/updateTodo")
    public Result updateTodo(@RequestBody CustomRequestDto customRequestDto) {
        try {
            todoService.updateStatus(customRequestDto);
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "已办更新成功");
    }

    @PostMapping("/restoreTodo")
    public Result restoreTodo(@RequestBody CustomRequestDto customRequestDto) {
        try {
            todoService.restoreTodo(customRequestDto);
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "已办更新成功");
    }

    @PostMapping("/deleteMemos")
    public Result deleteMemos(@RequestBody CustomRequestDto customRequestDto) {
        try {
            todoService.deleteTodo(customRequestDto.getId());
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "待办删除成功");
    }

    @PostMapping("/deleteBatch")
    public Result deleteMemosBatch(@RequestBody CustomRequestDto customRequestDto) {
        try {
            todoService.deleteTodoBatch(customRequestDto.getIds());
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "待办删除成功");
    }
}
