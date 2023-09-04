package com.xmx.servlet;

import com.xmx.pojo.User;
import com.xmx.service.impl.UserServiceImpl;
import com.xmx.util.MD5Encrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserServlet",value = "/userServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");   //解决中文是问号乱码

        int id = Integer.parseInt(request.getParameter("uId"));
        String name = request.getParameter("user");
        String pwd = request.getParameter("pwd");
        MD5Encrypt md5 = new MD5Encrypt();
        pwd = md5.MD5(pwd);

        User user = new User();
        user.setId(id);
        user.setUsername(name);
        user.setPwd(pwd);

        int result = new UserServiceImpl().update(user);
        if (result > 0) {
            request.setAttribute("msg", "信息修改成功！");
            response.sendRedirect("adpersonal.jsp");
        } else {
            request.setAttribute("msg", "信息修改失败！");
            request.getRequestDispatcher("adpersonal.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");   //解决中文是问号乱码


    }
}
