<!DOCTYPE html>
<html>
  
  <head>
  
  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8" import="java.util.*,com.example.entity.Student"%>
<META http-equiv=Content-Type content="text/html; charset=utf-8">


    <meta charset="UTF-8">
    <title>枫叶悦读 - 用户管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="./css/font.css">
    <link rel="stylesheet" href="./css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="./lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="./js/xadmin.js"></script>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  </head>
  
  <body>
  
  
  
  
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">枫叶悦读 - 后台管理</a>
        <a href="">用户管理</a>
        <a>
          <cite>用户列表</cite></a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    
    
    <!--下面是搜索框-->
    <div class="x-body">
      <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" action="Student1Servlet?page=1" method="post">          
          <input type="text" name="searchname"  placeholder="请输入用户名称" autocomplete="off" class="layui-input">
    <!--        <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">   &#xe615;</i></button>-->
         <input type="submit" name="增加" class="layui-btn"  lay-submit="" lay-filter="sreach"/><i class="layui-icon">   &#xe615;</i>
        </form>           
      </div>
      
      
      
      
      <xblock>
         <button class="layui-btn" onclick="x_admin_show('添加用户','student-list2.jsp')"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${studentsnumber} 条</span>
      </xblock>
      <table class="layui-table">
      <!--主要分页界面的标题栏-->
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>编号</th>
            <th>书名</th>
            <th>书图片</th>
            <th>用户名</th>
            <th>用户图片</th>
            <th>书评</th>
            <th>打分</th>
            <th>操作</th>
            </tr>
        </thead>
      <!--主要分页界面的内容栏-->
      <!-- Tbody是循环体 -->
        <!-- 开始编写循环体 -->
        <c:forEach var="stu" items="${studentslist1}" varStatus="sta" >
        <tbody>
          <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            <td>第${sta.index+1}条数据</td>
            <td>${studentslist1.get(sta.index).bookName}</td>
            <td><img height="60" width="100" src="img/${studentslist1.get(sta.index).bookImage}"></td>
            <td>${studentslist1.get(sta.index).userName}</td>
            <td><img height="60" width="100" src="img1/${studentslist1.get(sta.index).userImage}"></td>
            <td>${studentslist1.get(sta.index).content}</td>
            <td>${studentslist1.get(sta.index).score}</td>         
            <td>
            <button>
              <a href="PingLunListServlet?page=${aim}&order=delete&deletename=${studentslist1.get(sta.index).id}">
                <i class="layui-icon">&#xe640;</i>
              </a>
             </button>              
            </td>
          </tr>
        </tbody>
        </c:forEach>  

        
        
      </table>
      
      
      
      
      
      <!--分页展示的底部小窗口-->
      <div class="page">
        <div>
          <a class="num" href="PingLunListServlet?page=1">首页</a>
          <a class="prev" href="PingLunListServlet?page=${aim-1 }">&lt;&lt;</a><!-- 上一页 -->
          <a class="num" href="">${aim }</a><!-- 当前页 -->
          <span class="current">总页 ${lastpage }</span>
          <a class="next" href="PingLunListServlet?page=${aim+1 }">&gt;&gt;</a>
          <a class="num" href="PingLunListServlet?page=${lastpage }">尾页</a>
        </div>
      </div>

    </div>
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    <!--下面是H5的script方法-->
    <script>
      layui.use('laydate', function(){
        var laydate = layui.laydate;
        
        //执行一个laydate实例
        laydate.render({
          elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
          elem: '#end' //指定元素
        });
      });

       /*用户-停用*/
   
     
    </script>
    <script>var _hmt = _hmt || []; (function() {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
      })();</script>
  </body>

</html>