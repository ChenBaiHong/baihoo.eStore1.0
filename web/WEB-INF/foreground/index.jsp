<%--
  Created by IntelliJ IDEA.
  User: ChenBaiHong
  Date: 9/23/2016
  Time: 5:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--开始Meta-->
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <meta name="description" content=""/>
    <meta name="author" content="ChenBaiHong"/>
    <!--结束Meta-->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/backgroundPlug/img/"/>
    <title>后台管理员操作界面</title>
    <!--开始引入css stylesheet-->
    <!-- BOOTSTRAP CSS -->
    <link href="${pageContext.request.contextPath}/backgroundPlug/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/backgroundPlug/css/bootstrap-reset.css" rel="stylesheet"/>
    <!-- 特色风格的字体 图标 CSS样式 -->
    <link href="${pageContext.request.contextPath}/backgroundPlug/assets/font-awesome/css/font-awesome.css"rel="stylesheet"/>
    <!-- 基本主题的 CSS 样式 -->
    <link href="${pageContext.request.contextPath}/backgroundPlug/css/style.css"rel="stylesheet"/>
    <!--响应式主题css响应样式-->
    <link href="${pageContext.request.contextPath}/backgroundPlug/css/style-responsive.css" rel="stylesheet"/>
    <!-- MORRIS 图表 CSS 样式 -->
    <link href="${pageContext.request.contextPath}/backgroundPlug/assets/morris.js-0.4.3/morris.css"rel="stylesheet"/>
    <!--仪表盘 万年历 日历样式-->
    <link href="${pageContext.request.contextPath}/backgroundPlug/css/clndr.css" rel="stylesheet">

    <!--结束引入css stylesheet-->
</head>
<body>
<!--开始-->
    <section id="container">
       <!--开始页头-->
        <header class="header white-bg"><!--页头白色背景-->
            <!--侧边列切换键按钮-->
            <div class="sidebar-toggle-box">
                <div  data-placement="right" class="fa fa-bars tooltips"></div>
            </div>
            <!--侧边列切换键按钮 结束-->
            <a href="index.jsp" class="logo">${admin.adminname}<span>管理员</span></a>
            <!-- 开始标题导航 -->
            <nav class="nav notify-row" id="top_menu"><!--横向导航栏-->
                <!-- 开始通知任务栏 -->
                <ul class="nav top-menu">
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="fa fa-tasks"></i>
                            <span class="badge bg-success">
                              
                            </span><!--接受客户订单的数量-->
                        </a>
                    </li>
                </ul>
            </nav>
        </header>
    </section>
</body>
</html>




































































