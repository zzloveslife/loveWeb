package com.echoes.mysite.service.impl;

import com.echoes.mysite.dao.HelloDao;
import com.echoes.mysite.entity.HelloEntity;
import com.echoes.mysite.service.HelloService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelloServiceImpl implements HelloService {

    @Resource
    HelloDao helloDao;

    @Override
    public String hello() {
        List<HelloEntity> helloEntityList = helloDao.findAll();
        HelloEntity helloEntity = helloEntityList.get(0);
        String hello = helloEntity.getHello();
        return hello;
    }
}
