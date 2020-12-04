<!doctype html>
<html lang="en">
<head>
<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8"%>
<META http-equiv=Content-Type content="text/html; charset=utf-8">



	<meta charset="UTF-8">
	<title>枫叶阅读 - 登陆页面</title>
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
<body class="login-bg">
    

    <div class="login layui-anim layui-anim-up">
        <div class="message">枫叶阅读</div>
        <div id="darkbannerwrap"></div>
        

        
        <form method="post" class="layui-form" action="Login">
            <input name="name" placeholder="账号"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" name="login" style="width:100%;" type="submit">
            <hr class="hr20" >
            <p>    ${loginMessage }</p>
        </form>
    </div>
    

    
</body>
</html>