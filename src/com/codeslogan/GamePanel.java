package com.codeslogan;

import com.codeslogan.obj.*;
import com.codeslogan.utils.Data;
import com.codeslogan.utils.JdbcUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

import static com.codeslogan.utils.Data.*;

/**
 * @Author: codeslogan
 * @Date: 2021-12-08 14:55
 */
public class GamePanel extends JPanel {
    GamePanel gamePanel = this;
    private JFrame mainFrame = null;
    // 0 未开始，1开始，2暂停，3失败，4成功
    public static int state = 0;
    // 当前 X轴 位置
    public int x = 0, speed = 1;
    // 双背景移动
    BgObj bgObj = new BgObj(bgImg, 0, x, speed);
    BgObj bgObj2 = new BgObj(bgImg, 0, x - Data.HEIGHT, speed);
    // 我方飞机
    public PlaneObj plane = new PlaneObj(planeImg, 200, 590 , 80, 80, 12, this);
    // 飞机的移动控制
    boolean left = false, right = false, down = false, up = false;
    // 游戏重绘次数
    int count = 1;
    // 敌方boss
    public BossObj bossObj;
    // 分数
    public static int score = 0;
    // 敌机的数量
    int enemyCount = 0;
    // 绘制的 LOGO 索引
    int logoIndex = 0;
    // 游戏模式，1为简单模式，2为困难模式
    public static int modeNum = 2;

    Connection cn = null;
    Statement sm = null;
    ResultSet rs = null;
    String str1 = "INSERT INTO grade(`fraction`, `createtime`) VALUES (?, ?)";


