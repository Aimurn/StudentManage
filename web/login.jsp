<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
    <script src="js/jquery.js"></script>

<script language="javascript">
    $(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    $(window).resize(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })
});
    function replaceImg() {
        var img = document.getElementById("yzmImg");
        img.src = "getImg?random=" + Math.random();
    }
    //登录身份判断
    function login() {
        var username = $("#username").val();
        var pwd= $("#pwd").val();
        var code = $("#code").val();
        var logType = $("#logType").val();
        console.log(username);
        console.log(pwd);
        console.log(code);
        console.log(logType);
        $.ajax({
            url: "loginServlet",
            type: "POST",
            data: { tag : 'login', username : username, pwd : pwd, logType : logType, code : code },
            success: function(response) {
                console.log(response);
                if (response === "success") {
                    if (logType === "1") {
                        window.location.href = "stumain.jsp";
                    } else if (logType === "2") {
                        window.location.href = "main.jsp";
                    }
                } else if (response === "failure"){
                    alert("登录失败，用户名或密码错误！");
                } else if (response === "typeFailure") {
                    alert("登录失败，请选择用户身份！");
                } else if (response === "yzmFailure") {
                    alert("登录失败，验证码错误！");
                }
            }
        });

    }
</script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>
    </div>

    <div class="loginbody">

    <span class="systemlogo"></span>

    <form class="loginbox loginbox1">

    <ul>
    <li><input id="username" name="username" type="text" class="loginuser" value=""
                style="width: 343px" placeholder="用户名/学号"/></li>
    <li><input id="pwd" name="pwd" type="password" class="loginpwd" value=""
               style="width:
    343px" placeholder="密码"/></li>
    <li><select id="logType" name="logType" class="loginuser" style="width: 343px">
        <option value="0">请选择身份</option>
        <option value="1">学生</option>
        <option value="2">管理员</option>
    </select>
    </li>
    <li class="yzm">
    <span><input id="code" name="code" type="text" value="验证码" onclick="JavaScript:this.value=''"
                 style="width: 227px"/></span>
        <img id="yzmImg" src="getImg" title="点击更换验证码" onclick="replaceImg()" width="114px" height="46px">
        <span style="color: red; height: 20px">${msg}</span>
    </li>
    <li><input name="" type="button" class="loginbtn" value="登录" onclick="login()"/>
    </ul>


    </form>

    </div>


    <div class="loginbm">版权所有  2014  <a href="http://www.uimaker.com">uimaker.com</a>  仅供学习交流，勿用于任何商业用途</div>


</body>

</html>
