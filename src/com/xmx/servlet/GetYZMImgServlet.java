package com.xmx.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/27 09:24
 * @Description:
 */
@WebServlet(name = "GetYZMImgServlet", value = "/getImg")
public class GetYZMImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public static final int WIDTH = 114; //生成图片的宽度
    public static final int HEIGHT = 46;//生成 图片高度

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //1.在内存中创建一张图片
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        //2.得到图片的画布
        Graphics g = bi.getGraphics();
        //3.设置图片的背景色
        setBackGround(g);
        //4.设置图片的边框
        setBorder(g);
        //5.在图片上设置干扰线,点状的叫燥点，线状的叫燥线
        int num = 5;
        drawRandomLine(g, num);
        //6.写在图片上的随机数
        int num2 = 4;//最多4个，这个地方可以通过调节输入框的宽度来进行调节

        String random = drawRandomNum((Graphics2D) g, num2);
        //7.将随机数写在session里面
        request.getSession().setAttribute("checkCode", random);
        //8.设置响应头通知浏览器以图片的方式打开
        response.setContentType("image/jpeg");
        //9.设置响应头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        //10.、把图片写进浏览器
        ImageIO.write(bi, "jpg", response.getOutputStream());
    }

    /****设置图片的背景色***/
    private void setBackGround(Graphics g) {
        //设置颜色
        g.setColor(Color.WHITE);
        // g.setColor(Color.WHITE);
        //填充区域
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    /***设置图片的边框**/
    private void setBorder(Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
    }

    /*****设置图片的随即线条******/
    private void drawRandomLine(Graphics g, int num) {
        g.setColor(Color.GREEN);
        //设置线条个数并划线
        for (int i = 0; i < num*2; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /*******画随机字符*******/
    private String drawRandomNum(Graphics2D g, int num) {
        g.setColor(Color.RED);
        g.setFont(new Font("宋体", Font.BOLD, 30));
        //纯数字
        String baseNum = "0123456789";
        // 截取数字
        return createRandomChar(g, baseNum, num);
    }

    /**
     * 创建随机字符
     **/
    private String createRandomChar(Graphics2D g, String baseChar, int num) {
        StringBuffer sb = new StringBuffer();
        int x = 15;
        String ch = "";
        //控制字数
        for (int i = 0; i < num; i++) {
            // 设置字体旋转角度
            int degree = new Random().nextInt() % 20;
            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
            sb.append(ch);
            // 正向角度
            g.rotate(degree * Math.PI / 180, x, 20);
            g.drawString(ch, x, 30);
            // 反向角度
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 26;
        }
        return sb.toString();
    }
}
