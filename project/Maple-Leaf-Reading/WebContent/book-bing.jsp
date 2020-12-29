<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8" import="java.util.*,com.example.entity.Book"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>后台登录-X-admin2.0</title>
        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <meta http-equiv="Cache-Control" content="no-siteapp" />

  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="./css/font.css">
        <link rel="stylesheet" href="./css/xadmin.css">
    </head>
    <body>
    
    <%
    int a=Integer.parseInt(request.getParameter("a"));
    int t=Integer.parseInt(request.getParameter("t"));
    int h=Integer.parseInt(request.getParameter("h"));
    int p=Integer.parseInt(request.getParameter("p"));
    int c=Integer.parseInt(request.getParameter("c"));
    String color=request.getParameter("color").toString();
        request.setAttribute("a1", a*30);
        request.setAttribute("t1", t*30);
        request.setAttribute("h1", h*30);
        request.setAttribute("p1", p*30);
        request.setAttribute("c1", c*30);
        request.setAttribute("color1", color);
    %>
   
    
    
        <div class="x-body">
            <div id="main" style="width: 100%;height:400px;"></div>
        </div>
        <script src="//cdn.bootcss.com/echarts/3.3.2/echarts.min.js" charset="utf-8"></script>
        <script src="//cdn.bootcss.com/echarts/3.3.2/extension/bmap.min.js" type="text/javascript"></script>
        <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));


        
        // 指定图表的配置项和数据
        option = {
            backgroundColor: '#2c343c',

            title: {
                text: '枫叶悦读 - 图书列表',
                left: 'center',
                top: 20,
                textStyle: {
                    color: '#ccc'
                }
            },

            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },

            visualMap: {
                show: false,
                min: 80,
                max: 600,
                inRange: {
                    colorLightness: [0, 1]
                }
            },
                  
            
            
            series : [
                {
                    name:'访问来源',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '50%'],
                    data:[
                        {value:${a1}, name:'Java与移动智能设备开发方向'},
                        {value:${t1}, name:'选修'},
                        {value:${h1}, name:'HTML5与移动互联网应用开发方向'},
                        {value:${p1}, name:'大数据与人工智能'},
                        {value:${c1}, name:'测试方向'}
                        
                       
                    ].sort(function (a, b) { return a.value - b.value}),
                    roseType: 'angle',
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(255, 55, 255, 0.8)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(255, 0, 0, 0.9)'
                            },
                            smooth: 0.2,
                            length: 10,
                            length2: 20
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#c23531',
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };



        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
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