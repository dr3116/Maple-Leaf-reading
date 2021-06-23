package com.example.backcontroller.login_logout;

import com.example.entity.Master;
import com.example.service.MasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author 异侠 2021-05-03
 */


@Slf4j
@Controller
public class LoginController {
    @Autowired
    private MasterService masterService;



    /**地址栏跳转登陆页面*/
    @GetMapping(value = {"/", "/readyLogin"})
    public String loginPage() {
//        System.out.println("开始跳转到登陆页面");
        return "login";
    }



    /**虚假的去main页面*/
    @PostMapping("/toLogin")
    public String main(Master master, HttpSession session, Model model){ //RedirectAttributes



        //非空判断
        if(StringUtils.hasLength(master.getAccount()) && StringUtils.hasLength(master.getPassword())){
            //打印表单上交的数据
//            System.out.println("登陆者输入的信息是："+master);
            //链接数据库，数据库查询的结果为：
            Master dbMaster=masterService.findLoginUserInfo(master.getAccount());
//            System.out.println("数据库查询的结果为："+dbMaster.toString());
            //对结果进行判断
            if(dbMaster.getPassword().equals(master.getPassword()) && dbMaster.getAccount().equals(master.getAccount())){
                //数据正确
                //把登陆成功的用户保存起来
                session.setAttribute("loginMaster",master);
                //登录成功重定向到main.html;  重定向防止表单重复提交
                return "redirect:/toIndex";
            }else {
                model.addAttribute("msg","账号密码错误");
                //回到登录页面
                return "login";
            }
        }else{
            model.addAttribute("msg","输入信息不可为空，请重新输入");
            //回到登录页面
            return "login";

        }
    }




    /** 真实的去main页面 */
    @GetMapping("/toIndex")
    public String mainPage(HttpSession session,Model model){
//        System.out.println("开始真实的去main页面");
        //是否登录。  拦截器，过滤器
        Object loginMaster = session.getAttribute("loginMaster");
        if(loginMaster != null){
            //model携带信息给页面
            model.addAttribute("loginMaster",loginMaster);
            return "index";
        }else {
            //回到登录页面
            model.addAttribute("msg","请重新登录");
            return "login";
        }
    }





}
