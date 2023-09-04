package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.dao.impl.StudentDaoImpl;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Auther: 范榆林
 * @Date: 2023/7/2 16:04
 * @Description:
 */
@WebServlet(name = "AdStuLeaveServlet", value = "/adStuLeaveServlet")
public class AdStuLeaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String tag=request.getParameter("tag");
        StuLeaveService stuLeaveService=new StuLeaveServiceImpl();

        if("update".equals(tag)){
            int approval=Integer.parseInt(request.getParameter("approval"));
            StuLeave stuLeave= stuLeaveService.findId(approval);
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
        }
        else if("agree".equals(tag)){
            int id=Integer.parseInt(request.getParameter("stuNo"));
            Student student = new StudentServiceImpl().findById(id);

            String startTime = request.getParameter("start");
            String endTime = request.getParameter("end");
            int approval=1;

            StuLeave stuLeave = new StuLeave();
            stuLeave.setStudent(student);
            stuLeave.setApproval(approval);
            stuLeave.setStartTime(startTime);
            stuLeave.setEndTime(endTime);

            stuLeaveService.update(stuLeave);
            response.sendRedirect("adstuLeave.jsp");
        }
        else if("disagree".equals(tag)){
            int id=Integer.parseInt(request.getParameter("stuNo"));
            Student student = new StudentServiceImpl().findById(id);

            String startTime = request.getParameter("start");
            String endTime = request.getParameter("end");
            int approval=2;

            StuLeave stuLeave = new StuLeave();
            stuLeave.setStudent(student);
            stuLeave.setApproval(approval);
            stuLeave.setStartTime(startTime);
            stuLeave.setEndTime(endTime);

            stuLeaveService.update(stuLeave);
            response.sendRedirect("adstuLeave.jsp");
        }


        else {
            int index = 1;  //默认是第1页
            int size = 5;  // 每页大小
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }

            int approval = -1;
            if (request.getParameter("approval") != "") {
                approval = Integer.parseInt(request.getParameter("approval"));
            }
            String name = "";
            if (request.getParameter("stuName") != null) {
                name = request.getParameter("stuName");
            }
            int classId=0;
            String classId1 = request.getParameter("classId");
            if (classId1 != "") {
                classId = Integer.parseInt(request.getParameter("classId"));
            }


            PageUtil<StuLeave> pageUtil = new StuLeaveServiceImpl().adpages(index, size, approval, name,classId);
            String json = JSON.toJSONString(pageUtil);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        }
    }
}
