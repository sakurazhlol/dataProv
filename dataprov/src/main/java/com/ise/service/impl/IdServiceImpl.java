package com.ise.service.impl;

import com.ise.service.IdService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdServiceImpl implements IdService {
    @Override
    public String genId() {
        return UUID.randomUUID().toString();
    }
}
