<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>无标题文档</title>
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" />
  <script type="text/javascript" src="js/jquery.js"></script>


  <script type="text/javascript">
    $(document).ready(function () {
      $(".click").click(function () {
        $(".tip").fadeIn(200);
      });

      $(".tiptop a").click(function () {
        $(".tip").fadeOut(200);
      });

      $(".sure").click(function () {
        $(".tip").fadeOut(100);
      });

      $(".cancel").click(function () {
        $(".tip").fadeOut(100);
      });

    });

  </script>

  <style>
    input[type="radio"] {
      width: 18px;
      /* 设置单选框按钮的宽度 */
      height: 18px;
      /* 设置单选框按钮的高度 */
    }
  </style>
</head>


<body>

<div class="place">
  <span>位置：</span>
  <ul class="placeul">
    <li><a href="adindex.jsp">首页</a></li>
    <li><a href="adgrade.jsp">年级管理</a></li>
    <li><a href="#">年级信息</a></li>
  </ul>
</div>

<div class="rightinfo">

  <div class="tools">

    <ul class="toolbar">
      <li class="click"><span><img src="images/t01.png" /></span>添加</li>
    </ul>

  </div>


  <table class="tablelist">
    <thead>
    <tr>
      <th>年级编号</th>
      <th>年级名称</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody id="tb">
    <%--        <tr>--%>
    <%--          <td></td>--%>
    <%--          <td>王金平幕僚：马英九声明字字见血 人活着没意思</td>--%>
    <%--          <td>--%>
    <%--            <a href="gradeEdit.jsp" target="rightFrame" class="tablelink">编辑</a>&nbsp;&nbsp;--%>
    <%--            <a href="#" class="tablelink">删除</a>--%>
    <%--          </td>--%>
    <%--        </tr>--%>

    </tbody>
  </table>


  <%--    <div class="pagin">--%>
  <%--      <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>--%>
  <%--      <div class="paginList">--%>
  <%--        <li class="paginItem"><button><a href="">首页</a></button></li>--%>
  <%--        <li class="paginItem"><button><a href="">上页</a></button></li>--%>
  <%--        <li class="paginItem"><button><a href="">下页</a></button></li>--%>
  <%--        <li class="paginItem"><button><a href="">尾页</a></button></li>--%>
  <%--      </div>--%>
  <%--    </div>--%>


  <div class="tip" style="height: 300px">
    <div class="tiptop"><span>添加年级</span><a></a></div>
    <form action="gradeServlet?tag=add" method="post">
      <div class="tipinfo" style="height: 100px;">
        <div style="margin: 20px 0;">
          <label for="addgradeName" style="font-size: 16px;">年级名称：</label>
          <input type="text" name="addgradeName" id="addgradeName" class="layui-input" style="width: 350px;">
        </div>
      </div>

      <div class="tipbtn">
        <input name="" type="submit" class="sure" value="确定" />&nbsp;
        <input name="" type="button" class="cancel" value="取消" />
      </div>
    </form>
  </div>



</div>

<script type="text/javascript">
  $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>
<script src="js/jquery.js"></script>
<script>
  $(function (){
    get();
  })
  //取数据
  function get() {
    $.getJSON("gradeServlet", function (data) {
      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<tr>";
        str += "<td>" + data[i].gId + "</td>"
        str += "<td>" + data[i].gName + "</td>"
        str += "<td><a  href='gradeServlet?tag=edit&gId=" + data[i].gId + "'>编辑</a> | <a href='#' onclick= 'return mydelete(" + data[i].gId + ")'>删除</a></td>";
        str += "</tr>";
      }
      $("#tb").html(str);
    })
  }

  //删除
  function  mydelete(gId) {
    var isDel=confirm("你确定要删除吗");
    if(isDel){
      $.getJSON("gradeServlet",{tag:'del',gId:gId},function (data){
        if(data==1){
          get();
        }
      })
    }return false;
  };

</script>
</html>