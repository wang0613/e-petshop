package com.qhit.petshop.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://127.0.0.1:3306/petshop?characterEncoding=UTF-8";
    private String username = "root";
    private String pwd = "root";

    // 1.获取链接
    public Connection getConnection() {
        Connection conn = null;
        try {
            // 创建当前类的对象
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 增删改
     *
     * @param sql 执行sql语句
     * @return
     */
    public int update(String sql, Object... objs) {
        Connection conn = null;
        PreparedStatement ptm = null;
        int rs = 0;
        try {
            conn = getConnection();
            ptm = conn.prepareStatement(sql);
            if (objs != null) {

                for (int i = 0; i < objs.length; i++) {
                    ptm.setObject(i + 1, objs[i]);

                }
            }
            rs = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(null, ptm, conn);
        }
        return rs;
    }



    public List<Map<String, Object>> query(String sql, Object... objs) {

        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<Map<String, Object>> rslist = new ArrayList<Map<String, Object>>();
        try {
            conn = getConnection();
            ptm = conn.prepareStatement(sql);
            setParams(ptm, objs);
            // 获取查询结果
            rs = ptm.executeQuery();
            // 获取结果集结构(列的结构)
            ResultSetMetaData md = rs.getMetaData();
            // 获取结果集列数
            int columns = md.getColumnCount();

            // 循环获取每一行
            while (rs.next()) {
                // 封装每一行
                Map<String, Object> map = new HashMap<String, Object>();
                // 获取当前行的每一列的值
                for (int i = 1; i <= columns; i++) {
                    // 当前列的值
                    Object value = rs.getObject(i);
                    // 当前列的列名
                    String columnName = md.getColumnName(i);

                    // 每一行中的某一列
                    map.put(columnName, value);
                }
                rslist.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ptm, conn);
        }
        return rslist;
    }


    // 4.关闭
    public void closeAll(ResultSet rs, PreparedStatement ptm,
                         Connection conn) {
        try {
            if (null != rs)
                rs.close();
            if (null != ptm)
                ptm.close();
            if (null != conn)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 5.参数设置
    private void setParams(PreparedStatement ptm, Object... objs) {
        if (null != objs) {
            for (int i = 0; i < objs.length; i++) {
                try {
                    ptm.setObject(i + 1, objs[i]);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}



