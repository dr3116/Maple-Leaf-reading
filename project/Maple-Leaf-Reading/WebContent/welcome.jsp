<!DOCTYPE html>
<html>
    <head>
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8" import="java.util.*"%>
<META http-equiv=Content-Type content="text/html; charset=utf-8" import="java.utrl.*">
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
    
    
    
        <meta charset="UTF-8">
        <title>枫叶阅读 - 欢迎页面-</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
        
    </head>
    <body>
    
    <% 
	    long welcometime = new Date().getTime(); 
	    request.setAttribute("welcometime", welcometime); 
	    String username=request.getAttribute("username").toString();
	    int [] welcomelist = (int[])request.getAttribute("welcomelist");//1-6位置是我要的数据
    %>
    
    <div class="x-body layui-anim layui-anim-up">
        <blockquote class="layui-elem-quote">欢迎管理员：
            <span class="x-red">${username}</span>
	              ！当前时间:<jsp:useBean id="now" class="java.util.Date" scope="page"/>
	        <fmt:formatDate value="${now}" pattern="yyyy年MM月dd日" />
	    </blockquote>
        <fieldset class="layui-elem-field">
            <legend>数据统计</legend>
            <div class="layui-field-box">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body">
                            <div class="layui-carousel x-admin-carousel x-admin-backlog" lay-anim="" lay-indicator="inside" lay-arrow="none" style="width: 100%; height: 90px;">
                                <div carousel-item="">
                                    <ul class="layui-row layui-col-space10 layui-this">
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>图书数</h3>
                                                <p>
                                                    <cite>${welcomelist[1]}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>用户数</h3>
                                                <p>
                                                    <cite>${welcomelist[2]}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>评论数</h3>
                                                <p>
                                                    <cite>${welcomelist[3]}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>留言数</h3>
                                                <p>
                                                    <cite>${welcomelist[4]}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>管理人员数</h3>
                                                <p>
                                                    <cite>${welcomelist[5]}</cite></p>
                                            </a>
                                        </li>
                                        <li class="layui-col-xs2">
                                            <a href="javascript:;" class="x-admin-backlog-body">
                                                <h3>书籍种类数</h3>
                                                <p>
                                                    <cite>${welcomelist[6]}</cite></p>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统通知</legend>
            <div class="layui-field-box">
                <table class="layui-table" lay-skin="line">
                    <tbody>
                        <tr>
                            <td >
                                <a class="x-a" href="http://software.hebtu.edu.cn/a/2020/11/17/CD2F1C5E25254F73AD178B880994E09F.html" target="_blank">软件学院隆重举行第五届思源论坛活动</a>
                            </td>
                        </tr>
                        <tr>
                            <td >
                                <a class="x-a" href="http://software.hebtu.edu.cn/a/2020/11/09/ED56A7D06806413BB65433E0B2112822.html" target="_blank">软件学院成功召开第十一届团员代表大会暨学生代表大会</a>
                            </td>
                        </tr>                        
                        <tr>
                            <td >
                                <a class="x-a" href="http://software.hebtu.edu.cn/a/2020/09/29/97299678D6964B55BBFB5ADDC5E25A7F.html" target="_blank">我院在第四届河北省大学生程序设计竞赛中取得优异成绩</a>
                            </td>
                        </tr>
                        
                        <tr>
                            <td >
                                <a class="x-a" href="http://software.hebtu.edu.cn/a/2020/09/24/287005684B4B4D4183C322DC1686E299.html" target="_blank">软件学院加强考研自习室管理</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统信息</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>枫叶悦读版本</th>
                            <td>1.0</td></tr>
                        <tr>
                            <th>服务器地址</th>
                            <td>x.tomcat.com</td></tr>
                        <tr>
                            <th>操作系统</th>
                            <td>Windows</td></tr>
                        <tr>
                            <th>运行环境</th>
                            <td>Apache/2.4.23 (Win32) OpenSSL/1.0.2j mod_fcgid/2.3.9</td></tr>
                        <tr>
                            <th>PHP版本</th>
                            <td>5.6.27</td></tr>
                        <tr>
                            <th>上传附件限制</th>
                            <td>2M</td></tr>
                        <tr>
                            <th>执行时间限制</th>
                            <td>30s</td></tr>
                        <tr>
                            <th>剩余空间</th>
                            <td>86015.2M</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>开发团队</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>版权所有</th>
                            <td>枫叶悦读
                                <a href="https://github.com/dr3116/Maple-Leaf-reading" class='x-a' target="_blank">访问官网</a></td>
                        </tr>
                        <tr>
                            <th>开发者</th>
                            <td>枫叶悦读（团队）</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <blockquote class="layui-elem-quote layui-quote-nm">橙白之路，就在脚下。</blockquote>
    </div>
        <script>
        var _hmt = _hmt || [];
        (function() {
          var hm = document.createElement("script");
          hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
          var s = document.getElementsByTagName("script")[0]; 
          s.parentNode.insertBefore(hm, s);
        })();
        </script>
    </body>
</html>