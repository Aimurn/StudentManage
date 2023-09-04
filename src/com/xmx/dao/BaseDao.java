package com.xmx.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @Auther: 范榆林
 * @Date: 2023/6/26 11:44
 * @Description: 工具类，把数据库的相关的配置全部管理
 * 1. 加载驱动
 * 2. 向外提供一个获取连接的方法
 * 3. 向外提供关闭资源的方法
 * 4. 提供一个增删改的方法
 */
public class BaseDao {
    /**
     * 使用连接池
     */
    static String driver;
    static String url;
    static String username;
    static String pwd;

    //读取以属性文件
    static void init() {
        //配置类，加载配置文件后，可以取文件中的数据
        Properties properties = new Properties();
        String path = "jdbc.properties";  //文件路径
        //配置文件形成输入流
        InputStream inputStream =
                BaseDao.class.getClassLoader().getResourceAsStream(path);
        try {
            //加载配置文件流到配置对象
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //取配置文件的数据
        driver = properties.getProperty("jdbc.dri");
        url = properties.getProperty("jdbc.url");
        username = properties.getProperty("jdbc.username");
        pwd = properties.getProperty("jdbc.password");
    }

    /**
     * 加载驱动，程序一启动加要加载
     */
    static {
        init();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向外公开一个方法，提供连接对象
     */
    // public static Connection getConnection(){
    //     Connection connection = null;
    //     try {
    //         connection = DriverManager.getConnection(url,username,pwd);
    //     } catch (SQLException throwables) {
    //         throwables.printStackTrace();
    //     }
    //     return connection;
    // }

    // //1. 用tomcat中的JNDI连接池
    // public static Connection getConnection() {
    //     Context cxt = null;
    //     try {
    //         cxt = new InitialContext();
    //         //根据配置来创建数据源对象，也就是连接池
    //         DataSource dataSource = (DataSource) cxt.lookup("java:comp/env/jdbc/xmx");
    //         //返回连接
    //         return dataSource.getConnection();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    //2. c3p0 连接池，创建 ComboPooledDataSource 池对象
    static ComboPooledDataSource cbds = new ComboPooledDataSource();

    public static Connection getConnection() {
        try {
            return cbds.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 封装增删改的方法
     *
     * @return
     */
    public static int executeUpdate(String sql, Object... objects) {
        Connection connection = getConnection();
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(sql);
            //给占位符赋值
            for (int i = 0; i < objects.length; i++) {
                st.setObject(i + 1, objects[i]);
            }
            return st.executeUpdate();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll(connection, st, null);
        }
        return 0;
    }

    /**
     * 关闭所有资源
     *
     * @param conn 连接
     * @param st   命令
     * @param rs   结果集
     */
    public static void closeAll(Connection conn, Statement st, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    //
    // /**
    //  * 自己写的BaseDao部分，未使用连接池
    //  */
    // //1．加载驱动
    // static {
    //     try {
    //         //通过反射的机制来创建出Driver这个类的对象
    //         // com.mysql.jdbc.Driver类的路径﹐全限定名
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    //
    // //2.向外提供一个获取连接的方法
    // public static Connection getConnection() {
    //     Connection connection = null;
    //     try {
    //         connection = DriverManager.getConnection(
    //                 "jdbc:mysql://localhost:3306/j24_javaweb?characterEncoding=utf-8",
    //                 "root",
    //                 "123456");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return connection;
    // }
    //
    // //3.关闭所有资源
    // public static void closeAll(Connection connection, Statement statement, ResultSet resultSet) {
    //     try {
    //         if (resultSet != null) {
    //             resultSet.close();
    //         }
    //         if (statement != null) {
    //             statement.close();
    //         }
    //         if (connection != null) {
    //             connection.close();
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    //
    // //4.封装增删改方法
    // public static int executeUpdate(String sql, Object... objects) {
    //     Connection connection = getConnection();
    //     PreparedStatement statement = null;
    //     try {
    //         statement = connection.prepareStatement(sql);
    //         //给占位符赋值
    //         for (int i = 0; i < objects.length; i++) {
    //             statement.setObject(i + 1, objects[i]);
    //         }
    //         return statement.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     } finally {
    //         closeAll(connection, statement, null);
    //     }
    //     return 0;
    // }
}
