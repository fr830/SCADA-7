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
    <meta charset="utf-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="X-UA-Compatible" content="IE=9">
    <title>舜华能源 | 数据监控系统</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- BEGIN GLOBAL MANDATORY STYLES -->
    <link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
    <link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
    <!-- END GLOBAL MANDATORY STYLES -->
    <!-- BEGIN PAGE LEVEL STYLES -->
    <link href="media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/daterangepicker.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/fullcalendar.css" rel="stylesheet" type="text/css"/>
    <link href="media/css/jqvmap.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="media/css/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <!-- END PAGE LEVEL STYLES -->
    <link rel="shortcut icon" href="media/image/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">
<!-- BEGIN HEADER -->

<div class="row-fluid">
    <img src="media/image/station_yunfu.png" id="station_img">
    <canvas id="mycanvas">Your browser does not support the HTML5 canvas tag.</canvas>
</div>
<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
<script src="media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
<script src="media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
<script src="media/js/bootstrap.min.js" type="text/javascript"></script>
<script src="media/js/app.js" type="text/javascript"></script>
<script>
    function showData() {
        $.ajax({
            url: 'GetDataServlet_Yunfu',
            type: 'POST',
            'success': function (data) {
                var dataJson = jQuery.parseJSON(data);
                var dataArray = dataJson.data;
                var canvas = document.getElementById("mycanvas");
                var img = document.getElementById("station_img");
                canvas.width = img.width;
                canvas.height = img.height;
                var context = canvas.getContext("2d");
                context.scale(0.71, 0.71);
                context.drawImage(img, 0, 0);
                context.font = "23px Microsoft YaHei";
                //context.fillText(dataArray["Pout_H2_1_1"] + "%", 300, 40);//
               // context.fillText(dataArray["ATD_101"] + "%", 300, 100);
                context.fillText(dataArray["Pin_H2_1_1"] + " Bar", 320, 40);
                context.fillText(dataArray["Pin_H2_2_1"] + " Bar", 315, 350);
                context.fillText(dataArray["Pout_H2_1_1"] + " Bar", 590, 90);//
                context.fillText(dataArray["Tout_H2_1_1"] + " ℃", 680, 90);//
                context.fillText(dataArray["P_H_tank_2_1"] + " Bar", 800, 90);//
                context.fillText(dataArray["P_L_TANK_2_1"] + " Bar", 820, 300);//
                context.fillText(dataArray["Tout_H2_2_1"] + " ℃", 690, 300);//
                context.fillText(dataArray["Pout_H2_2_1"] + " Bar", 590, 300);//
              //  context.fillText(dataArray["ATD_105"] + "% ", 710, 510);
                //context.fillText(dataArray["ATD_103"] + "% ", 1090, 90);
                context.fillText(dataArray["AT_A"] + "℃", 690, 470);//
                context.fillText(dataArray["Plinest_A"] + "MPa", 600, 470);//
                context.fillText(dataArray["Llinest_A"] + "KG/MIN ", 770, 470);//
                context.fillText(dataArray["AT_B"] + "℃", 690, 680);//
                context.fillText(dataArray["Plinest_B"] + "MPa", 580, 680);//
                context.fillText(dataArray["Llinest_B"] + "KG/MIN ", 770, 680);//
                $("#station_img").css('display', 'none');
            },
            'error': function (data) {

            }
        });


    }

    jQuery(document).ready(function () {
        showData();
        App.init(); // initlayout and core plugins
    });
    var i = 0
    setInterval(function () {
        showData();
    }, 5000)
</script>
</body>
</html>
