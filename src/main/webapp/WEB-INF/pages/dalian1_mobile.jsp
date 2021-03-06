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
    <img src="media/image/station_dalian1.png" id="station_img">
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
            url: 'GetDataServlet_Dalian1',
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
                context.fillText(dataArray["MH2"] + "%", 300, 40);
                context.fillText(dataArray["AT"] + "%", 300, 100);
                context.fillText(dataArray["Pinst_MPa"] + " Bar", 530, 90);
                context.fillText(dataArray["Linst"] + " Bar", 710, 150);
                context.fillText(dataArray["Ptarget_MPa"] + " ℃", 780, 240);
                context.fillText(dataArray["Pend_MPa"] + " Bar", 880, 240);
                context.fillText(dataArray["P0_MPa"] + " Bar", 880, 430);
                context.fillText(dataArray["AMH2_Calculation35"] + " ℃", 800, 430);
                context.fillText(dataArray["Pend_bar"] + " Bar", 720, 435);
                context.fillText(dataArray["Ptarget_bar"] + "% ", 710, 510);
                context.fillText(dataArray["WRITEcompenssor"] + "% ", 1090, 90);
                context.fillText(dataArray["Pin450"] + "℃", 100, 270);
                context.fillText(dataArray["Pout450"] + "℃", 980, 480);
                context.fillText(dataArray["Tout450"] + "MPa", 1050, 480);
                context.fillText(dataArray["Toil450"] + "KG/MIN ", 1140, 480);
                context.fillText(dataArray["Pin450_bar"] + "℃", 970, 580);
                context.fillText(dataArray["Pout450_bar"] + "MPa", 1040, 580);
                context.fillText(dataArray["Tout900"] + "KG/MIN ", 1130, 620);
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
