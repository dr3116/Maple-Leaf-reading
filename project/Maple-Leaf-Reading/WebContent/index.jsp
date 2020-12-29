<!doctype html>
<html lang="en">
<head>

<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<META http-equiv=Content-Type content="text/html; charset=utf-8" import="java.utrl.*">


	<meta charset="UTF-8">
	<title>MapeReading</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
	<link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>

</head>
<body>


    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="#">枫叶阅读 - 后台管理</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        <ul class="layui-nav left fast-add" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">快捷方式</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a onclick="x_admin_show('图书','BookList1')"><i class="iconfont">&#xe6a2;</i>图书</a></dd>
              <dd><a onclick="x_admin_show('学生','Student1Servlet')"><i class="iconfont">&#xe6a8;</i>用户</a></dd>
            </dl>
          </li>
        </ul>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">${username }</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a onclick="x_admin_show('个人信息','http://www.baidu.com')">个人信息</a></dd>
              <dd><a href="Logout?name=${username }&state=2">切换账号</a></dd>
              <dd><a href="Logout?name=${username }&state=1">退出登录</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item to-index"><a href="Logout?name=${username }&state=1">退出登录</a></li>
        </ul>
        
    </div>
    <!-- 顶部结束 -->
    
    
    
    
    
    
    
    
    
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>学生</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="Student1Servlet">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>学生列表</cite>                            
                        </a>
                    </li >
                    <li>
                        <a href="javascript:;">
                            <i class="iconfont">&#xe70b;</i>
                            <cite>学生管理</cite>
                            <i class="iconfont nav_right">&#xe697;</i>
                        </a>
                        <ul class="sub-menu">
                            <li>
                                <a _href="student-list2.jsp">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>增加学生账号</cite>                                    
                                </a>
                            </li >
                            <li>
                                <a _href="student-list3.jsp">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>修改学生信息</cite>                                    
                                </a>
                            </li>                            
                        </ul>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>图书</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="BookList1">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>图书列表</cite>                            
                        </a>
                    </li >
                    <li>
                        <a href="javascript:;">
                            <i class="iconfont">&#xe70b;</i>
                            <cite>图书管理</cite>
                            <i class="iconfont nav_right">&#xe697;</i>
                        </a>
                        <ul class="sub-menu">
                            <li>
                                <a _href="book-list2.jsp">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>增加图书</cite>                                    
                                </a>
                            </li >
                            <li>
                                <a _href="book-list3.jsp">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>修改图书</cite>                                    
                                </a>
                            </li>                            
                        </ul>
                    </li>
                </ul>                
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>其余</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="PingLunListServlet">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>评论列表</cite>                            
                        </a>
                    </li >                    
                </ul>   
            </li>
            
            
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    
    
    
    
    
    
    
    
    
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='WelcomeServlet?username=${username}' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    
    
    
    
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright">Copyright ©2017 x-admin v2.3 All Rights Reserved</div>  
    </div>
    <!-- 底部结束 -->
    <script>
    //百度统计可去掉
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