package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.dao.impl.UserDaoImpl;
import com.xmx.pojo.User;
import com.xmx.service.UserService;
import com.xmx.service.impl.UserServiceImpl;
import com.xmx.util.MD5Encrypt;

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
 * @Date: 2023/7/4 20:17
 * @Description:
 */
@WebServlet(name = "AdminServlet", value = "/adminServlet")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8"); // 解决中文是问号乱码
        String tag = request.getParameter("tag");

        if ("add".equals(tag)) {
            String username = request.getParameter("adduser");
            String pwd = request.getParameter("addpwd");
            MD5Encrypt md5 = new MD5Encrypt();
            pwd = md5.MD5(pwd);
            int type = Integer.parseInt(request.getParameter("addtype"));

            User user = new User(username,pwd,type);
            int result = new UserServiceImpl().add(user);
            response.sendRedirect("admanage.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8"); // 解决中文是问号乱码
        String tag = request.getParameter("tag");

        if (tag == null) {
            String username = "";
            if (request.getParameter("user") != null) {
                username = request.getParameter("user");
            }

            int type = 2;
            if (Integer.parseInt(request.getParameter("adType")) != 2) {

                type = Integer.parseInt(request.getParameter("adType"));
            }

            List<User> list = new UserDaoImpl().find1(username, type);
            String json = JSON.toJSONString(list);
            System.out.println(json);
            response.getWriter().write(json);
        } else if ("del".equals(tag)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int type = Integer.parseInt(request.getParameter("adType"));
            if (type == 1) {
                response.getWriter().write("failure");
            } else {
                int result = new UserServiceImpl().del(id);
                if (result > 0) {
                    response.getWriter().write("success");
                }
            }

        }
    }

}
