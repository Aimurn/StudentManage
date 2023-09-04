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
    <li><a href="adindex.jsp">首页</a></li>
    <li><a href="#">学生管理</a></li>
    <li><a href="adscore.jsp">成绩信息</a></li>
  </ul>
</div>

<div class="rightinfo">

  <div class="tools">
    <ul class="toolbar">
      <li class="click"><span><img src="images/t01.png" /></span>添加</li>
    </ul>
    <ul class="seachform1" style="width: 100%;">
      <li><label>学号</label><input type="text" name="stuNo" id="stuNo" class="scinput1"></li>
      <%--        <li><label>姓名</label><input type="text" name="stuName" id="stuName" class="scinput1"></li>--%>
      <li><label>课程名称</label>
        <select class="select3 scinput1" name="stuClass" id="stuClass">
          <option value="0">全部</option>

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
      <th>操作</th>
    </tr>
    </thead>
    <tbody id="tb">
    <%--    <tr>--%>
    <%--      <td>学号</td>--%>
    <%--      <td>学生姓名，需要先发ajax获取学生信息，在加载到这里</td>--%>
    <%--      <td>课程名称，ajax获取课程信息</td>--%>
    <%--      <td>100</td>--%>
    <%--      <td><a href="scoreEdit.jsp" class="tablelink">编辑</a>&nbsp;&nbsp;<a href="#" class="tablelink"> 删除</a></td>--%>
    <%--    </tr>--%>

    </tbody>
  </table>


  <div class="pagin">
    <div class="message"></div>
    <%--      <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>--%>
    <div class="paginList">
      <li class="paginItem"><button><div id="firstpage"></div></button></li>
      <li class="paginItem"><button><div id="prepage"></div></button></li>
      <li class="paginItem"><button><div id="nextpage"></div></button></li>
      <li class="paginItem"><button><div id="lastpage"></div></button></li>
    </div>
  </div>


  <div class="tip" style="height: 400px">
    <div class="tiptop"><span>添加成绩</span><a></a></div>
    <form action="scoreServlet?tag=add" method="post">
      <div class="tipinfo" style="height: 230px;">
        <div style="margin: 20px 0;">
          <label for="addsid" style="font-size: 16px;">学号：</label>
          <input type="text" name="addsid" id="addsid" class="layui-input" style="width: 350px;">
        </div>
        <div style="margin: 20px 0;">
          <label style="font-size: 16px;">课程名称：</label>
          <select class="select3 scinput1" name="addcourse" id="addcourse">
            <option value="0">请选择</option>

          </select>
        </div>
        <div style="margin: 20px 0;">
          <label for="addscore" style="font-size: 16px;">分数：</label>
          <input type="text" name="addscore" id="addscore" class="layui-input" style="width: 350px;">
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
<script src="js/jquery.js"></script>
<script>
  $(function () {
    //获取课程信息
    $.getJSON("courseServlet", {tag:'list'},function (data) {
      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].cId + "'>" + data[i].cName + "</option>";
      }
      $("#stuClass").append(str);
      getpages(1);
    });
    //添加页获取课程信息
    $.getJSON("courseServlet", {tag:'list'},function (data) {

      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].cId + "'>" + data[i].cName + "</option>";
      }
      $("#addcourse").append(str);

    });


  })
  var pageIndex=1;
  function getpages(index){
    //学号
    var no=$("#stuNo").val();
    //课程号
    var cId=$("#stuClass").val();
    //请求第一页数据
    $.getJSON("scoreServlet",{"index":index,"no":no,"cId":cId},function (data){
      $("#tb").html();
      var str="";
      for(var i=0;i<data.list.length;i++){
        str+="<tr>";
        str+="<td>"+data.list[i].student.stuNo+"</td>";
        str+="<td>"+data.list[i].student.stuName+"</td>";
        str+="<td>"+data.list[i].course.cName+"</td>";
        str+="<td>"+data.list[i].score+"</td>";
        str+="<td><a  href='scoreServlet?tag=edit&cId="+data.list[i].course.cId+"&no="+data.list[i].student.stuNo+"&addscore="+data.list[i].score +"'>编辑</a> | <a href='#' onclick='return mydelete("+data.list[i].student.stuNo+","+data.list[i].course.cId+")'>删除</a></td>";
        str+="</tr>";
      }
      $("#tb").html(str);
      pageIndex=data.pageIndex;//保存页码

      //处理分页信息
      var str5="";
      str5 +=  "共<i class='blue'>" + data.pageTotial + "</i>页,共<i class='blue'>" + data.rowNumber + "</i>条记录，当前显示第&nbsp;<i class='blue'>" + data.pageIndex + "</i>页";

      $(".message").html(str5);

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

  //删除
  function  mydelete(no,cId) {
    var isDel=confirm("你确定要删除吗");
    if(isDel){
      $.getJSON("scoreServlet",{tag:'del',no:no,cId:cId},function (data){
        if(data==1){
          getpages(pageIndex);
        }
      })
    }return false;
  };



</script>
</body>

</html>