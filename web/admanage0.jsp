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
    <li><a href="admanage.jsp">用户管理</a></li>
    <li><a href="#">用户信息</a></li>
  </ul>
</div>

<div class="rightinfo">

  <div class="tools">


    <ul class="seachform1">
      <li><label>用户名</label><input type="text" name="user" id="user" class="scinput1"></li>
      <li><label>管理员类型</label>
        <select class="select3 scinput1" name="adType" id="adType">
          <option value="2">全部</option>
          <option value="1">超级管理员</option>
          <option value="0">普通管理员</option>
        </select>
      </li>
      <li>
        <button type="button" class="scbtn" style="width: 60px; height: 30px;" onclick="getpages()"> 搜索 </button>
      </li>
    </ul>

  </div>


  <table class="tablelist">
    <thead>
    <tr>
      <th>ID</th>
      <th>用户名</th>
      <th>密码</th>
      <th>类型</th>
    </tr>
    </thead>
    <tbody id="tb">
    <%--        <tr>--%>
    <%--          <td></td>--%>
    <%--          <td>王金平幕僚：马英九声明字字见血 人活着没意思</td>--%>
    <%--          <td>admin</td>--%>
    <%--          <td>type字段值为1显示超级管理员，值为0是普通管理员</td>--%>
    <%--          <td>--%>
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



</div>

<script type="text/javascript">
  $('.tablelist tbody tr:odd').addClass('odd');
</script>
<script src="js/jquery.js"></script>
<script>
  $(function () {
    getpages();
  });


  function getpages() {
    //获取搜索条件
    var user=$("#user").val();
    var adType=$("#adType").val();
    $.getJSON("adminServlet", {'adType' : adType, 'user' : user}, function (data) {
      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<tr>";
        str += "<td>" + data[i].id + "</td>";
        str += "<td>" + data[i].username + "</td>";
        str += "<td>" + data[i].pwd + "</td>";
        if (data[i].type == 1){
          str += "<td style='color: red'>超级管理员</td>";
        } else if (data[i].type == 0) {
          str += "<td>普通管理员</td>";
        }
        // str += "<td><a href='#' onclick= 'return mydelete(" + data[i].id + "," + data[i].type + ")'" +
        //         " style='color: rebeccapurple'>删除</a></td>";
        str += "</tr>";
      }
      $("#tb").html(str);
    });
  }


  // //删除
  // function mydelete(id,type) {
  //   var isDel = confirm("你确定要删除吗？");
  //   if(isDel){
  //     $.ajax({
  //       url: "adminServlet",
  //       type: "GET",
  //       data: {'adType':type,tag:'del','id':id},
  //       success: function (data) {
  //         if(data === "success"){
  //           getpages();
  //           alert("用户删除成功！");
  //           // window.location.href = "admanage.jsp";
  //         } else if (data === "failure") {
  //           getpages();
  //           alert("超级管理员不可删除！");
  //           return false;
  //         }
  //       }
  //     });
  //   }
  //   return false;
  // };

  // $.getJSON("adminServlet",{adType:type,tag:'del','id':id},function (data){
  //   if(data === "success"){
  //     get();
  //     alert("用户删除成功！");
  //     window.location.href = "admanage.jsp";
  //   } else if (data === "failure") {
  //     alert("超级管理员不可删除！");
  //     return false;
  //   }
  // });
</script>


</body>

</html>