package com.demo.service;


import com.demo.model.UserDataDo;

public interface UserDataService {


    UserDataDo getData(String userName);

    void insert(String userName,Integer total);

    void updateTotal(String userName,Integer total);

    Integer getTotalScanNum();

}
