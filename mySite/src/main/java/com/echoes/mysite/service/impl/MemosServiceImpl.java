package com.echoes.mysite.service.impl;

import com.echoes.mysite.dao.MemosDao;
import com.echoes.mysite.dao.PhotoBoardDao;
import com.echoes.mysite.dao.TagDao;
import com.echoes.mysite.dto.CustomRequestDto;
import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.PhotoBoardEntity;
import com.echoes.mysite.entity.TagEntity;
import com.echoes.mysite.service.MemosService;
import com.echoes.mysite.service.PhotoBoardService;
import com.echoes.mysite.utils.CustomException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class MemosServiceImpl implements MemosService {

    @Resource
    private MemosDao memosDao;

    @Override
    public Page<MemosEntity> findAllMessages(int limit, int offset) {

        Pageable pageable = PageRequest.of(offset, limit, Sort.by("createdAt").descending());
        return memosDao.findByIsDeleteFalse(pageable);
    }

    @Override
    public boolean saveMemos(CustomRequestDto customRequestDto) {
        String content = customRequestDto.getContent();
        Long tagId = customRequestDto.getTagId();

        MemosEntity memosEntity = new MemosEntity();
        memosEntity.setContent(content);
        memosEntity.setCreatedAt(new Date());
        memosEntity.setIsDelete(false);
        memosEntity.setTagId(tagId);
        log.info("memosEntity is: {}", memosEntity);
        memosDao.save(memosEntity);
        return true;
    }

    @Override
    public boolean deleteMemos(Long id) {
        log.info("delete memos: {}", id);
        try {
            memosDao.updateIsDeleteById(id, true);
        } catch (Exception e) {
            throw new CustomException(500, "删除留言失败");
        }
        return true;
    }

    @Override
    public boolean deleteMemosBatch(List<Long> ids) {
        log.info("delete memos: {}", ids);
        try {
            memosDao.updateIsDeleteByIdBatch(ids, true);
        } catch (Exception e) {
            throw new CustomException(500, "删除留言失败");
        }
        return true;
    }
}
