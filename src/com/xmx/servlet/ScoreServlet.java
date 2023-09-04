package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.xmx.dao.CourseDao;
import com.xmx.dao.StudentDao;
import com.xmx.dao.impl.CourseDaoImpl;
import com.xmx.dao.impl.StudentDaoImpl;
import com.xmx.pojo.Course;
import com.xmx.pojo.Score;
import com.xmx.pojo.Student;
import com.xmx.service.CourseService;
import com.xmx.service.ScoreService;
import com.xmx.service.impl.CourseServiceImpl;
import com.xmx.service.impl.ScoreServiceImpl;
import com.xmx.service.impl.StudentServiceImpl;
import com.xmx.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ScoreServlet", value = "/scoreServlet")
public class ScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        ScoreService scoreService = new ScoreServiceImpl();
        String tag = request.getParameter("tag");

        if ("add".equals(tag)) {
            int no = Integer.parseInt(request.getParameter("addsid"));
            StudentDao studentDao = new StudentDaoImpl();
            Student student = studentDao.findById(no);

            int cid = Integer.parseInt(request.getParameter("addcourse"));
            CourseDao courseDao = new CourseDaoImpl();
            Course course = courseDao.findByCourseId(cid);

            int addscore = Integer.parseInt(request.getParameter("addscore"));

            //把数据封装成实体对象
            Score score = new Score(student, course, addscore);
            //调用底层方法把数据存入数据库
            int result = scoreService.add(score);
            if (result == 0) {
                //添加失败
                response.sendRedirect("adscore.jsp");
            } else {
                //添加成功
                response.sendRedirect("adscore.jsp");
            }

        } else if ("save".equals(tag)) {
            int no = Integer.parseInt(request.getParameter("stuNo"));
            StudentDao studentDao = new StudentDaoImpl();
            Student student = studentDao.findById(no);
            int cId = Integer.parseInt(request.getParameter("course"));
            CourseDao courseDao = new CourseDaoImpl();
            Course course = courseDao.findByCourseId(cId);
            int score = Integer.parseInt(request.getParameter("score"));
            Score score1 = new Score(student, course, score);
            scoreService.update(score1);
            response.sendRedirect("adscore.jsp");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        ScoreService scoreService = new ScoreServiceImpl();
        String tag = request.getParameter("tag");

        if ("del".equals(tag)) {
            int cId = Integer.parseInt(request.getParameter("cId"));
            int no = Integer.parseInt(request.getParameter("no"));
            int result = scoreService.del(no, cId);
            PrintWriter out = response.getWriter();
            //向客户端写出数据
            out.write(result + "");
            out.close();

        } else if ("edit".equals(tag)) {
            int cId = Integer.parseInt(request.getParameter("cId"));
            int no = Integer.parseInt(request.getParameter("no"));
            int addscore = Integer.parseInt(request.getParameter("addscore"));

            Course course = new CourseDaoImpl().findByCourseId(cId);

            //取年级信息
            CourseService courseService1 = new CourseServiceImpl();
            List<Course> list = courseService1.getCourse();

            request.setAttribute("no", no);
            request.setAttribute("addscore", addscore);
            request.setAttribute("course", course);
            request.setAttribute("Course", list);
            request.getRequestDispatcher("scoreEdit.jsp").forward(request, response);
        } else {
            int index = 1;//默认第一页
            int size = 6;//每页大小
            if (request.getParameter("index") != null) {
                index = Integer.parseInt(request.getParameter("index"));
            }
            //从session中取搜索条件
            int no = 0;
            if (request.getParameter("no") != "") {
                no = Integer.parseInt(request.getParameter("no"));
            }
            int cId = 0;
            if (request.getParameter("cId") != null) {
                cId = Integer.parseInt(request.getParameter("cId"));
            }
            System.out.println(no + "--------------");
            System.out.println(cId + "--------------");
            PageUtil<Score> pageUtil = scoreService.pages(index, size, no, cId);
            String json = JSON.toJSONString(pageUtil);
            System.out.println(json);
            PrintWriter out = response.getWriter();
            out.write(json);
            out.close();
        }
    }
}
