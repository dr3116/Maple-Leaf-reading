package com.example.backcontroller.echarts;


import com.example.entity.myself_style.StrStrBean;
import com.example.mapper.ClassificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 异侠 2021-05-06
 */

@Controller
public class EchartsBingController {
    @Autowired
    private ClassificationMapper classificationMapper;





    @GetMapping("/toEcharts-Bing.html")
    public String toWelcome(HttpSession session) {
//        System.out.println("- - - - - - - - - - -");
//        System.out.println("开始准备饼状图数据");

        List<StrStrBean> lists=classificationMapper.getClassificationNumberAndName();
        for(StrStrBean strStrBean:lists){
            String str2=strStrBean.getStr2();
            String str1=strStrBean.getStr1();
            if(str1.equals("小程序")){
                str1="xiaochengxu";
            }
            if(str1.equals("机器学习")){
                str1="jiqixuexi";
            }
            if(str1.equals("深度学习")){
                str1="shenduxuexi";
            }
            if(str1.equals("测试")){
                str1="ceshi";
            }
            session.setAttribute(str1,str2+"00");
        }





//        Android         4
//        C/C++         3
//        H5小游戏         2
//        HTML5         2
//        Java         5
//        JavaScript         2
//        JQuery         3
//        MySQL         3
//        Python         3
//        小程序         2
//        机器学习         3
//        测试         3
//        深度学习         2






        return "echarts-bing";
    }



}
