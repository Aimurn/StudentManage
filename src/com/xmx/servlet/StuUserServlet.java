package com.xmx.servlet;

import com.xmx.pojo.Classes;
import com.xmx.pojo.Grade;
import com.xmx.pojo.Student;
import com.xmx.service.impl.StudentServiceImpl;
import com.xmx.util.MD5Encrypt;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: 范榆林
 * @Date: 2023/7/2 16:04
 * @Description:
 */
@WebServlet(name = "StuUserServlet", value = "/stuUserServlet")
public class StuUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");   //解决中文是问号乱码

        int stuNo = Integer.parseInt(request.getParameter("stuNo"));
        String stuName = request.getParameter("stuName");

        String pwd = request.getParameter("pwd");
        MD5Encrypt md5 = new MD5Encrypt();
        pwd = md5.MD5(pwd);
        String sex = request.getParameter("sex");

        Student student = new StudentServiceImpl().findById(stuNo);
        int gId = student.getGrade().getgId();
        Grade grade = new Grade();
        grade.setgId(gId);
        int classId = student.getClasses().getClassId();
        Classes classes = new Classes();
        classes.setClassId(classId);

        String born = request.getParameter("born");
        String phone = request.getParameter("tel");
        String address = request.getParameter("address");
        String idCard = request.getParameter("idcard");

        Student student1 = new Student(stuNo,stuName,pwd,sex,grade,classes,born,phone,address,idCard);
        int result = new StudentServiceImpl().update(student1);
        if (result > 0) {
            request.setAttribute("msg", "信息修改成功！");
            response.sendRedirect("stupersonal.jsp");
        } else {
            request.setAttribute("msg", "信息修改失败！");
            request.getRequestDispatcher("stupersonal.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
