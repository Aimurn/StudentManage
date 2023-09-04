<%@ page import="com.xmx.pojo.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(function () {
            //导航切换
            $(".menuson .header").click(function () {
                var $parent = $(this).parent();
                $(".menuson>li.active").not($parent).removeClass("active open").find('.sub-menus').hide();

                $parent.addClass("active");
                if (!!$(this).next('.sub-menus').size()) {
                    if ($parent.hasClass("open")) {
                        $parent.removeClass("open").find('.sub-menus').hide();
                    } else {
                        $parent.addClass("open").find('.sub-menus').show();
                    }


                }
            });

            // 三级菜单点击
            $('.sub-menus li').click(function (e) {
                $(".sub-menus li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function () {
                var $ul = $(this).next('ul');
                $('dd').find('.menuson').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('.menuson').slideUp();
                } else {
                    $(this).next('.menuson').slideDown();
                }
            });
        })	
    </script>


</head>

<body style="background:#f0f9fd;">
    <div class="lefttop"><span></span>选项列表</div>

    <dl class="leftmenu">
        <dd>
            <div class="title">
                <span><img src="images/ico01.png" width="16px" height="16px" /></span>
                <a href="main.jsp" target="_parent">首页</a>
            </div>
        </dd>

        <dd>
            <div class="title">
                <span><img src="images/i07.png" width="16px" height="16px" /></span>
                <a href="adpersonal.jsp" target="rightFrame">个人资料</a>
            </div>
        </dd>

        <%
            Object userObject = session.getAttribute("user");
            String href = "";
            if (userObject instanceof User) {
                User user = (User) userObject;
                if (user.getType() == 1) {
                    href += "admanage.jsp";
                }
                if (user.getType() == 0) {
                    href += "admanage0.jsp";
                }
            }
        %>

        <dd>
            <div class="title">
                <span><img src="images/icon13.png" width="16px" height="16px" /></span>用户管理
            </div>
            <ul class="menuson">
                <li><cite></cite><a href="<%=href%>" target="rightFrame">用户信息</a><i></i></li>
            </ul>
        </dd>

        <dd>
            <div class="title">
                <span><img src="images/l02.png" width="16px" height="16px" /></span>年级管理
            </div>
            <ul class="menuson">
                <li>
                    <div class="header">
                        <cite></cite>
                        <a href="adgrade.jsp" target="rightFrame">年级信息</a>
                        <i></i>
                    </div>
                </li>
            </ul>
        </dd>


        <dd>
            <div class="title">
                <span><img src="images/icon16.png" width="16px" height="16px" /></span>班级管理
            </div>
            <ul class="menuson">
                <li><cite></cite><a href="adclass.jsp" target="rightFrame">班级信息</a><i></i></li>
            </ul>
        </dd>


        <dd>
            <div class="title">
                <span><img src="images/icon01.png" width="16px" height="16px" /></span>课程管理
            </div>
            <ul class="menuson">
                <li><cite></cite><a href="adcourse.jsp" target="rightFrame">课程信息</a><i></i></li>
            </ul>
        </dd>
        <dd>
            <div class="title">
                <span><img src="images/lc02.png" width="16px" height="16px" /></span>学生管理
            </div>
            <ul class="menuson">
                <li><cite></cite><a href="adstudent.jsp" target="rightFrame">学生信息</a><i></i></li>
                <li><cite></cite><a href="adscore.jsp" target="rightFrame">成绩信息</a><i></i></li>
                <li><cite></cite><a href="adstuLeave.jsp" target="rightFrame">请假审批</a><i></i></li>
            </ul>
        </dd>

    </dl>

</body>

</html>