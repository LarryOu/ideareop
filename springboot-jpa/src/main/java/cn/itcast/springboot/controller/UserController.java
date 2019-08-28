package cn.itcast.springboot.controller;

import cn.itcast.springboot.dao.UserDao;
import cn.itcast.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/findAll")
    public List<User> findAll() {
        return userDao.findAll();
    }
}
