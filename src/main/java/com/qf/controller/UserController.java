package com.qf.controller;

import com.qf.Response.UserResponse;
import com.qf.domain.User;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 张包包 on 2019/11/27.
 */

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll/{size}/{page}")
    public UserResponse findAll(@PathVariable("size")Integer size, @PathVariable("page")Integer page){
      return   userService.findAll(size,page);
    }

    @RequestMapping("/findOne")
    public User findOne(@RequestBody User user){
        Integer id = user.getId();
        return   userService.findById(id);
    }

    @RequestMapping("/updateuser")
    public User updateuser(@RequestBody User user){

        return   userService.saveAndFlush(user);
    }

    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    public String deleteById(@RequestBody User user){

        return   userService.deleteById(user);
    }
}
