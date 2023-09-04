package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.pojo.Grade;
import com.xmx.service.GradeService;
import com.xmx.service.impl.GradeServiceImpl;
import com.xmx.util.PageUtil;
import com.xmx.pojo.Classes;
import com.xmx.service.ClassesService;
import com.xmx.service.impl.ClassesServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ClassesServlet", value = "/classesServlet")
public class ClassesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        ClassesService classesService = new ClassesServiceImpl();
        String tag = request.getParameter("tag");

        if ("add".equals(tag)) {//添加
            String addclassName = request.getParameter("addclassName");
            int adgid = Integer.parseInt(request.getParameter("addgrade"));
            Grade adgrade = new Grade();
            adgrade.setgId(adgid);

            Classes adclasses = new Classes(addclassName, adgrade);
            if (classesService.add(adclasses) > 0) {
                response.sendRedirect("adclass.jsp");
            } else {
                response.sendRedirect("adclass.jsp");
            }
        } else if ("update".equals(tag)) {
            int cid = Integer.parseInt(request.getParameter("cid"));
            String cname = request.getParameter("cname");
            int adgid = Integer.parseInt(request.getParameter("grade"));
            Grade adgrade = new Grade();
            adgrade.setgId(adgid);

            Classes adclasses =
                    new Classes(cid, cname, adgrade);
            if (classesService.update(adclasses) > 0) {
                response.sendRedirect("adclass.jsp");
            } else {
                response.sendRedirect("classEdit.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        ClassesService classesService = new ClassesServiceImpl();
        String tag = request.getParameter("tag");

        if ("list".equals(tag)) {
            List<Classes> list1 = new ClassesServiceImpl().getClasses();
            //返回Json格式的数据
            String json = JSON.toJSONString(list1);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        } else if ("option".equals(tag)) {

            //取搜索条件
            int gId = 0;
            if (request.getParameter("gId") != null) {
                gId = Integer.parseInt(request.getParameter("gId"));
            }
            List<Classes> list = new ClassesServiceImpl().findClassBygId(gId);
            //返回Json格式的数据
            String json = JSON.toJSONString(list);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        } else if ("del".equals(tag)) {
            int classId = Integer.parseInt(request.getParameter("classId"));
            int result = classesService.del(classId);
            PrintWriter out = response.getWriter();
            //向客户端写出数据
            out.write(result + "");
            out.close();

        } else if ("edit".equals(tag)) {
            int id = Integer.parseInt(request.getParameter("classId"));
            Classes classes = classesService.findByClassId(id);
            //取年级信息
            GradeService gradeService = new GradeServiceImpl();
            List<Grade> list = gradeService.getGrades();

            //数据如何传递到页面
            request.setAttribute("grades", list);
            request.setAttribute("classes", classes);
            request.getRequestDispatcher("classEdit.jsp").forward(request, response);
        } else {
            int index = 1;
            int size = 10;
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }
            //取搜索条件
            int gId = 0;
            if (request.getParameter("gId") != null) {
                gId = Integer.parseInt(request.getParameter("gId"));
            }

            PageUtil<Classes> pageUtil = classesService.pages(index, size, gId);
            String json = JSON.toJSONString(pageUtil);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();

        }
    }
}
