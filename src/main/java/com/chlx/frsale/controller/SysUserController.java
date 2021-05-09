package com.xiaowang.mesqle.controller;


import com.xiaowang.mesqle.pojo.User;
import com.xiaowang.mesqle.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/gotoUpdateUser")
    public String gotoUpdateUser(Model model,int id){
        User user = this.usersService.findUserById(id);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @PostMapping("/updateUserType")
    public String updateUserType(User user){
        try{
            this.usersService.updateUserType(user);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/sysUser/showAllUsers";
    }

    @GetMapping("/showAllUsers")
    public String showAllUsers(Model model){
        try{
            List<User> list = this.usersService.findAllUser();
            System.out.println(list.size());
            model.addAttribute("list", list);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "showUsers";
    }
}
