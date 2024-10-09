package com.demo.service.impl;

import com.demo.mapper.UserDataMapper;
import com.demo.model.UserDataDo;
import com.demo.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService {
    @Autowired
    private UserDataMapper dataMapper;

    @Override
    public UserDataDo getData(String userName) {
        return dataMapper.query(userName);
    }

    @Override
    public void insert(String userName, Integer total) {
        dataMapper.insert(userName, total);
    }

    @Override
    public void updateTotal(String userName, Integer total) {
        dataMapper.updateTotal(userName, total);
    }

    @Override
    public Integer getTotalScanNum() {
        return dataMapper.getTotalScanNum();
    }
}
