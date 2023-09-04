package com.xmx.util.filter;

import com.xmx.pojo.Student;
import com.xmx.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther: 范榆林
 * @Date: 2023/7/2 09:11
 * @Description:
 */

@WebFilter(filterName = "AdLoginFilter", value = {"/main.jsp","/adclass.jsp","/adcourse.jsp","/adgrade.jsp",
        "/adindex.jsp","/admanage.jsp","/adpersonal.jsp","/adscore.jsp","/adstudent.jsp","/adstuLeave.jsp",
        "/approvalEdit.jsp","/classEdit.jsp","/echarts.jsp","/gradeEdit.jsp","/scoreEdit.jsp",
        "/studentEdit.jsp","/adstuLeave.jsp","/admanage0.jsp"})
public class AdLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException,
            IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 检查需要放行的页面（例如登录页面）
        if (shouldBypass(httpRequest)) {
            // 页面不需要登录，直接放行
            chain.doFilter(request, response);
            return;
        }

        // 检查用户是否已登录
        if (isAccessAllowed(httpRequest)) {
            // 用户已登录且有权限访问该页面，继续执行过滤器链
            chain.doFilter(request, response);
        } else {
            // 用户未登录或无权限访问该页面，将其重定向到登录页面或返回错误信息
            httpResponse.sendRedirect("login.jsp"); // 重定向到登录页面
            // 或者
            // httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 返回未授权错误
        }
    }

    private boolean isAccessAllowed(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userObject = session.getAttribute("user");

        if (userObject != null) {

            if (userObject instanceof User) {
                User user = (User) userObject;
                if (user.getSFtype() == 2) {
                    return true; // 管理员用户可以访问 main.jsp
                }
            }
        }

        return false;
    }


    private boolean shouldBypass(HttpServletRequest request) {
        // 在此处编写判断页面是否需要放行的逻辑
        // 可以通过检查请求的 URL 或其他方式进行判断
        // 返回 true 表示页面需要放行，不需要登录
        // 返回 false 表示页面需要登录
        // 示例逻辑：判断请求的 URL 是否为登录页面
        String requestURI = request.getRequestURI();
        return requestURI.endsWith("login.jsp");
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
