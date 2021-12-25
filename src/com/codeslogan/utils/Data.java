package com.codeslogan.utils;


import com.codeslogan.obj.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Data {
    // 窗口的宽度、高度,游戏的刷新速度
    public static final int WIDTH = 512, HEIGHT = 768;
    // 背景图片
    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    // icon
    public static Image iconImg = Toolkit.getDefaultToolkit().getImage("imgs/ICON1.png");
    // boss图片
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss.png");
    // 爆炸图片
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("imgs/explode/e6.gif");
    // 我方战斗机图片
    public static Image planeImg = Toolkit.getDefaultToolkit().getImage("imgs/plane.png");
    // 我方子弹
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("imgs/shell.png");
    // 敌方子弹
    public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("imgs/bullet.png");
    // boss血条
    public static Image hpBox = Toolkit.getDefaultToolkit().getImage("imgs/BOSS血条.png");
    // logo
    public static Image logoImg[] = new Image[]
            {
                    Toolkit.getDefaultToolkit().getImage("imgs/LOGO1.png"),
                    Toolkit.getDefaultToolkit().getImage("imgs/LOGO2.png"),
                    Toolkit.getDefaultToolkit().getImage("imgs/LOGO3.png"),
            };
    // 排行榜icon
    public static Image rankImg = Toolkit.getDefaultToolkit().getImage("imgs/favicon.png");
    // 敌方战斗机
    public static Image enemyImg[] = new Image[]
            {
                    Toolkit.getDefaultToolkit().getImage("imgs/enemy1.png"),
                    Toolkit.getDefaultToolkit().getImage("imgs/enemy2.png"),
                    Toolkit.getDefaultToolkit().getImage("imgs/enemy3.png"),
                    Toolkit.getDefaultToolkit().getImage("imgs/enemy4.png"),
                    Toolkit.getDefaultToolkit().getImage("imgs/enemy5.png")};

    // 所有游戏内物体的集合
    public static List<GameObj> gameObjList = new ArrayList<>();
    // 我方子弹的集合
    public static List<ShellObj> shellObjList = new ArrayList<>();
    // 敌方子弹的集合
    public static List<BulletObj> bulletObjList = new ArrayList<>();
    // 敌方战斗机的集合
    public static List<EnemyObj> enemyObjList = new ArrayList<>();
    // 移除的元素集合
    public static List<GameObj> removeList = new ArrayList<>();
    // 爆炸的集合
    public static List<ExplodeObj> explodeObjList = new ArrayList<>();



    //绘制字符串的工具类
    public static void drawWord(Graphics gImage,String str, String fontName, Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font(fontName, Font.BOLD, size));
        gImage.drawString(str,x,y);
    }

}
