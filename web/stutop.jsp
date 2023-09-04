<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script type="text/javascript">
        $(function () {
            //顶部导航切换
            $(".nav li a").click(function () {
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
        })	
    </script>


</head>

<body style="background:url(images/topbg.gif) repeat-x;">

    <div class="topleft">
        <a href="stumain.jsp" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
    </div>

    <div class="topright">
        <ul>
            <li><span><img src="images/help.png" title="帮助" class="helpimg" /></span><a href="#">帮助</a></li>
            <li><a href="#">关于</a></li>
            <li><a href="loginServlet?tag=logOut" target="_parent">退出</a></li>
        </ul>

        <div class="user">
            <span><a href="stupersonal.jsp" target="rightFrame">个人中心</a></span>
        </div>

    </div>

</body>

</html>