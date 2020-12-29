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
<INPUT id="Button1" type="button" value="枫叶悦读系统所有信息" name="Button1" language=javascript onclick="allinfo()">
<div id="elInfo">
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
        
        function allinfo() {
            var appName = navigator.appName; //浏览器的正式名称
            var appVersion = navigator.appVersion; //浏览器的版本号
            var cookieEnabled = navigator.cookieEnabled; // 返回用户浏览器是否启用了cookie
            var cpuClass = navigator.cpuClass; //返回用户计算机的cpu的型号，通常intel芯片返回"x86"（火狐没有）

            var mimeType = navigator.mimeTypes; // 浏览器支持的所有MIME类型的数组
            var platform = navigator.platform; // 浏览器正在运行的操作系统平台，包括Win16(windows3.x) 
            // Win32(windows98,Me,NT,2000,xp),Mac68K(Macintosh 680x0)
            // 和ＭacPPC(Macintosh PowerPC)
            var plugins = navigator.plugins; // 安装在浏览器上的所有插件的数组
            var userLanguage = navigator.userLanguage; // 用户在自己的操作系统上设置的语言（火狐没有）
            var userAgent = navigator.userAgent; //包含以下属性中所有或一部分的字符串：appCodeName,appName,appVersion,language,platform
            var systemLanguage = navigator.systemLanguage; // 用户操作系统支持的默认语言（火狐没有）

            //director

            var appCodeName = navigator.appCodeName; //与浏览器相关的内部代码名
            var appMinorVersion = navigator.appMinorVersion; //辅版本号（通常应用于浏览器的补丁或服务包)

            var language = navigator.language; //浏览器支持的语言 （IE没有）

            var onLine = navigator.onLine; //返回浏览器是否处于在线模式（IE4以上版本）

            var opsProfile = navigator.opsProfile; // 未定义 （IE、火狐没有）

            var oscpu = navigator.oscpu; //浏览器正在运行的操作系统，其中可能有CPU的信息（IE没有）

            var product = navigator.product; // 浏览器的产品名（IE没有）

            var productSub = navigator.productSub; //关于浏览器更多信息（IE没有）

            var securityPolicy = navigator.securityPolicy; // 浏览器支持的加密类型（IE没有）

            var userProfile = navigator.userProfile; // 返回一个UserProfile对象，它存储用户的个人信息（火狐没有）

            var vender = navigator.vender; // 浏览器厂商名称（IE、火狐没有）

            var vendorSub = navigator.vendorSub; // 关于浏览器厂商更多的信息

            var webkitPersistentStorage = navigator.webkitPersistentStorage;

            var info = "<table border=1 class='layui-table'>";
            var type = "";
            if (isIe()) {
                type = "IE浏览器";
            } else if (isFireFox()) {
                type = "火狐浏览器";
            } else{
            	type="Chrome浏览器";
            }
            info += " <tr><th>枫叶悦读版本</th><td>1.0</td></tr><tr>";
            info += "<th>服务器地址</th><td>x.tomcat.com</td></tr><tr>";
            info += "<th>操作系统</th><td>Windows</td></tr><tr>";
            info += "<th>运行环境</th><td>Apache/2.4.23 (Win32) OpenSSL/1.0.2j mod_fcgid/2.3.9</td></tr><tr>";
            info += "<th>PHP版本</th><td>5.6.27</td></tr><tr>";
           	info += "<th>上传附件限制</th><td>2M</td></tr><tr>";
      		info += "<th>执行时间限制</th><td>30s</td></tr>";
      		info += "<tr><th>剩余空间</th><td>86015.2M</td></tr>";
            info += "<tr><th>IE特有属性：</th><td>" + navigator.msManipulationViewsEnabled + "</td></tr>";
            info += "<tr><th>浏览器类型：</th><td>" + type + "</td></tr>";
            info += "<tr><th>weizhi ：</th><td>" + webkitPersistentStorage + "</td></tr>";
            info += "<tr><th>浏览器属性信息：</th><td>" + userAgent + "</td></tr>";
            info += "<tr><th>浏览器的正式名称：</th><td>" + appName + "</td></tr>";
            info += "<tr><th>浏览器的版本号：</th><td>" + appVersion + "</td></tr>";
            info += "<tr><th>浏览器相关的内部代码名：</th><td>" + appCodeName + "</td></tr>";
            info += "<tr><th>浏览器相关的辅代码号：</th><td>" + appMinorVersion + "</td></tr>";
            info += "<tr><th>浏览器支持的语言IE没有）：</th><td>" + language + "</td></tr>";
            info += "<tr><th>浏览器是否处于在线模式：</th><td>" + onLine + "</td></tr>";
            info += "<tr><th>浏览器的产品名（IE没有）：</th><td>" + product + "</td></tr>";
            info += "<tr><th>浏览器更多信息：</th><td>" + productSub + "</td></tr>";
            info += "<tr><th>浏览器厂商名称：</th><td>" + vender + "</td></tr>";
            info += "<tr><th>浏览器厂商更多信息：</th><td>" + vendorSub + "</td></tr>";
            info += "<tr><th>浏览器的是否启用了cookie：</th><td>" + cookieEnabled + "</td></tr>";
            info += "<tr><th>cpu等级：</th><td>" + cpuClass + "</td></tr>";
            info += "<tr><th>浏览器的MIME类型：</th><td>" + mimeType.length + "</td></tr>";
            info += "<tr><th>系统平台：</th><td>" + platform + "</td></tr>";
            info += "<tr><th>安装的插件：</th><td>" + plugins + "</td></tr>";
            info += "<tr><th>插件的数量：</th><td>" + plugins.length + "</td></tr>";
            info += "<tr><th>插件的名称：</th><td>" + getPluginName() + "</td></tr>";
            info += "<tr><th>用户设置的操作系统语言：</th><td>" + userLanguage + "</td></tr>";
            info += "<tr><th>操作系统支持的默认语言：</th><td>" + systemLanguage + "</td></tr>";
            info += "<tr><th>Director：</th><td>" + checkePlugs("Director") + "</td></tr>";
            info += "<tr><th>javaEnabled：</th><td>" + navigator.javaEnabled() + "</td></tr>";
            info += "<tr><th>是否有quickTime：</th><td>" + checkePlugs("QuickTime") + "</td></tr>";
            info += "<tr><th>flash插件情况：</th><td>" + checkePlugs('Shockwave Flash') + "</td></tr>";
            info += "<tr><th>是否有MediaPlayer：</th><td>" + checkePlugs("MediaPlayer") + "</td></tr>";
            info += "<tr><th>是否有realPlayer:</th><td>" + checkePlugs("RealPlayer") + "</td></tr>";
            info += "<tr><th>屏幕分辨率高度：</th><td>" + window.screen.height + "</td></tr>";
            info += "<tr><th>屏幕分辨率宽度：</th><td>" + window.screen.width + "</td></tr>";
            info += "<tr><th>颜色质量：</th><td>" + window.screen.colorDepth + "位</td></tr>";
            info += "<tr><th>像素：</th><td>" + window.screen.deviceXDPI + "像素/英寸</td></tr>";
            info += "<tr><th>字体是否平滑：</th><td>" + window.screen.fontSmoothingEnabled + "</td></tr>";
            info += "</table>";
            document.getElementById("elInfo").innerHTML = info;
            return info;



        }
        //获取插件所有的名称
        function getPluginName() {
            var info = "";
            var plugins = navigator.plugins;
            if (plugins.length > 0) {
                for (i = 0; i < navigator.plugins.length; i++) {
                    info += navigator.plugins[i].name + ";";
                }
            }
            return info;
        }
        //检查是否安装了某插件，如果安装了返回版本号
        function checkePlugs(pluginname) {
            var f = "-"
            var plugins = navigator.plugins;
            if (plugins.length > 0) {
                for (i = 0; i < navigator.plugins.length; i++) {
                    if (navigator.plugins[i].name.indexOf(pluginname) >= 0) {
                        f = navigator.plugins[i].description.split(pluginname)[1];
                        return f;
                        break;
                    }
                }
            }
            return false;
        }
        //判断是否IE
        function isIe() {
            var i = navigator.userAgent.toLowerCase().indexOf("msie");
            return i >= 0;
        }
        //判断是否firefox
        function isFireFox() {
            var i = navigator.userAgent.toLowerCase().indexOf("firefox");
            return i >= 0;
        }


        </script>
    </body>
</html>