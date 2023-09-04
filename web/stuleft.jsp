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
                <a href="stumain.jsp" target="_parent">首页</a>
            </div>
        </dd>

        <dd>
            <div class="title">
                <span><img src="images/i07.png" width="16px" height="16px" /></span>
                <a href="stupersonal.jsp" target="rightFrame">个人资料</a>
            </div>
        </dd>
        <dd>
            <div class="title">
                <span><img src="images/ico03.png" width="16px" height="16px" /></span>
                <a href="stuscore.jsp" target="rightFrame">成绩信息</a>
            </div>
        </dd>

        <dd>
            <div class="title">
                <span><img src="images/lc01.png" width="16px" height="16px" /></span>
                <a href="stuLeave.jsp" target="rightFrame">请假申请</a>
            </div>
        </dd>

    </dl>

</body>

</html>