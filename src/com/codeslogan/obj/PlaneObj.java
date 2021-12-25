package com.codeslogan.obj;


import com.codeslogan.GamePanel;
import java.awt.*;


public class PlaneObj extends GameObj {
    public PlaneObj() {
        super();
    }

    public PlaneObj(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }

    public PlaneObj(Image img, int x, int y, int width, int height, int speed, GamePanel panel) {
        super(img, x, y, width, height, speed, panel);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void paint(Graphics gImage) {
        super.paint(gImage);

        // 与敌方boss的碰撞检测
        if (this.panel.bossObj != null && this.getRec().intersects(this.panel.bossObj.getRec())) {
            GamePanel.state = 3;
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }

    @Override
    public Image getImg() {
        return super.getImg();
    }
}
