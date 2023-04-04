package com.ue.termi.controller.demo;

import com.ue.termi.service.UserService;
import com.ue.termi.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: iserver
 * @description: description
 * @author: youyi
 * @create: 2019-12-26 15:14
 **/
@RestController
public class DemoController {
    @Autowired
    private UserService userService;
    @GetMapping("/getList")
    public ResponseResult getList(){
        return ResponseResult.success(userService.getList());
    }

}
