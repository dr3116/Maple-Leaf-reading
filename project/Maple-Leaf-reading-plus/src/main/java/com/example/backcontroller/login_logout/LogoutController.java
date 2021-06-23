package com.example.backcontroller.login_logout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 异侠 2021-05-05
 */

@Slf4j
@Controller
public class LogoutController {




    @GetMapping("/switchMaster")
    public String switchMaster() {
//        System.out.println("开始切换用户");
        return "login";
    }


    //虚假的登出
    @GetMapping("/logoutMaster")
    public String logoutMaster(HttpSession session) {
//        System.out.println("虚假注销用户");
        session.removeAttribute("loginMaster");
        return "redirect:/finalLogoutMaster";
    }


    //真实的登出
    @GetMapping("/finalLogoutMaster")
    public String finalLogoutMaster() {
//        System.out.println("真实注销用户");
        return "login";
    }




}
