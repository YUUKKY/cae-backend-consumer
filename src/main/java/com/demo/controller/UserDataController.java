package com.demo.controller;

import com.demo.model.UserDataDo;
import com.demo.service.UserDataService;
import com.demo.vo.UserDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1")
public class UserDataController {
    @Autowired
    private UserDataService dataService;

    @CrossOrigin
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public UserDataVo getData(@RequestParam(value = "user_name") String userName) {
        UserDataDo data = dataService.getData(userName);
        if (data == null) {
            data = new UserDataDo(userName);
        }
        data.setScanNum(data.getScanNum() + 1);
        if (data.getScanNum() <= 1) {
            dataService.insert(data.getUserName(), data.getScanNum());
        } else {
            dataService.updateTotal(data.getUserName(), data.getScanNum());
        }
        return new UserDataVo().setUserName(data.getUserName()).setScanNum(data.getScanNum());
    }
}
