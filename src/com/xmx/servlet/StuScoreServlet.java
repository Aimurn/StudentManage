package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.pojo.Score;
import com.xmx.pojo.Student;
import com.xmx.service.ScoreService;
import com.xmx.service.impl.ScoreServiceImpl;
import com.xmx.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Auther: 范榆林
 * @Date: 2023/7/2 16:03
 * @Description:
 */
@WebServlet(name = "StuScoreServlet", value = "/stuScoreServlet")
public class StuScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        ScoreService scoreService = new ScoreServiceImpl();
        HttpSession session = request.getSession();

        Student student = (Student) session.getAttribute("user");
        int stuNo = student.getStuNo();

        int index = 1;//默认第一页
        int size = 4;//每页大小
        if (request.getParameter("index") != null) {
            index = Integer.parseInt(request.getParameter("index"));
        }
        //从session中取搜索条件
        int no = 0;
        if (request.getParameter("no") != null) {
            no = Integer.parseInt(request.getParameter("no"));
        }
        int cId = 0;
        if (request.getParameter("cId") != null) {
            cId = Integer.parseInt(request.getParameter("cId"));
        }
        System.out.println(no + "--------------");
        System.out.println(cId + "--------------");
        PageUtil<Score> pageUtil = scoreService.pages(index, size, stuNo, cId);
        String json = JSON.toJSONString(pageUtil);
        System.out.println(json);
        PrintWriter out = response.getWriter();
        out.write(json);
        out.close();
    }
}
