package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.dao.impl.GradeDaoImpl;
import com.xmx.pojo.Grade;
import com.xmx.service.GradeService;
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

@WebServlet(name = "GradeServlet", value = "/gradeServlet")
public class GradeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String tag = request.getParameter("tag");

        if ("add".equals(tag)) {
            String name = request.getParameter("addgradeName");
            //把数据封装成实体对象
            Grade grade = new Grade();
            grade.setgName(name);
            //调用底层方法把数据存入数据库
            int result = new GradeServiceImpl().add(grade);
            if (result == 0) {
                //添加失败
                response.sendRedirect("adgrade.jsp");
            } else {
                //添加成功
                response.sendRedirect("adgrade.jsp");
            }
        } else if ("save".equals(tag)) {
            //修改保存
            //写修改代码
            String name = request.getParameter("gname");
            //int state =Integer.parseInt(request.getParameter("state"));
            int id = Integer.parseInt(request.getParameter("gid"));
            //把数据封装成实体对象
            Grade grade = new Grade();
            grade.setgId(id);
            grade.setgName(name);
            // grade.setState(state);
            //调用底层方法把数据存入数据库
            int result = new GradeServiceImpl().update(grade);
            if (result == 0) {
                //添加失败
                response.sendRedirect("gradeEdit.jsp");
            } else {
                //添加成功
                response.sendRedirect("adgrade.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        String tag = request.getParameter("tag");


        if ("del".equals(tag)) {
            //删除
            int gradeId = Integer.parseInt(request.getParameter("gId"));
            int result = new GradeDaoImpl().del(gradeId);
            PrintWriter out = response.getWriter();
            //向客户端写出数据
            out.write(result + "");
            out.close();

        } else if ("edit".equals(tag)) {
            int id = Integer.parseInt(request.getParameter("gId"));
            Grade grade = new GradeServiceImpl().findByGradeId(id);
            request.setAttribute("grade", grade);
            //转发到页面
            request.getRequestDispatcher("gradeEdit.jsp").forward(request, response);
        } else {

            List<Grade> list1 = new GradeServiceImpl().getGrades();
            //返回Json格式的数据
            String json = JSON.toJSONString(list1);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        }
    }
}
