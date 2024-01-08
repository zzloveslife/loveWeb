package com.echoes.mysite.service;

import com.echoes.mysite.dto.CustomRequestDto;
import com.echoes.mysite.entity.MemosEntity;
import com.echoes.mysite.entity.PhotoBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemosService {
    Page<MemosEntity> findAllMessages(int limit, int offset);

    boolean saveMemos(CustomRequestDto customRequestDto);

    boolean deleteMemos(Long id);

    boolean deleteMemosBatch(List<Long> ids);
}
