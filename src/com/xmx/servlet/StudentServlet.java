package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.pojo.Classes;
import com.xmx.pojo.Grade;
import com.xmx.pojo.Student;
import com.xmx.service.GradeService;
import com.xmx.service.StudentService;
import com.xmx.service.impl.ClassesServiceImpl;
import com.xmx.service.impl.GradeServiceImpl;
import com.xmx.service.impl.StudentServiceImpl;
import com.xmx.util.MD5Encrypt;
import com.xmx.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StudentServlet", value = "/studentServlet")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");   //解决中文是问号乱码
        String tag = request.getParameter("tag");
        StudentService studentService = new StudentServiceImpl();

        if (tag == null) {
            // 搜索
            int gid = Integer.parseInt(request.getParameter("grade"));
            String name = request.getParameter("stuName");
            int no = Integer.parseInt(request.getParameter("stuNo"));
            int cid = Integer.parseInt(request.getParameter("classId"));

            // 把搜索条件存入到Session
            request.getSession().setAttribute("gid", gid);
            request.getSession().setAttribute("stuName", name);
            request.getSession().setAttribute("stuNo", no);
            request.getSession().setAttribute("classId", cid);
            // 取年级信息
            GradeService gradeService = new GradeServiceImpl();
            List<Grade> list = gradeService.getGrades();
            // 数据如何传递到页面
            request.setAttribute("grades", list);
            // 带条件分页查询
            PageUtil<Student> pageUtil = studentService.pages(1, 10, gid, name, no, cid);

            // 数据如何传递到页面
            request.setAttribute("pageUtil", pageUtil);

            // 转发
            request.getRequestDispatcher("adstudent.jsp").forward(request, response);
        } else if ("save".equals(tag)) {
            // int adstuNo= Integer.parseInt(request.getParameter("stuNo"));
            String adname = request.getParameter("addstuName");
            // String adimg=request.getParameter("stuImg");
            String adpwd = request.getParameter("addpwd");
            MD5Encrypt md5 = new MD5Encrypt();
            adpwd = md5.MD5(adpwd);
            String adsex = request.getParameter("addsex");

            int adgid = Integer.parseInt(request.getParameter("addgrade"));
            Grade adgrade = new Grade();
            adgrade.setgId(adgid);

            int adcid = Integer.parseInt(request.getParameter("addclass"));
            Classes adclasses = new Classes();
            adclasses.setClassId(adcid);

            String adborn = request.getParameter("addborn");
            String adphone = request.getParameter("addtel");
            String adaddress = request.getParameter("addaddress");
            String adidcard = request.getParameter("addidcard");

            Student adstudent = new Student(adname, adpwd, adsex, adgrade, adclasses, adborn, adphone, adaddress,
                    adidcard);

            if (studentService.add(adstudent) > 0) {
                response.sendRedirect("adstudent.jsp");
            } else {
                response.sendRedirect("adstudent.jsp");
            }
        } else if ("update".equals(tag)) {
            int stuNo = Integer.parseInt(request.getParameter("stuNo"));
            String name = request.getParameter("stuName");
            // String img = request.getParameter("stuImg");
            String pwd = request.getParameter("pwd");
            MD5Encrypt md5 = new MD5Encrypt();
            pwd = md5.MD5(pwd);
            String sex = request.getParameter("sex");

            int gid = Integer.parseInt(request.getParameter("grade"));
            Grade grade = new Grade();
            grade.setgId(gid);

            int cid = Integer.parseInt(request.getParameter("stuclass"));
            Classes classes = new Classes();
            classes.setClassId(cid);

            String born = request.getParameter("born");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String idcard = request.getParameter("idCard");

            Student student = new Student(stuNo, name, pwd, sex, grade, classes, born, phone, address, idcard);
            int id = Integer.parseInt(request.getParameter("stuNo"));
            student.setStuNo(id);
            studentService.update(student);
            response.sendRedirect("adstudent.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8"); // 解决中文是问号乱码
        StudentService studentService = new StudentServiceImpl();
        String tag = request.getParameter("tag");

        if ("edit".equals(tag)) {
            int stuNo = Integer.parseInt(request.getParameter("stuNo"));
            Student student = studentService.findById(stuNo);
            // 取年级信息
            GradeService gradeService = new GradeServiceImpl();
            List<Grade> list = gradeService.getGrades();
            // 取班级信息
            List<Classes> list1 = new ClassesServiceImpl().getClasses();
            // 数据传递到页面
            request.setAttribute("classes", list1);
            request.setAttribute("grades", list);
            request.setAttribute("student", student);
            // 转发
            request.getRequestDispatcher("studentEdit.jsp").forward(request, response);
        } else if ("del".equals(tag)) {
            // 删除单个
            int stuNo = Integer.parseInt(request.getParameter("stuNo"));
            int result = studentService.del(stuNo);
            PrintWriter out = response.getWriter();
            // 向客户端写出数据
            out.write(result + "");
            out.close();
        } else if ("add".equals(tag)) {
            // 取年级信息
            GradeService gradeService = new GradeServiceImpl();
            List<Grade> list = gradeService.getGrades();
            // 数据如何传递到页面
            request.setAttribute("grades", list);
            // 转发
            request.getRequestDispatcher("adstudent.jsp").forward(request, response);
        } else {
            int index = 1; // 默认是第1页
            int size = 5; // 每页大小
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }

            int gid = 0;
            if (request.getParameter("gId") != null) {
                gid = Integer.parseInt(request.getParameter("gId"));
            }

            String name = "";
            if (request.getParameter("stuName") != null) {
                name = request.getParameter("stuName");
            }

            int stuNo = 0;
            if (!"".equals(request.getParameter("stuNo"))) {

                stuNo = Integer.parseInt(request.getParameter("stuNo"));
            }

            int classId = 0;
            String classId1 = request.getParameter("classId");
            if (classId1 != null) {
                classId = Integer.parseInt(request.getParameter("classId"));
            }

            System.out.println(gid + "--------------");
            System.out.println(name);
            PageUtil<Student> pageUtil = studentService.pages(index, size, gid, name, stuNo, classId);
            String json = JSON.toJSONString(pageUtil);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        }
    }
}
