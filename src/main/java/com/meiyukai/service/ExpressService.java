package com.meiyukai.service;

import com.meiyukai.domain.Express;

import java.util.List;

public interface ExpressService {

    List<Express> findAll();

    Express findExpressById(String id);

    Express findByOrderId(String orderId);

    void addNewExpress(String orderId , String expressName , String expressNumber);
}
