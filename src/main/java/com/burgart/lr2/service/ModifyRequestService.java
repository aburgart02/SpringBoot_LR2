package com.burgart.lr2.service;

import com.burgart.lr2.model.Request;
import org.springframework.stereotype.Service;

@Service
public interface ModifyRequestService {
    void modify(Request request);
}