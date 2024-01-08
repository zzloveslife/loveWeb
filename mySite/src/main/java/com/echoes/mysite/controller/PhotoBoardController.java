package com.echoes.mysite.controller;

import com.echoes.mysite.dto.CustomRequestDto;
import com.echoes.mysite.entity.PhotoBoardEntity;
import com.echoes.mysite.service.PhotoBoardService;
import com.echoes.mysite.utils.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/photoBoard")
public class PhotoBoardController {
    @Resource
    private PhotoBoardService photoBoardService;

    @GetMapping("/list")
    public Result listMessages(@RequestParam(defaultValue = "0") int offset,
                               @RequestParam(defaultValue = "10") int limit) {
        Page<PhotoBoardEntity> allMessages;
        try {
            allMessages = photoBoardService.findAllMessages(limit, offset);
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", allMessages);
    }

    @PostMapping("/savePhoto")
    public Result savePhoto(
            @RequestParam("photo") MultipartFile photo,
            @Valid CustomRequestDto customRequestDto) {
        try {
            photoBoardService.savePhoto(photo, customRequestDto.getContent());
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "照片保存成功");
    }

    @PostMapping("/deletePhoto")
    public Result deletePhoto(@RequestBody CustomRequestDto customRequestDto) {
        try {
            photoBoardService.deletePhoto(customRequestDto.getId());
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "照片删除成功");
    }

    @PostMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody CustomRequestDto customRequestDto) {
        try {
            photoBoardService.deleteBatch(customRequestDto.getIds());
        } catch (Exception e) {
            return new Result<>(500, "Exception", e.getMessage());
        }
        return new Result<>(200, "Success", "照片删除成功");
    }
}
