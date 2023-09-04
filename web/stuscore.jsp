<%@ page import="com.xmx.pojo.Student" %>
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


</head>


<body>

  <div class="place">
    <span>位置：</span>
    <ul class="placeul">
      <li><a href="stuindex.jsp">首页</a></li>
      <li><a href="stuscore.jsp">成绩信息</a></li>
    </ul>
  </div>

  <%
      Student student = (Student) session.getAttribute("user");
  %>

  <div class="rightinfo">

    <div class="tools">
      <ul class="seachform1" style="width: 100%;">
        <li><label>课程名称</label>
          <select class="select3 scinput1" name="stuClass" id="stuClass">
            <option value="0">全部</option>
<%--            <option value="">xxx</option>--%>
<%--            <option value="">xxx</option>--%>
          </select>
        </li>
        <li>
          <button type="button" class="scbtn" style="width: 60px; height: 30px;" onclick="getpages(1)"> 搜索 </button>
        </li>
      </ul>
    </div>

    <table class="tablelist">
      <thead>
        <tr>
          <th>学号</th>
          <th>姓名</th>
          <th>课程名称</th>
          <th>分数</th>
        </tr>
      </thead>
      <tbody id="tb">
<%--        <tr>--%>
<%--          <td>学号</td>--%>
<%--          <td>学生姓名，需要先发ajax获取学生信息，在加载到这里</td>--%>
<%--          <td>课程名称，ajax获取课程信息</td>--%>
<%--          <td>100</td>--%>
<%--        </tr>--%>

      </tbody>
    </table>


    <div class="pagin">
      <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
      <div class="paginList">
        <li class="paginItem"><button><div id="firstpage"></div></button></li>
        <li class="paginItem"><button><div id="prepage"></div></button></li>
        <li class="paginItem"><button><div id="nextpage"></div></button></li>
        <li class="paginItem"><button><div id="lastpage"></div></button></li>
      </div>
    </div>



  </div>

  <script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
  </script>

  <script>
    $(function () {
        //Ajax获取课程信息
        $.getJSON("courseServlet", {tag : 'list'}, function (data) {
            var str = "";
            for (var i = 0; i < data.length; i++) {
                str += "<option value='" + data[i].cId +"'>" + data[i].cName + "</option>";
            }
            $("#stuClass").append(str);
        });
        getpages(1);
    });
    var pageIndex=1;
    function getpages(index) {
        //带搜索条件
        var cId = $("#stuClass").val();
        //请求第一页数据
        $.getJSON("stuScoreServlet", {"index" : index, "cId" : cId }, function (data) {
            var str = "";
            var stuNo = <%=student.getStuNo()%>;
            for (var i = 0; i < data.list.length; i++) {
                str += "<tr>";
                if (stuNo === data.list[i].student.stuNo) {
                    str += "<td>" + data.list[i].student.stuNo +"</td>";
                    str += "<td>" + data.list[i].student.stuName + "</td>";
                    str += "<td>" + data.list[i].course.cName + "</td>";
                    str += "<td>" + data.list[i].score + "</td>";
                    str += "</tr>";
                }
            }
            $("#tb").html(str);
            pageIndex = data.pageIndex;//保存页码
            //处理分页信息
            var str="";
            str +=  "共<i class='blue'>" + data.pageTotial + "</i>页,共<i class='blue'>" + data.rowNumber + "</i>条记录，当前显示第&nbsp;<i class='blue'>" + data.pageIndex + "</i>页";
            $(".message").html(str);

            var str1="";
            str1 +=" <a href='#' onclick='getpages(1)'>首页</a>";
            $("#firstpage").html(str1);

            var str2="";
            var gId = data.pageIndex > 1 ? data.pageIndex - 1 : 1;
            str2 += " <a onclick='getpages(" + gId + ")'>上页</a>";
            $("#prepage").html(str2);

            var str3="";
            gId = data.pageIndex < data.pageTotial ? data.pageIndex + 1 : data.pageTotial;
            str3 += " <a onclick='getpages(" + gId + ")'>下页</a>";
            $("#nextpage").html(str3);

            var str4="";
            str4 += " <a onclick='getpages(" + data.pageTotial + ")'>尾页</a>"
            $("#lastpage").html(str4);
        });
    }
  </script>
</body>

</html>