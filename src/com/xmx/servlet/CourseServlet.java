package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.dao.impl.CourseDaoImpl;
import com.xmx.pojo.Course;
import com.xmx.pojo.Grade;
import com.xmx.service.CourseService;
import com.xmx.service.GradeService;
import com.xmx.service.impl.CourseServiceImpl;
import com.xmx.service.impl.GradeServiceImpl;
import com.xmx.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CourseServlet", value = "/courseServlet")
public class CourseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String tag = request.getParameter("tag");
        CourseService courseService = new CourseServiceImpl();
        if ("add".equals(tag)) {
            //有图片
            String cName = request.getParameter("addcourseName");
            int hour = Integer.parseInt(request.getParameter("chour"));
            int gId = Integer.parseInt(request.getParameter("addgrade"));

            Grade grade = new Grade();
            grade.setgId(gId);

            Course course = new Course();
            course.setcName(cName);
            course.setHour(hour);
            course.setGrade(grade);

            int result =  new CourseDaoImpl().updateByImg(course);
            if (result > 0) {
                response.sendRedirect("adcourse.jsp");
            } else {
                response.sendRedirect("adcourse.jsp");
            }

        } else {

           int cid=Integer.parseInt(request.getParameter("couId"));
           String cname = request.getParameter("couName");

           int hour = Integer.parseInt(request.getParameter("chour"));

           int gid = Integer.parseInt(request.getParameter("grade"));
           Grade grade = new Grade();
           grade.setgId(gid);

           Course course =
                   new Course(cid,cname,hour,grade);
           if ("save".equals(tag)) {
               if (courseService.add(course) > 0) {
                   response.sendRedirect("adcourse.jsp");
               } else {
                   response.sendRedirect("courseEdit.jsp");
               }
           } else if ("update".equals(tag)) {
               int id = Integer.parseInt(request.getParameter("couId"));
               course.setcId(id);
               courseService.update(course);
               response.sendRedirect("adcourse.jsp");
           }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        CourseService courseService = new CourseServiceImpl();
        String tag = request.getParameter("tag");
        if ("edit".equals(tag)) {
            int id = Integer.parseInt(request.getParameter("cId"));
            Course course = courseService.findByCourseId(id);
            //取年级信息
            GradeService gradeService = new GradeServiceImpl();
            List<Grade> list = gradeService.getGrades();
            //数据如何传递到页面
            request.setAttribute("grades", list);
            request.setAttribute("course", course);
            request.getRequestDispatcher("courseEdit.jsp").forward(request, response);

        } else if ("del".equals(tag)) {  //删除
            int id = Integer.parseInt(request.getParameter("cId"));
            int result = courseService.del(id);
            PrintWriter out = response.getWriter();
            //向客户端写出数据
            out.write(result + "");
            out.close();

        } else if ("list".equals(tag)) {
            //为其他页面提供下拉框
            List<Course> list = new CourseServiceImpl().getCourse();
            //返回Json格式的数据
            String json = JSON.toJSONString(list);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();

        } else {
            int index = 1;  //默认是第1页
            int size = 10;  // 每页大小
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }
            //从session 中取搜索条件
            String name = "";
            if (request.getParameter("cName") != null) {
                name = request.getParameter("cName");
            }

            //取搜索条件
            int gid = 0;
            if (request.getParameter("gId") != null) {
                gid = Integer.parseInt(request.getParameter("gId"));
            }

            System.out.println(gid + "--------------");
            System.out.println(name);
            PageUtil<Course> pageUtil = courseService.pages(index, size, name, gid);
            String json = JSON.toJSONString(pageUtil);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        }
    }
}

