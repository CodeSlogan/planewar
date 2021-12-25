package com.codeslogan;

import com.codeslogan.utils.Data;
import com.codeslogan.utils.JdbcUtils;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;

/**
 * @Author: codeslogan
 * @Date: 2021-12-10 21:03
 */
public class RankFrame extends JFrame {
    //rowData用来存放行数据
    //columnNames存放列名
    Vector rowData,columnNames;
    JTable jt=null;
    JScrollPane jsp=null;
    Connection cn = null;
    Statement sm = null;
    ResultSet rs = null;
    String str = "SELECT fraction, createtime from grade ORDER BY fraction DESC";
    int index;

    public RankFrame() {
        index = 1;
        columnNames=new Vector();
        // 设置列名
        columnNames.add("排名");
        columnNames.add("分数");
        columnNames.add("日期");

        rowData = new Vector();
        try {
            cn = JdbcUtils.getConnection();
            PreparedStatement statement = cn.prepareStatement(str);
            rs = statement.executeQuery();

            while (rs.next()) {
                // rowData行数据，可以存放多行
                Vector hang=new Vector();
                hang.add(""+index);
                hang.add(rs.getInt("fraction"));
                hang.add(rs.getTimestamp("createtime"));
                index++;
                // 加入到rowData
                rowData.add(hang);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.release(cn, sm, rs);
        }

        jt = new JTable(rowData,columnNames);

        // 初始化 jsp
        jsp = new JScrollPane(jt);

        // 把jsp放入到jframe
        this.add(jsp);
        // 设置窗口大小
        this.setSize(400, 300);
        // 居中
        this.setLocationRelativeTo(null);
        // 设置标题
        this.setTitle("排行榜");
        // icon
        this.setIconImage(Data.rankImg);
        //设置不可最大化
        this.setResizable(false);
        // 设置可见
        this.setVisible(true);
    }

}
