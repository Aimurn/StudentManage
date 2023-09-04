package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.pojo.Classes;
import com.xmx.pojo.StuLeave;
import com.xmx.pojo.Student;
import com.xmx.service.StuLeaveService;
import com.xmx.service.impl.StuLeaveServiceImpl;
import com.xmx.service.impl.StudentServiceImpl;
import com.xmx.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StuLeaveServlet",value = "/stuLeaveServlet")
public class StuLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        StuLeaveService stuLeaveService=new StuLeaveServiceImpl();
        String tag = request.getParameter("tag");
        HttpSession session = request.getSession();

        Student student = (Student) session.getAttribute("user");
        int stuNo = student.getStuNo();

        if (tag == null) {
            //搜索
            int approval=Integer.parseInt(request.getParameter("approval"));
            //把搜索条件存入到Session
            request.getSession().setAttribute("approval",approval);
            List<StuLeave> list=stuLeaveService.getStuLeave();
            //数据如何传递到页面
            request.setAttribute("stuleave", list);
            //带条件分页查询
            PageUtil<StuLeave> pageUtil = stuLeaveService.pages(1, 10,approval, stuNo);
            //数据如何传递到页面
            request.setAttribute("pageUtil", pageUtil);
            //转发
            request.getRequestDispatcher("stuLeave.jsp").forward(request, response);
        }else if("save".equals(tag)){
            int sid = Integer.parseInt(request.getParameter("addstuNo"));
            student=new Student();
            student.setStuNo(sid);

            String name=request.getParameter("addstuName");
            //学号找班级编号
            Student student1 =  new StudentServiceImpl().findById(sid);
            int cid = student1.getClasses().getClassId();

            Classes classes=new Classes();
            classes.setClassId(cid);

            String reason=request.getParameter("addreason");
            String starttime=request.getParameter("addstartTime");
            String endtime=request.getParameter("addendTime");
            // int  approval=Integer.parseInt(request.getParameter("approval"));
            StuLeave stuLeave=new StuLeave(student,name,classes,reason,starttime,endtime);
            int result = stuLeaveService.add(stuLeave);
            if(result > 0){
                response.sendRedirect("stuLeave.jsp");
            }else {
                response.sendRedirect("stuLeave.jsp");
            }
        }else  if("update".equals(tag)){
            int sid=Integer.parseInt(request.getParameter("addstuNo"));
            student=new Student();
            student.setStuNo(sid);

            String name=request.getParameter("addstuName");

            int cid=Integer.parseInt(request.getParameter("addclass"));
            Classes classes=new Classes();
            classes.setClassId(cid);

            String reason=request.getParameter("addreason");
            String starttime=request.getParameter("addstartTime");
            String endtime=request.getParameter("addendTime");
            // int  approval=Integer.parseInt(request.getParameter("approval"));
            StuLeave stuLeave=new StuLeave(student,name,classes,reason,starttime,endtime);
            int approval1=Integer.parseInt(request.getParameter("approval"));
            stuLeave.setApproval(approval1);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StuLeaveService stuLeaveService=new StuLeaveServiceImpl();
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String tag=request.getParameter("tag");
        HttpSession session = request.getSession();

        Student student = (Student) session.getAttribute("user");
        int stuNo = student.getStuNo();

        if("update".equals(tag)){
            int approval=Integer.parseInt(request.getParameter("approval"));
            StuLeave stuLeave=stuLeaveService.findId(approval);
            List<StuLeave> list=stuLeaveService.getStuLeave();
            request.setAttribute("stuleave",list);
            //转发
            request.getRequestDispatcher("approvalEdit.jsp").forward(request,response);
        }else if("del".equals(tag)){
            //删除
            int id=Integer.parseInt(request.getParameter("stuNo"));
            int result=stuLeaveService.del(id);
            PrintWriter out = response.getWriter();
            //向客户端写出数据
            out.write(result + "");
            out.close();
        }else {
            int index = 1;  //默认是第1页
            int size = 4;  // 每页大小
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }
            //从session 中取搜索条件
            int approval = -1;
            if (request.getParameter("approval") != "") {
                approval = Integer.parseInt(request.getParameter("approval"));
            }

            System.out.println(approval + "--------------");

            PageUtil<StuLeave> pageUtil = stuLeaveService.pages(index, size, approval, stuNo);
            String json = JSON.toJSONString(pageUtil);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        }
    }
}
