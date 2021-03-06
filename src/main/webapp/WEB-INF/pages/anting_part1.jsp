<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if IE 8]>
<html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]>
<html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="pragma" content="no-cache" />
    <title>舜华能源 | 数据监控系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/daterangepicker.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="shortcut icon" href="media/image/favicon.ico"/>
</head>
<body class="page-header-fixed">
<div class="header navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="brand" href="index.jsp">
                <img src="media/image/logo.png" alt="logo"/>
            </a>
            <a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
                <img src="media/image/menu-toggler.png" alt=""/>
            </a>
            <ul class="nav pull-right">
                <li class="dropdown user">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img alt="" src="media/image/avatar1_small.jpg"/>
                        <span class="username">用户</span>
                        <i class="icon-angle-down"></i>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="extra_profile.html"><i class="icon-user"></i> 用户信息</a></li>
                        <li><a href="extra_lock.html"><i class="icon-lock"></i> 锁屏</a></li>
                        <li><a href="login.html"><i class="icon-key"></i> 退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div class="page-container">
    <div class="page-sidebar nav-collapse collapse">
        <ul class="page-sidebar-menu">
            <li class="">
                <a href="javascript:;">
                    <i class="icon-cogs"></i>
                    <span class="title">安亭站</span>
                    <span class="arrow "></span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="anting.jsp">
                            整体图</a>
                    </li>
                    <li>
                        <a href="anting_part1.jsp">
                            局部图1</a>
                    </li>
                    <li>
                        <a href="anting_part2.jsp">
                            局部图2</a>
                    </li>
                </ul>
            </li>
            <li class="start">
                <a href="index.html">
                    <i class="icon-home"></i>
                    <span class="title">大连站</span>
                    <!--<span class=""></span>-->
                </a>
            </li>
            <li class="start">
                <a href="index.html">
                    <i class="icon-home"></i>
                    <span class="title">其余站点</span>
                    <!--<span class=""></span>-->
                </a>
            </li>
            <!--
            <li class="start">
                <a href="index.html">
                    <i class="icon-home"></i>
                    <span class="title">安亭站</span>
                    <!--<span class=""></span>
                </a>
            </li>
            <li class="last">
                <a href="charts.html">
                    <i class="icon-bar-chart"></i>
                    <span class="title">图表信息</span>
                </a>
            </li>-->
        </ul>
    </div>
    <div class="page-content">
        <div class="container-fluid">
            <div class="row-fluid">
                <div class="span12">
                    <div class="color-panel hidden-phone">
                        <div class="color-mode-icons icon-color"></div>
                        <div class="color-mode-icons icon-color-close"></div>
                        <div class="color-mode">
                            <p>主题颜色</p>
                            <ul class="inline">
                                <li class="color-black current color-default" data-style="default"></li>
                                <li class="color-blue" data-style="blue"></li>
                                <li class="color-brown" data-style="brown"></li>
                                <li class="color-purple" data-style="purple"></li>
                                <li class="color-grey" data-style="grey"></li>
                                <li class="color-white color-light" data-style="light"></li>
                            </ul>
                            <label>
                                <span>布局</span>
                                <select class="layout-option m-wrap small">
                                    <option value="fluid" selected>流式布局</option>
                                    <option value="boxed">盒式布局</option>
                                </select>
                            </label>
                            <label>
                                <span>底部</span>
                                <select class="footer-option m-wrap small">
                                    <option value="fixed">固定</option>
                                    <option value="default" selected>默认</option>
                                </select>
                            </label>
                        </div>
                    </div>
                    <h3 class="page-title">
                        安亭站
                        <small>站点示意图</small>
                    </h3>
                    <ul class="breadcrumb">
                        <li>
                            <i class="icon-home"></i>
                            <a href="index.html">Home</a>
                            <i class="icon-angle-right"></i>
                        </li>
                        <li><a href="#">安亭站</a></li>
                    </ul>
                </div>
            </div>
            <div id="dashboard">
                <div class="row-fluid">
                    <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
                    </div>
                    <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
                        <div class="dashboard-stat green"></div>
                    </div>
                    <div class="span3 responsive" data-tablet="span6  fix-offset" data-desktop="span3">
                    </div>
                    <div class="span3 responsive" data-tablet="span6" data-desktop="span3">
                        <div class="dashboard-stat yellow"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
                <div class="row-fluid">
                    <img src="media/image/anting_part1.png" id="anting_part1_img">
                    <canvas id="mycanvas">Your browser does not support the HTML5 canvas tag.</canvas>
                </div>
                <div class="row-fluid">
                    <div class="span12">
                        <div class="portlet solid bordered light-grey">
                            <div class="portlet-title">
                                <div class="caption"><i class="icon-bar-chart"></i>Site Visits</div>
                            </div>
                            <div class="portlet-body">
                                <div id="site_statistics_loading">
                                    <img src="media/image/loading.gif" alt="loading"/>
                                </div>
                                <div id="site_statistics_content" class="hide">
                                    <div id="site_statistics" class="chart"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="footer-inner">
        Copyright©2016 SUNWISE.SH.CN. All Rights Reserved. <a target="_blank" href="http://www.sunwise.sh.cn/">上海舜华新能源系统有限公司</a>版权所有.
    </div>
    <div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
    </div>
</div>
<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>

<![endif]-->
<script src="media/js/app.js" type="text/javascript"></script>
<script>
    $("#anting_part1_img").on("load",function() {

    });

   jQuery(document).ready(function () {
       var canvas=document.getElementById("mycanvas");
       var img=document.getElementById("anting_part1_img");
       canvas.width = img.width;
       canvas.height = img.height;
       var ration1=img.width/img.naturalWidth;
       var ration2=img.height/img.naturalHeight;
       var context=canvas.getContext("2d");

       context.scale(ration1,ration1);
       context.drawImage(img, 0, 0);
       context.font="20px Georgia";
       context.fillText("0.145%",300,70);
       context.fillText("0.116%",300,140);
       context.fillText("150.0 Bar",550,140);
       context.fillText("-1.0 Bar",750,200);
       context.fillText("26.5 ℃",840,200);
       context.fillText("405 Bar",930,200);
       context.fillText("411 Bar",910,420);
       context.fillText("24 ℃",840,420);
       context.fillText("3.0 Bar",730,420);
       context.fillText("0.145% ",730,530);
       context.fillText("-0.029% ",1120,130);
       $("#anting_part1_img").css('display','none');
        App.init(); // initlayout and core plugins
    });
</script>
</body>
</html>
