package com.qf.service.impl;

import com.qf.Response.UserResponse;
import com.qf.domain.User;
import com.qf.repository.UserRepository;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by 张包包 on 2019/11/27.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse findAll(Integer size, Integer page) {

        if (page<0){
            page=0;
        }else {
            page= page-1;
        }
        Pageable pages = PageRequest.of(page,size);
        Page<User> all = userRepository.findAll(pages);
        List<User> content = all.getContent();
        UserResponse us = new UserResponse();
        us.setList(content);
        us.setTotal(all.getTotalElements());
        us.setPage(all.getTotalPages());
        return us;
    }

    @Override
    public User findById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        User user=null;
        if (byId.isPresent()){
            user = byId.get();
        }
        return user;
    }

    @Override
    public User saveAndFlush(User user) {


        return userRepository.saveAndFlush(user);
    }

    @Override
    public String deleteById(User user) {
        try {
            userRepository.deleteById(user.getId());
            return "success";
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        return "fail";
    }
}
