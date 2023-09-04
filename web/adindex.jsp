<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>
    <script language="javascript">
        $(function () {
            $('.loginbox').css({ 'position': 'absolute', 'left': ($(window).width() - 692) / 2 });
            $(window).resize(function () {
                $('.loginbox').css({ 'position': 'absolute', 'left': ($(window).width() - 692) / 2 });
            })
        });  
    </script>
    <style>
        .mainindex {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 50vh;
            /* 可根据需要调整高度 */
        }

        .welinfo {
            text-align: center;
        }
    </style>
</head>


<body
    style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">

    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
        </ul>
    </div>

    <div id="mainBody">
        <div id="cloud1" class="cloud"></div>
        <div id="cloud2" class="cloud"></div>
    </div>

    <div class="mainindex">
        <div class="welinfo">
            <span><img src="images/sun.png" alt="天气" width="50px" height="50px" /></span>
            <b style="font-size: 4em;">欢迎使用学生信息管理系统</b>
            <br>
            <br>
            <br>
            <br>
            <b style="font-size: 4em;">管理员界面</b>
        </div>
    </div>
</body>

</html>