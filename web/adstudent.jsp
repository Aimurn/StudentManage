<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>学生</title>
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" />
  <script type="text/javascript" src="js/jquery.js"></script>
  <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>


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
    <li><a href="adindex.html">首页</a></li>
    <li><a href="#">学生管理</a></li>
    <li><a href="adstudent.jsp">学生信息</a></li>
  </ul>
</div>

<div class="rightinfo">

  <div class="tools">

    <ul class="toolbar">
      <li class="click"><span><img src="images/t01.png" /></span>添加</li>
    </ul>

    <ul class="seachform1" style="width: 100%;">
      <li><label>学号</label><input type="text" name="stuNo" id="stuNo" class="scinput1"></li>
      <li><label>姓名</label><input type="text" name="stuName" id="stuName" class="scinput1"></li>
      <li><label>所属年级</label>
        <select class="select3 scinput1"  id="grade" onchange="classBygid()">
          <option value="0" selected>全部</option>
        </select>
      </li>
      <li><label>所属班级</label>
        <select class="select3 scinput1" id="stuclass">

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
      <th width="100px;">头像</th>
      <th>密码</th>
      <th>性别</th>
      <th>出生日期</th>
      <th>年级</th>
      <th>班级</th>
      <th>联系电话</th>
      <th>地址</th>
      <th>身份证</th>
      <th>操作</th>
    </tr>
    </thead>
    <%--     // //////////////////////////////////////////////////////////////////////////////////////////     --%>

    <tbody id="tb">

    <!--        <tr>-->
    <!--          <td></td>-->
    <!--          <td>xxx</td>-->
    <!--          <td class="imgtd"><img src="images/img11.png" /></td>-->
    <!--          <td>MD5加密的密码</td>-->
    <!--          <td>男</td>-->
    <!--          <td>年月日</td>-->
    <!--          <td>年级</td>-->
    <!--          <td>班级</td>-->
    <!--          <td>联系电话</td>-->
    <!--          <td>地址</td>-->
    <!--          <td>身份证</td>-->
    <!--          <td><a href="studentEdit.html" target="rightFrame" class="tablelink">编辑</a>&nbsp;&nbsp;<a href="#"-->
    <!--              class="tablelink">删除</a>-->
    <!--          </td>-->
    <!--        </tr>-->
    <%--///////////////////////////////////////////////////////////////////////////////////////////--%>
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


  <div class="tip" style="height: 650px">
    <form action="studentServlet?tag=save" method="post">
      <div class="tiptop"><span>添加学生</span><a></a></div>
      <div class="tipinfo" style="height: 500px;">
        <div style="margin: 6px 0;">
          <label for="addstuName" style="font-size: 16px;">姓名：</label>
          <input id="addstuName" name="addstuName" type="text" class="layui-input" style="width: 350px;" />
        </div>
        <div style="margin: 6px 0;">
          <label style="font-size: 16px;">密码：</label>
          <input id="addpwd" name="addpwd" type="password" class="layui-input" style="width: 350px;" />
        </div>
        <div style="margin: 6px 0;">
          <label style="font-size: 16px;">性别：</label>
          <input type="radio" name="addsex" value="男"><i style="font-size: 14px; font-style: normal;">
          男
        </i>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="addsex" value="女"><i style="font-size: 14px; font-style: normal;">
          女
        </i>
        </div>
        <div style="margin: 6px 0;">
          <li><label style="font-size: 16px;">出生日期：</label>
            <input type="text" class="layui-input" name="addborn" id="born" placeholder="" style="width: 350px;">
        </div>
        <div style="margin: 6px 0;">
          <label style="font-size: 16px;">所属年级：</label>
          <select class="select3 scinput1" name="addgrade" id="addgrade" onchange="addclassBygid()" >
            <option selected value="0" >请选择</option>
          </select>
        </div>
        <div style="margin: 6px 0;">
          <label style="font-size: 16px;">所属班级：</label>
          <select class="select3 scinput1" name="addclass" id="addstuclass">

          </select>
        </div>
        <div style="margin: 6px 0;">
          <label style="font-size: 16px;">联系电话：</label>
          <input id="tel" name="addtel" type="text" class="layui-input" style="width: 350px;" />
        </div>
        <div style="margin: 6px 0;">
          <label style="font-size: 16px;">地址：</label>
          <input id="address" name="addaddress" type="text" class="layui-input" style="width: 350px;" />
        </div>
        <div style="margin: 6px 0;">
          <label style="font-size: 16px;">身份证：</label>
          <input id="idcard" name="addidcard" type="text" class="layui-input" style="width: 350px;" />
        </div>
      </div>

      <div class="tipbtn">
        <input name="" type="submit" class="sure" value="确定" />&nbsp;
        <input name="" type="button" class="cancel" value="取消" />
      </div>
    </form>
  </div>


