<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" />
    <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>



</head>

<body>

    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="adindex.jsp">首页</a></li>
            <li><a href="#">学生管理</a></li>
            <li><a href="adstuLeave.jsp">请假审批</a></li>
            <li><a href="#">审批</a></li>
        </ul>
    </div>

    <div class="formbody">

        <div class="formtitle"><span>请假审批</span></div>

        <ul class="forminfo">
            <li><label>学号</label><input name="stuNo" id="stuNo" type="text" class="dfinput" readonly /></li>
            <li><label>姓名</label><input name="stuName" id="stuName" type="text" class="dfinput" readonly /></li>
            <li><label>所属班级</label><input name="stuclass" id="stuclass" type="text" class="dfinput" readonly /></li>
            <li><label>请假原因</label><input name="reason" id="reason" type="text" class="dfinput" readonly /></li>
            <li><label>开始时间</label><input name="startTime" id="startTime" type="text" class="dfinput" readonly /></li>
            <li><label>结束时间</label><input name="endTime" id="endTime" type="text" class="dfinput" readonly /></li>
            <li><label>审批状态</label>
                <input type="radio" name="approval" value="1"><i style="font-size: 14px; font-style: normal;">同意
                </i>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name="approval" value="2"><i style="font-size: 14px; font-style: normal;">不同意
                </i>
            </li>
            <li><label>&nbsp;</label><input name="" type="button" class="btn" value="确认保存" /></li>
        </ul>


    </div>

    <script>
        layui.use('laydate', function () {
            var laydate = layui.laydate;
            laydate.render({
                elem: '#born',
                type: 'date',
                trigger: 'click'
            });
        });

    </script>
</body>

</html>