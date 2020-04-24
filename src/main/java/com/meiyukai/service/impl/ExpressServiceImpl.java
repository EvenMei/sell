package com.meiyukai.service.impl;

import com.meiyukai.dao.ExpressRepository;
import com.meiyukai.domain.Express;
import com.meiyukai.service.ExpressService;
import com.meiyukai.utils.KeyUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "expressService")
public class ExpressServiceImpl implements ExpressService {

    @Resource(name = "expressRepository")
    private ExpressRepository expressRepository;

    @Override
    public List<Express> findAll() {
        return expressRepository.findAll();
    }

    @Override
    public Express findExpressById(String id) {
        return expressRepository.findById(id).get();
    }

    @Override
    public Express findByOrderId(String orderId) {
        return expressRepository.findByOrderId(orderId);
    }



    @Transactional
    @Override
    public void addNewExpress(String orderId, String expressName, String expressNumber) {
        Express express  = new Express();
        express.setId(KeyUtil.getUniqueKey());
        express.setOrderId(orderId);
        express.setExpressName(expressName);
        express.setExpressNumber(expressNumber);
        expressRepository.save(express);
    }



}
