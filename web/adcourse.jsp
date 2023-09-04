<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>无标题文档</title>
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui-v2.5.6/css/layui.css" />
  <script type="text/javascript" src="js/jquery.js"></script>
  <script src="https://www.layuicdn.com/layui-v2.5.6/layui.js"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/blueimp-file-upload/10.7.0/js/jquery.fileupload.min.js"></script>


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
    <li><a href="adcourse.jsp">课程管理</a></li>
    <li><a href="adcourse.jsp">课程信息</a></li>
  </ul>
</div>

<div class="rightinfo">

  <div class="tools">

    <ul class="toolbar">
      <li class="click"><span><img src="images/t01.png" /></span>添加</li>
    </ul>

    <ul class="seachform1">
      <li><label>课程名称</label><input type="text" name="couName" id="couName" class="scinput1"></li>
      <li><label>所属年级</label>
        <select class="select3 scinput1" name="grade" id="grade">
          <option value="0">全部</option>

        </select>
      </li>
      <li>
        <button type="button" class="scbtn" style="width: 60px; height: 30px;" onclick="getpages(1)"> 搜索 </button>
      </li>
    </ul>

  </div>


  <table class="imgtable">

    <thead>
    <tr>
      <th>课程编号</th>
      <th width="100px;">缩略图</th>
      <th>课程名称</th>
      <th>课时</th>
      <th>所属年级</th>
      <th>操作</th>
    </tr>
    </thead>

    <tbody id="tb">

    <%--        <tr>--%>
    <%--          <td></td>--%>
    <%--          <td class="imgtd"><img src="images/img11.png" /></td>--%>
    <%--          <td>非常不错的国外后台模板，支持HTML5</td>--%>
    <%--          <td>后台界面</td>--%>
    <%--          <td>开放浏览</td>--%>
    <%--          <td><a href="courseEdit.jsp" target="rightFrame" class="tablelink">编辑</a>&nbsp;&nbsp;--%>
    <%--            <a href="#" class="tablelink">删除</a></td>--%>
    <%--        </tr>--%>

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


  <div class="tip" style="height: 510px">
    <div class="tiptop"><span>添加课程</span><a></a></div>

    <form action="courseServlet?tag=add" method="post" id="personalForm">
      <div class="tipinfo" style="height: 360px;">
        <div style="margin: 20px 0;">
          <label for="addcourseName" style="font-size: 16px;">课程名称：</label>
          <input type="text" name="addcourseName" id="addcourseName" class="layui-input" style="width: 350px;">
        </div>
        <div>
          <label for="previewImg" style="font-size: 16px;">课程图片:</label><br>
          <img name="previewImg" id="previewImg"
               src="fileImages/" width="100px" height="100px">
          <input type="file" id="upImg" style="position: absolute; bottom: 220px; left: 180px"
                 onchange="handleFileSelect(event)">
          <i style="padding-left: 2px"></i>
        </div>
        <div style="margin: 20px 0;">
          <label for="chour" style="font-size: 16px;">课 时：</label>
          <input type="text" name="chour" id="chour" class="layui-input" style="width: 350px;">
        </div>
        <div style="margin: 20px 0;">
          <label style="font-size: 16px;">所属年级：</label>
          <select class="select3 scinput1" name="addgrade" id="addgrade">
            <option value="0">全部</option>

          </select>
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
    //获取年级信息
    $.getJSON("gradeServlet", function (data) {
      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].gId + "'>" + data[i].gName + "</option>";
      }
      $("#grade").append(str);
      getpages(1);
    });
    //添加页获取年级信息
    $.getJSON("gradeServlet", function (data) {
      var str = "";
      for (var i = 0; i < data.length; i++) {
        str += "<option value='" + data[i].gId + "'>" + data[i].gName + "</option>";
      }
      $("#addgrade").append(str);

    });



  })
  var pageIndex=1;
  function getpages(index){
    //获取搜索条件--科目名称
    var cName=$("#couName").val();
    //获取搜索条件--年级
    var gId=$("#grade").val();
    //请求第一页数据
    $.getJSON("courseServlet",{"index":index,"gId":gId,"cName":cName},function (data){
      $("#tb").html();
      var str="";
      for(var i=0;i<data.list.length;i++){
        str+="<tr>";
        str+="<td>"+data.list[i].cId+"</td>";
        str+="<td>" + "<img src='fileImages/" + data.list[i].cImage + "'>" + "</td>";
        str+="<td>"+data.list[i].cName+"</td>";
        str+="<td>"+data.list[i].hour+"</td>";
        str+="<td>"+data.list[i].grade.gName+"</td>";
        str+="<td><a href='courseServlet?tag=edit&cId="+data.list[i].cId+"'>编辑</a> | <a href='#' onclick= 'return mydelete("+data.list[i].cId+")'>删除</a></td>";
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
  function  mydelete(cId) {
    var isDel=confirm("你确定要删除吗");
    if(isDel){
      $.getJSON("courseServlet",{tag:'del',cId:cId},function (data){
        if(data==1){
          getpages(pageIndex);
        }
      })
    }return false;
  };


  function handleFileSelect(event) {
    var fileInput = event.target;
    var file = fileInput.files[0]; // 获取选择的文件（假设只选择了一个文件）

    // 显示预览图像
    var imgElement = document.getElementById('previewImg');
    var reader = new FileReader();
    reader.onload = function (e) {
      imgElement.src = e.target.result;
    };
    reader.readAsDataURL(file);

    // 延迟一定时间后执行上传操作
    setTimeout(function () {
      uploadFile(file);
    }, 500); // 0.5秒后执行上传操作（单位为毫秒）
  }

  function uploadFile(file) {
    var form = document.getElementById('personalForm');
    var formData = new FormData(form);
    formData.append('file', file); // 将选择的文件添加到FormData对象中

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'fileUploadServlet?tag=cou', true);
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status === 200) {
        // 上传成功后的操作
        console.log('文件上传成功！');
      }
    };
    xhr.send(formData);
  }
</script>

</body>

</html>