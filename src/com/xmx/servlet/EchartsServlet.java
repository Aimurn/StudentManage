package com.xmx.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/27 11:21
 * @Description:
 */
@WebServlet(name = "EchartsServlet", value = "/echarts")
public class EchartsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        List<Integer> list = new ArrayList<>();
        list.add(120);
        list.add(200);
        list.add(150);
        list.add(80);
        list.add(70);
        list.add(110);
        list.add(130);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data1", list);
        String json = JSON.toJSONString(jsonObject);
        System.out.println(json);
        response.getWriter().write(json);
    }
}
