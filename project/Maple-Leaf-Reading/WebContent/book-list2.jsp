<!DOCTYPE html>
<html>  
  <head>
    
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8" import="java.util.*,com.example.entity.Book"%>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
  
  
  
  
  
  
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  
  <body>
    <div class="x-body">
    <!--  from表单增加提交信息属性-->
        <form class="layui-form" action="BookList2Servlet" method="post">
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>图书名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="bookName" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>分类
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="classification" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>阅读量
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="phone" name="readingVolume" required="" lay-verify="phone"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>章节数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="numberOfChapters" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
      
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>作者              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="author" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
         
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>简介
                  
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="briefIntroduction" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
          
          
          
          
          
          
          <!-- 下面是提交按钮 -->
          
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red"></span>
              </label>
        <input type="submit" name="增加"/>               
              </div>
          </div>          
          
                  <span class="x-red">${message }</span>
 
          
          
      </form>
    <!--  from表单增加提交信息属性-->
      
      
      
      
    </div>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>

</html>