package com.codeslogan;

import com.codeslogan.utils.Data;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class GameFrame extends JFrame {
    JMenuBar menuBar;
    JMenu menu1, menu2, mode;
    JMenuItem about, easyMode, diffMode, rankText, exitText, ruleText;
    String selfIntroduction = "2019级计算机科学与技术4班\n学号：20196601310039\n姓名：陈仕林";
    String rule;


    public GameFrame() throws IOException {
        // 初始化菜单
        initMenu();
        // 设置窗口大小
        this.setSize(512,768);
        // 居中
        this.setLocationRelativeTo(null);
        // 设置窗口标题
        this.setTitle("飞机大战");
        // icon
        this.setIconImage(Data.iconImg);
        //设置不可最大化
        this.setResizable(false);
        // 设置关闭窗体时结束程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置可见
        this.setVisible(true);
    }

    void initMenu() throws IOException {
        //菜单栏
        menuBar = new JMenuBar();
        // 读取规则
        readRule();
        //菜单
        menu1 = new JMenu("游戏");
        menu2 = new JMenu("帮助");
        mode = new JMenu("模式");
        //菜单项
        about = new JMenuItem("关于作者");
        easyMode = new JMenuItem("简单模式");
        diffMode = new JMenuItem("困难模式");
        rankText = new JMenuItem("排行榜");
        exitText = new JMenuItem("退出");
        ruleText = new JMenuItem("规则");

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(about,selfIntroduction,"关于作者",JOptionPane.PLAIN_MESSAGE);
            }
        });

        easyMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel.modeNum = 1;
            }
        });
        diffMode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GamePanel.modeNum = 2;
            }
        });

        rankText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RankFrame();
            }
        });
        ruleText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ruleText,rule,"游戏规则",JOptionPane.PLAIN_MESSAGE);
            }
        });

        exitText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mode.add(easyMode);
        mode.add(diffMode);
        menu1.add(mode);
        menu1.add(rankText);
        menu1.addSeparator();
        menu1.add(exitText);
        menu2.add(ruleText);
        menu2.add(about);
        menuBar.add(menu1);
        menuBar.add(menu2);
        setJMenuBar(menuBar);
    }

    public void readRule() throws IOException {
        Reader reader = new FileReader("lib/rule.txt");
        char[] ch = new char[255];
        int len;
        StringBuffer stringBuffer = new StringBuffer();
        while((len = reader.read(ch)) != -1) {
            String str = new String(ch, 0, len);
            stringBuffer.append(str);
        }
        rule = new String(stringBuffer);
        reader.close();
    }

}