    public GamePanel(JFrame frame) {
        this.setLayout(null);
        mainFrame = frame;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1 && state == 0) {
                    state = 1;
                    repaint();
                }
            }
        });
        this.mainFrame.addKeyListener(new OnKeyEvent());
        mainFrame.setVisible(true);
        gameObjList.add(plane);


        new Thread(new RefreshThread()).start();
    }

    //键盘事件内部类
    class OnKeyEvent extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            onKeyDown(e.getKeyCode());
        }

        public void keyReleased(KeyEvent e) {
            onKeyUp(e.getKeyCode());
        }
    }

    public void onKeyDown(int keyCode) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP)
            up = true;
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN)
            down = true;
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
            left = true;
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
            right = true;
        if (keyCode == KeyEvent.VK_SPACE) {
            switch (state) {
                case 1: state = 2;
                break;
                case 2: state = 1;
                break;
                default:
            }
        }
    }

    public void onKeyUp(int keyCode) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP)
            up = false;
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN)
            down = false;
        if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT)
            left = false;
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT)
            right = false;
    }

    @Override
    public void paint(Graphics g) {
        if (state == 0) {
            home(g);
        } else if (state == 1) {
            game(g);
        } else if (state == 3) {
            g.drawImage(explodeImg, plane.getX()+20, plane.getY(), null);
            drawWord(g, "游戏结束", "仿宋", Color.RED, 40, 150, 300);
        } else if (state == 4) {
            g.drawImage(explodeImg, bossObj.getX()+30, bossObj.getY(), null);
            drawWord(g, "游戏通关", "仿宋", Color.GREEN, 40, 180, 300);
        }
        if ( state == 4 && score != 0) {
            insertScore();
        }
        count++;
        if (state == 3 || state == 4) {
            restartGame();
        }
    }

    // 游戏界面
    public void game(Graphics g) {
        bgObj.paint(g);
        bgObj2.paint(g);
        // 更新图片在 X轴 的位置
        x = (x >= Data.HEIGHT) ? 0 : x + speed;
        drawWord(g,"得分：" + score, "楷体", Color.green,30,330,50);
        gameObjList.addAll(explodeObjList);
        move();
        for (int i = 0; i < gameObjList.size(); i++) {
            gameObjList.get(i).paint(g);
        }
        // 移除的物体不再绘制
        gameObjList.removeAll(removeList);
    }

    // 游戏结束后重启游戏
    public void restartGame() {
        count = 1;
        enemyCount = 0;
        score = 0;
        gameObjList.clear();
        shellObjList.clear();
        bulletObjList.clear();
        enemyObjList.clear();
        removeList.clear();
        death();
        // 我方飞机回到原始位置
        plane.setX(200);
        plane.setY(590);
        // 重新添加到主集合中，以便下次绘制
        gameObjList.add(plane);
    }

    // 将分数写入数据库
    public void insertScore() {
        try {
            // 连接数据库
            cn = JdbcUtils.getConnection();
            PreparedStatement sql1 = cn.prepareStatement(str1);
            // 手动赋值
            sql1.setInt(1, score);
            sql1.setTimestamp(2, new Timestamp(new Date().getTime()));
            //执行
            int i = sql1.executeUpdate();
            if (i > 0) {
                System.out.println("分数更新成功");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 关闭数据库
            JdbcUtils.release(cn, sm, rs);
        }
        score = 0;
    }

    // 主页
    public void home(Graphics g) {
        g.drawImage(bgImg, 0, 0, null);
        // 绘制 LOGO
        g.drawImage(logoImg[logoIndex / 30 > 2 ? 2 : logoIndex / 30], 20, 65, null);
        logoIndex = logoIndex >= 100 ? 0 : logoIndex+1;
        g.drawImage(bossImg, 185, 200, null);
        g.drawImage(explodeImg, 270, 350, null);
        drawWord(g, "点击开始游戏", "仿宋", Color.yellow, 40, 130, 300);
    }

    // 管理飞机移动
    void move() {
        if (plane != null) {
            // 飞机向上移动, 这里还限制了飞机的范围，不让其飞出屏幕外
            if (up)
                plane.move(0, -plane.getSpeed());
            if (plane.getY() < 0)
                plane.setY(0);
            // 飞机向下移动
            if (down) plane.move(0, plane.getSpeed());
            if (plane.getY() > Data.HEIGHT - plane.getHeight())
                plane.setY(Data.HEIGHT - plane.getHeight());
            // 飞机向左移动
            if (left) plane.move(-plane.getSpeed(), 0);
            if (plane.getX() < 0) plane.setX(0);
            // 飞机向右移动
            if (right)
                plane.move(plane.getSpeed(), 0);
            if (plane.getX() > Data.WIDTH - plane.getWidth())
                plane.setX(Data.WIDTH - plane.getWidth());

        }
    }

    void createObj() {
        // 我方子弹
        if (count % 3 == 0) {
            shellObjList.add(new ShellObj(shellImg, plane.getX() + 48, plane.getY() - 25, 30, 30, 20, this));
            gameObjList.add(shellObjList.get(shellObjList.size()-1));
        }

        // 敌机
        if (count % 12 == 0 && state == 1) {
            enemyCount++;
            int index = (int)(Math.random()*5);
            switch (modeNum) {
                case 1: enemyObjList.add(new EnemyObj(enemyImg[index], index*90, 0, 86, 59, 5, 1, this));
                break;
                case 2: enemyObjList.add(new EnemyObj(enemyImg[index], index*90, 0, 86, 59, 5, index+1, this));
                break;
                default:
            }
            gameObjList.add(enemyObjList.get(enemyObjList.size()-1));
        }
        //敌机出现10架后boss出现
        if (enemyCount > 10 && bossObj == null) {
            bossObj = new BossObj(bossImg, 250, 0, 157, 109, 10, this);
            gameObjList.add(bossObj);

        }

        // 敌方Boss子弹
        if (count % 15 == 0 && bossObj != null) {
            bulletObjList.add(new BulletObj(bulletImg, bossObj.getX()+76, bossObj.getY()+85, 14, 25, 15, this));
            gameObjList.add(bulletObjList.get(bulletObjList.size()-1));
        }

    }

    //刷新线程，用来重新绘制页面
    private class RefreshThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                createObj();
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void death() {
        new Thread(() ->{
            try {
                Thread.sleep(3000);
                state = 0;
                bossObj = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
