package com.codeslogan.obj;

import com.codeslogan.GamePanel;
import com.codeslogan.utils.Data;

import java.awt.*;

public class BulletObj extends GameObj {
    public BulletObj(Image img, int x, int y, int width, int height, int speed, GamePanel panel) {
        super(img, x, y, width, height, speed, panel);
    }

    @Override
    public void paint(Graphics gImage) {
        super.paint(gImage);
        y += speed;
        // 敌方子弹与我方飞机的碰撞检测
        if (this.getRec().intersects(this.panel.plane.getRec())) {
            GamePanel.state = 3;
        }

        if (y > Data.HEIGHT) {
            this.x = -300;
            this.y = 300;
            Data.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
