package com.xmx.servlet;

import com.xmx.pojo.Student;
import com.xmx.pojo.User;
import com.xmx.service.impl.StudentServiceImpl;
import com.xmx.service.impl.UserServiceImpl;
import com.xmx.util.MD5Encrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");   //解决中文是问号乱码
        HttpSession session = request.getSession(); //创建session对象
        String tag = request.getParameter("tag");

        if ("login".equals(tag)) {
            String username = request.getParameter("username");
            String pwd = request.getParameter("pwd");
            //MD5加密密码
            MD5Encrypt md5 = new MD5Encrypt();
            pwd = md5.MD5(pwd);

            String code = request.getParameter("code"); //获取验证码
            String logType = request.getParameter("logType");
            System.out.println("用户名：" + username + "，密码：" + pwd + "，验证码：" + code + "，用户身份：" + logType);

            if (code.equals(session.getAttribute("checkCode"))) {   //登录时判断验证码
                if (logType.equals("1")) {  // 学生身份登录验证
                    int stuNo = 0;
                    if (username != "") {
                        stuNo = Integer.parseInt(username);
                    }
                    //获取学生登录对应用户信息
                    Student student = new StudentServiceImpl().login(stuNo, pwd);
                    if (student != null) {   //判断用户名和密码正确
                        session.setAttribute("user", student);
                        response.getWriter().write("success");
                    } else {
                        response.getWriter().write("failure");
                    }
                } else if (logType.equals("2")) {   // 管理员身份登录验证
                    User user = new UserServiceImpl().login(username, pwd);  //获取管理员登录对应用户信息
                    if (user != null) {   //判断用户名和密码正确
                        session.setAttribute("user", user);
                        response.getWriter().write("success");
                    } else {
                        System.out.println("用户名或密码不正确");
                        response.getWriter().write("failure");
                    }
                } else {
                    // 未选择身份
                    System.out.println("未选择身份");
                    response.getWriter().write("typeFailure");
                }
                System.out.println("验证码正确");
                // response.sendRedirect("main.jsp");
            } else {
                // 验证码错误
                System.out.println("验证码错误");
                response.getWriter().write("yzmFailure");
                // request.setAttribute("msg", "验证码错误！");
                // request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");   //解决中文是问号乱码
        HttpSession session = request.getSession(); //创建session对象
        String tag = request.getParameter("tag");

        //用户退出登录
        if ("logOut".equals(tag)) {
            // session.invalidate();
            // session.removeAttribute("user");
            session.removeAttribute("user");
            response.sendRedirect("login.jsp");
        }
    }
}
