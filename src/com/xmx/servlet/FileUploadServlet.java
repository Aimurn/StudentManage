package com.xmx.servlet;

import com.xmx.dao.impl.CourseDaoImpl;
import com.xmx.dao.impl.StudentDaoImpl;
import com.xmx.dao.impl.UserDaoImpl;
import com.xmx.pojo.Course;
import com.xmx.pojo.Student;
import com.xmx.pojo.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 何志丹
 * @date 2023-05-10 9:13
 * @depict
 */
@WebServlet(name = "FileUploadServlet", value = "/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        // 请求信息中的内容是否是multipart类型
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //获取服务器的userFile目录的完整路径
        String uploadFilePath = request.getServletContext().getRealPath("/fileImages");
        if (isMultipart) {   //判断请求信息中的内容 是否是“multipart/form-data”类型
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(1024 * 1024 * 10);  //设置上传文件大小 10M
            try {
                List<FileItem> items = upload.parseRequest(request);    // 解析form表单中所有文件
                for (FileItem item : items) {
                    if (!item.isFormField()) { // 普通表单字段true, 文件表单字段false
                        String fileName = item.getName(); //文件名  a.txt
                        if (fileName != null && !fileName.equals("")) {
                            String s = fileName.substring(fileName.lastIndexOf('.') + 1);
                            if (!s.equals("jpg") && !s.equals("gif") && !s.equals("bmp") && !s.equals("png")) {
                                out.print("<script>alert('文件格式不正确！只能上传图片');location.href='index.jsp'</script>");
                            } else {
                                //组织文件服务器端的名称
                                //long d = (new Date().getTime());
                                String d = UUID.randomUUID().toString();
                                File saveFile = new File(uploadFilePath + "/" + d + fileName);


                                String tag = request.getParameter("tag");

                                if ("cou".equals(tag)) {
                                    CourseDaoImpl courseDao = new CourseDaoImpl();
                                    String img = d + fileName;
                                    courseDao.saveImg(img);
                                    // //存课程图片
                                    // Course course = new Course();
                                    // int cId = course.getcId();
                                    // CourseDaoImpl courseDao = new CourseDaoImpl();
                                    // String img = d + fileName;
                                    // courseDao.saveImg(cId, img);
                                } else if ("update".equals(tag)) {
                                    int cId = Integer.parseInt(request.getParameter("cId"));
                                    Course course = new Course();
                                    course.setcId(cId);
                                    CourseDaoImpl courseDao = new CourseDaoImpl();
                                    String img = d + fileName;
                                    courseDao.updateImg(img,course);
                                } else {
                                    Object userObject = session.getAttribute("user");
                                    if (userObject != null) {
                                        if (userObject instanceof Student) {
                                            //存学生图片
                                            Student student = (Student) userObject;
                                            int no = student.getStuNo();
                                            StudentDaoImpl studentDao = new StudentDaoImpl();
                                            String img = d + fileName;
                                            studentDao.saveImg(no, img);
                                        } else if (userObject instanceof User) {
                                            //存管理员图片
                                            User user = (User) userObject;
                                            int id = user.getId();
                                            UserDaoImpl userDao = new UserDaoImpl();
                                            String img = d + fileName;
                                            userDao.saveImg(id, img);
                                        }
                                    }
                                }



                                item.write(saveFile);  //上传
                                out.print("<script>alert('文件上传成功！');location.href='index.jsp'</script>");
                            }
                        }
                    } else {
                        if (item.getFieldName().equals("userName")) {
                            String name = new String(item.getString().getBytes("ISO-8859-1"), "utf-8");
                            System.out.println("用户名是：" + name);
                        } else if (item.getFieldName().equals("pwd")) {
                            System.out.println("密码：" + item.getString());
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.print("<script>alert('文件大小超出范围，只能上传最多4M的文件！');location.href='index.jsp'</script>");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
