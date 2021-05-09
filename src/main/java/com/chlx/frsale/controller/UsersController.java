package com.xiaowang.mesqle.controller;

import com.xiaowang.mesqle.pojo.User;
import com.xiaowang.mesqle.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UsersController {
    /*@Resource(name = "source")
    //@Autowired
    public DataSource dataSource;
    @GetMapping("/showInfo")
    public String showInfo(Model model){
        model.addAttribute("dis",dataSource.toString());
        return "source";
    }*/
    @Autowired
    private UsersService usersService;
    @PostMapping("/addUser")
    public String addUser(User user){
        try {
            //System.out.println(params.get("username"));
            this.usersService.addUsers(user);

        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "redirect:/ok";
    }

    @PostMapping("/login")
    public String login(User user,HttpServletRequest request){
        User existUser = new User();
        try {
            //System.out.println(params.get("username"));
            existUser = this.usersService.login(user);
            if(existUser==null){
                return "login";
            }else {
                HttpSession session= request.getSession();
                session.setAttribute("existUser", existUser);
                session.setAttribute("uid", existUser.getUid());
                session.setAttribute("usernamename", existUser.getUsername());
                session.setAttribute("type", existUser.getType());
            }
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        if (existUser.getType() == 0) {
            return "admin";
        }else if (existUser.getType() == 1){
            return "adminSaler";
        }else if (existUser.getType() == 2){
            return "adminPurchaser";
        }
        return "adminStocker";
    }

}
