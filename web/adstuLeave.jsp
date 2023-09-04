<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>无标题文档</title>
  <link href="css/style.css" rel="stylesheet" type="text/css" />
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
    <li><a href="adindex.jsp">首页</a></li>
    <li><a href="#">学生管理</a></li>
    <li><a href="adstuLeave.jsp">请假审批</a></li>
  </ul>
</div>

<div class="rightinfo">

  <ul class="seachform1" style="width: 100%;">
    <li><label>学生姓名</label><input type="text" name="stuName" id="stuName" class="scinput1"></li>
    <li><label>班级名称</label>
      <select class="select3 scinput1" name="stuclass" id="stuclass">
        <option value="0">全部</option>
      </select>
    </li>
    <li><label>审批状态</label>
      <select class="select3 scinput1" name="approval" id="approval">
        <option value="-1">全部</option>
        <option value="0">待审核</option>
        <option value="1">同意</option>
        <option value="2">不同意</option>
      </select>
    </li>
    <li>
      <button type="button" class="scbtn" style="width: 60px; height: 30px;" onclick="getpages(1)"> 搜索 </button>
    </li>
  </ul>

  <table class="tablelist">
    <thead>
    <tr>
      <th>学号</th>
      <th>学生姓名</th>
      <th>班级名称</th>
      <th>请假原因</th>
      <th>开始时间</th>
      <th>结束时间</th>
      <th>审批状态</th>
      <th>操作</th>
    </tr>
    </thead>
    <tbody id="tb">
    <%--        <tr>--%>
    <%--          <td>20130908</td>--%>
    <%--          <td>王金平幕僚：马英九声明字字见血 人活着没意思</td>--%>
    <%--          <td>admin</td>--%>
    <%--          <td>江苏南京</td>--%>
    <%--          <td>2013-09-09 15:05</td>--%>
    <%--          <td>2013-09-09 15:05</td>--%>
    <%--          <td>0为待审核，1为同意，2为不同意</td>--%>
    <%--          <td><a href="approvalEdit.jsp" class="tablelink">审批</a>&nbsp;&nbsp;<a href="#" class="tablelink">--%>
    <%--              删除</a></td>--%>
    <%--        </tr>--%>


    </tbody>
  </table>


  <div class="pagin">
    <div class="message"></div>
    <%--      <div class="message">共<i class="blue"></i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>--%>
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
<script src="js/jquery.js"></script>
<script>

  $(function (){
    //发Ajax请求取班级信息
    $.getJSON("classesServlet",{tag:'list'} ,function (data) {
      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].classId + "' >" + data[i].className + "</option>";
      }
      $("#stuclass").append(str);
    })
    getpages(1);

  })

  var pageIndex=1;
  function getpages(index){
    //获取搜索条件
    var stuName=$("#stuName").val();
    var classId=$("#stuclass").val() === null ? 0 : $("#stuclass").val();
    var approval=$("#approval").val();
    //请求第一页数据
    $.getJSON("adStuLeaveServlet",{"index":index,"stuName":stuName,"classId":classId,"approval":approval},function
            (data){
      $("#tb").html();
      var str="";
      for(var i=0;i<data.list.length;i++){
        str+="<tr>";
        str+="<td>"+data.list[i].student.stuNo+"</td>";
        str+="<td>"+data.list[i].name+"</td>";
        str+="<td>"+data.list[i].classes.className+"</td>";
        str+="<td>"+data.list[i].reason+"</td>";
        str+="<td>"+data.list[i].startTime+"</td>";
        str+="<td>"+data.list[i].endTime+"</td>";
        if (data.list[i].approval == 0){
          str += "<td style='color: orange'>待审批</td>";
        } else if (data.list[i].approval == 1) {
          str += "<td style='color: green'>同 意</td>";
        } else if (data.list[i].approval == 2) {
          str += "<td style='color: red'>不同意</td>";
        }

        str+="<td><a  href='adStuLeaveServlet?tag=agree&stuNo="+data.list[i].student.stuNo+"&start="+data.list[i].startTime+"&end="+data.list[i].endTime+"' " +
                "style='color: green'>同意</a> | <a href='adStuLeaveServlet?tag=disagree&stuNo="+data.list[i].student.stuNo+"&start="+data.list[i].startTime+"&end="+data.list[i].endTime+"' " +
                "style='color: red'>不同意</a></td>";
        str+="</tr>";
      }
      $("#tb").html(str);
      pageIndex=data.pageIndex;//保存页码

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

    })
  }

</script>


</body>


</html>