<%--
  Created by IntelliJ IDEA.
  User: cbh12
  Date: 9/27/2016
  Time: 1:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html class="no-js">
<head>
    <title>Title</title>
    <meta name="description" content="">
    <meta name="viewpoint" content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backgroundPlug/AddRole/css/normalize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backgroundPlug/AddRole/css/font-awesome.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backgroundPlug/AddRole/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/backgroundPlug/AddRole/css/templatemo-style.css">
    <script src="${pageContext.request.contextPath}/backgroundPlug/AddRole/js/vendor/modernizr-2.6.2.min.js"></script>
</head>
<body>
<!--[if lt IE 7]>
<p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
<![endif]-->
<!--开始页面包装-->
<section class="wrapper">
<div class="site-bg"></div>
<div class="site-bg-overlay"></div>

<!-- TOP HEADER -->
<div class="top-header">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-12">
                <p class="phone-info">Call us: <a href="#">010 020 0340</a></p>
            </div>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <div class="social-icons">
                    <ul>
                        <li><a href="#" class="fa fa-facebook"></a></li>
                        <li><a href="#" class="fa fa-twitter"></a></li>
                        <li><a href="#" class="fa fa-linkedin"></a></li>
                        <li><a href="#" class="fa fa-dribbble"></a></li>
                        <li><a href="#" class="fa fa-rss"></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div> <!-- .top-header -->

<div class="copyrights">Collect from </div>
<div class="container" id="page-content">
    <div class="row">
        <div class="col-md-9 col-sm-12 content-holder">
            <!-- CONTENT -->
            <div id="menu-container">
                <div id="menu-3" class="content gallery-section">
                    <div class="box-content">
                        <h3 class="widget-title">观赏画廊</h3>
                        <div class="row">
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="project-item">
                                    <img src="${pageContext.request.contextPath}/backgroundPlug/AddRole/images/1.jpg" alt="">
                                    <div class="project-hover">
                                        <h4>Great Nature Capture</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="project-item">
                                    <img src="${pageContext.request.contextPath}/backgroundPlug/AddRole/images/2.jpg" alt="">
                                    <div class="project-hover">
                                        <h4>Another Image Caption</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="project-item">
                                    <img src="${pageContext.request.contextPath}/backgroundPlug/AddRole/images/3.jpg" alt="">
                                    <div class="project-hover">
                                        <h4>Great Nature Capture</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="project-item">
                                    <img src="${pageContext.request.contextPath}/backgroundPlug/AddRole/images/4.jpg" alt="">
                                    <div class="project-hover">
                                        <h4>Another Image Caption</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="project-item">
                                    <img src="${pageContext.request.contextPath}/backgroundPlug/AddRole/images/5.jpg" alt="">
                                    <div class="project-hover">
                                        <h4>Great Nature Capture</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 col-sm-6 col-xs-12">
                                <div class="project-item">
                                    <img src="${pageContext.request.contextPath}/backgroundPlug/AddRole/images/6.jpg" alt="">
                                    <div class="project-hover">
                                        <h4>Another Image Caption</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="project-pages">
                            <ul>
                                <li><a href="#">1</a></li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">...</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div id="menu-4" class="content contact-section" style="display:block">
                    <div class="row">
                        <div class="col-md-8 col-sm-8">
                            <div class="box-content">
                                <h3 class="widget-title">权限操作</h3>
                                <form class="contact-form" action="javascript:;" method="post">
                                    <fieldset>
                                        <select id="authority" name="authority"
                                                style="border-radius:1px;box-shadow:0 0 2px rgba(0,0,0,0.5);  border:1px silver;width: 150px;">
                                            <option>--开启权限--</option>
                                            <c:forEach var="authority" items="${requestScope.authorityNames}">
                                                <option value="${authority}">${authority}</option>
                                            </c:forEach>
                                        </select>
                                    </fieldset>
                                    <br>
                                    <fieldset>
                                        <textarea name="message" id="description"  cols="30" rows="4" placeholder="权限描述.."></textarea>
                                    </fieldset>
                                    <fieldset>
                                        <input type="submit" class="button" id="button" value="保存数据库">
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-4">
                            <div class="box-content">
                                <h3 class="widget-title">权限简要</h3>
                                <p>权限是为各个管理员所对当前管理页面的操作权限，合理的分配权限能够更好的管理！ <br><br>
                                    权限处理消息:<br>
                                <span id="prompt" style="display:none ;font-weight:bold; font-size:14px;color: #843534">并未选中权限</span>
                                </p>
                                <div class="about-social">
                                    <ul>
                                        <li><a href="#" class="fa fa-facebook"></a></li>
                                        <li><a href="#" class="fa fa-twitter"></a></li>
                                        <li><a href="#" class="fa fa-linkedin"></a></li>
                                        <li><a href="#" class="fa fa-dribbble"></a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>


        <div class="col-md-3 hidden-sm">
            <nav id="nav" class="main-navigation hidden-xs hidden-sm">
                <ul class="main-menu">
                    <li>
                        <a class="show-5 contactbutton" href="#"><i class="fa fa-user"></i>Add Authority</a>
                    </li>
                    <li>
                        <a class="show-3 projectbutton" href="#"><i class="fa fa-camera"></i>Gallery</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</section>

<!-- BEGIN JS -->
<script src="${pageContext.request.contextPath}/backgroundPlug/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/backgroundPlug/AddRole/js/plugins.js"></script>
<script src="${pageContext.request.contextPath}/backgroundPlug/AddRole/js/main.js"></script>
<script type="text/javascript">
    $(function(){
        var timer = setInterval(intervalTime, 5000);
        $('input[type="submit"]').click(function(){
            var authority = $("#authority").val();
            var authorityDescription=$("#description").val();
            if(authority.length==0 || authorityDescription.length==0){
                clearInterval(timer);
                timer=setInterval(intervalTime,5000);
                $("#prompt").text("并未选中权限并对权限的描述").show(500);
                return false;
            }else{
                var url="${pageContext.request.contextPath}/PrivilegeServlet?method=additionalPrivilege";
                var args={"authority":authority,"description":authorityDescription,"time":new Date()};
                $.post(url , args , function(data){
                    var object=eval("("+data+")")
                    var variable = object.authorityMessage;
                    if("addAuthority"==variable){
                        clearInterval(timer);
                        timer=setInterval(intervalTime,5000);
                        $("#prompt").text("添加权限成功！").show(500);
                    }else if("addRepeatAuthority"==variable){
                        clearInterval(timer);
                        timer = setInterval(intervalTime,5000);
                        $("#prompt").text("请不要重复添加权限！").show(500);
                    }else if("insufficientPrivilege"==variable){
                        clearInterval(timer);
                        timer = setInterval(intervalTime,5000);
                        $("#prompt").text("权限不足！请联系超级管理员").show(500);
                    }else if("internalError"==variable){
                        top.location.href="${pageContext.request.contextPath}/500.jsp";
                    }
                })
                return false;
            }
        })
        function intervalTime(){
            $("#prompt").hide(500);
        }
    })
</script>
</body>
</html>
