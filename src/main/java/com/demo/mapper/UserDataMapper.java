package com.demo.mapper;

import com.demo.model.UserDataDo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataMapper {
    UserDataDo query(String userName);

    void insert(String userName, Integer scanNum);

    void updateTotal(String userName, Integer scanNum);

    Integer getTotalScanNum();
}
