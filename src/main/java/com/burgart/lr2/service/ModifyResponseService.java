package com.burgart.lr2.service;

import com.burgart.lr2.model.Response;
import org.springframework.stereotype.Service;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}