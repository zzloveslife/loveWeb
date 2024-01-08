package com.echoes.mysite.service.impl;

import com.echoes.mysite.dao.PhotoBoardDao;
import com.echoes.mysite.entity.PhotoBoardEntity;
import com.echoes.mysite.service.PhotoBoardService;
import com.echoes.mysite.utils.CustomException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PhotoBoardServiceImpl implements PhotoBoardService {

    @Resource
    private PhotoBoardDao photoBoardDao;

    @Value("${file.path}")
    private String filePath;

    @Override
    public Page<PhotoBoardEntity> findAllMessages(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("createdAt").descending());
        return photoBoardDao.findByIsDeleteFalse(pageable);
    }

    @Override
    public boolean savePhoto(MultipartFile photo, String content) {
        if (!photo.isEmpty()) {
            try {
                String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                String randomStr = UUID.randomUUID().toString().replace("-", "");
                String filename = dateStr + "-" + randomStr + "-" + photo.getOriginalFilename();

                // 保存文件到指定路径
                byte[] bytes = photo.getBytes();
                Files.write(Path.of(filePath + filename), bytes);

                PhotoBoardEntity photoBoardEntity = new PhotoBoardEntity();
                photoBoardEntity.setContent(content);
                photoBoardEntity.setCreatedAt(new Date());
                photoBoardEntity.setUpdatedAt(new Date());
                photoBoardEntity.setIsDelete(false);
                photoBoardEntity.setImagePath(filePath + filename);
                log.info("photoBoardEntity is: {}", photoBoardEntity);
                photoBoardDao.save(photoBoardEntity);
            } catch (Exception e) {
                throw new CustomException(500, "保存照片失败");
            }
        }
        return true;
    }

    @Override
    public boolean deletePhoto(Long id) {
        log.info("delete photo: {}", id);
        try {
            photoBoardDao.updateIsDeleteById(id, true);
        } catch (Exception e) {
            throw new CustomException(500, "删除照片失败");
        }
        return true;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        log.info("delete photo: {}", ids);
        try {
            photoBoardDao.updateIsDeleteByIdBatch(ids, true);
        } catch (Exception e) {
            throw new CustomException(500, "删除照片失败");
        }
        return true;
    }
}
