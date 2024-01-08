package com.echoes.mysite.service;

import com.echoes.mysite.dto.CustomRequestDto;
import com.echoes.mysite.entity.PhotoBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoBoardService {
    Page<PhotoBoardEntity> findAllMessages(int limit, int offset);

    boolean savePhoto(MultipartFile photo, String content);

    boolean deletePhoto(Long id);

    boolean deleteBatch(List<Long> ids);
}