</div>

<script src="js/jquery.js"></script>
<script type="text/javascript">
  $('.tablelist tbody tr:odd').addClass('odd');
</script>
<script>
  layui.use('laydate', function () {
    var laydate = layui.laydate;
    laydate.render({
      elem: '#born',
      type: 'date',
      trigger: 'click'
    });
  });

  $(function () {
    //发Ajax请求取年级信息
    $.getJSON("gradeServlet", function (data) {
      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].gId + "'>" + data[i].gName + "</option>";
      }
      $("#grade").append(str);
    });
    //发Ajax请求取班级信息
    // $.getJSON("classesServlet",{tag:'list'} ,function (data) {
    //   var str = "";
    //   str += "<option value='0'>全部</option>";
    //   for (var i = 0; i < data.length; i++) {
    //     str += "<option value='" + data[i].classId + "' >" + data[i].className + "</option>";
    //   }
    //   $("#stuclass").append(str);
    // });
    classBygid();
    getpages(1);
  });

  //年级编号找班级
  function classBygid() {
    var gId = $("#grade").val();
    //console.log(gId)
    $.getJSON("classesServlet",{tag:'option',gId:gId} ,function (data) {
      //console.log(data)
      var str = "";
      str += "<option value='0' selected>全部</option>";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].classId + "'>" + data[i].className + "</option>";
      }
      $("#stuclass").html(str);
    });
    //getpages(1);
  }

  //删除
  function  mydelete(stuNo) {
    var isDel=confirm("你确定要删除吗");
    if(isDel){
      $.getJSON("studentServlet",{tag:'del',stuNo:stuNo},function (data){
        if(data==1){
          getpages(pageIndex);
        }
      })
    }return false;
  };


  var pageIndex=1;
  function getpages(index){
    //获取搜索条件
    var gId=$("#grade").val();
    var stuName=$("#stuName").val();
    var stuNo=$("#stuNo").val();
    var classId=$("#stuclass").val() === null ? 0:$("#stuclass").val();
    console.log("classId=" + classId," type=" + typeof classId)
    //请求第一页数据
    $.getJSON("studentServlet",{"index":index,"gId":gId,"stuName":stuName,"stuNo":stuNo,"classId":classId},function (data){
      $("#tb").html();
      var str="";
      for(var i=0;i<data.list.length;i++){
        str+="<tr>";
        str+="<td>"+data.list[i].stuNo+"</td>";
        str+="<td>"+data.list[i].stuName+"</td>";
        str+="<td>" + "<img width='100px'; height='90px' style src='fileImages/" + data.list[i].stuImg + "'>" +
                "</td>";
        str+="<td>"+data.list[i].pwd+"</td>";
        str+="<td>"+data.list[i].sex+"</td>";
        str+="<td>"+data.list[i].born+"</td>";
        str+="<td>"+data.list[i].grade.gName+"</td>";
        str+="<td>"+data.list[i].classes.className+"</td>";
        str+="<td>"+data.list[i].phone+"</td>";
        str+="<td>"+data.list[i].address+"</td>";
        str+="<td>"+data.list[i].idCard+"</td>";
        str+="<td><a  href='studentServlet?tag=edit&stuNo="+data.list[i].stuNo+"'>编辑</a> | <a href='#' onclick= 'return mydelete("+data.list[i].stuNo+")'>删除</a></td>";
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

  $(function () {
    //发Ajax请求取年级信息
    $.getJSON("gradeServlet", function (data) {
      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].gId + "'>" + data[i].gName + "</option>";
      }
      $("#addgrade").append(str);
    });
    //发Ajax请求取班级信息
    // $.getJSON("classesServlet",{tag:'list'} ,function (data) {
    //   var str = "";
    //   str += "<option value='0'>全部</option>";
    //   for (var i = 0; i < data.length; i++) {
    //     str += "<option value='" + data[i].classId + "' >" + data[i].className + "</option>";
    //   }
    //   $("#stuclass").append(str);
    // });
    addclassBygid();

  });

  //年级编号找班级
  function addclassBygid() {
    var gId = $("#addgrade").val();
    //console.log(gId)
    $.getJSON("classesServlet",{tag:'option',gId:gId} ,function (data) {
      //console.log(data)
      var str = "";
      str += "<option value='0' selected>全部</option>";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].classId + "'>" + data[i].className + "</option>";
      }
      $("#addstuclass").html(str);
    });
    //getpages(1);
  }
</script>
</body>

</html>