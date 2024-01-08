package com.echoes.mysite.controller;

import com.echoes.mysite.dto.CustomRequestDto;
import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.PhotoBoardEntity;
import com.echoes.mysite.service.MemosService;
import com.echoes.mysite.service.PhotoBoardService;
import com.echoes.mysite.utils.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/memos")
public class MemosController {
    @Resource
    private MemosService memosService;

    @GetMapping("/list")
    public Result listMessages(@RequestParam(defaultValue = "0") int offset,
                               @RequestParam(defaultValue = "10") int limit) {
        Page<MemosEntity> allMessages;
        try {
            allMessages = memosService.findAllMessages(limit, offset);
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", allMessages);
    }

    @PostMapping("/saveMemos")
    public Result saveMemos(
            @RequestBody CustomRequestDto customRequestDto) {
        try {
            memosService.saveMemos(customRequestDto);
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "留言保存成功");
    }

    @PostMapping("/deleteMemos")
    public Result deleteMemos(@RequestBody CustomRequestDto customRequestDto) {
        try {
            memosService.deleteMemos(customRequestDto.getId());
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "留言删除成功");
    }

    @PostMapping("/deleteBatch")
    public Result deleteMemosBatch(@RequestBody CustomRequestDto customRequestDto) {
        try {
            memosService.deleteMemosBatch(customRequestDto.getIds());
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "留言删除成功");
    }
}
