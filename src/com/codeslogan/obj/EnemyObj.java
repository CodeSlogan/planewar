package com.codeslogan.obj;

import com.codeslogan.GamePanel;
import com.codeslogan.utils.Data;

import java.awt.*;

public class EnemyObj extends GameObj {
    private int hp;
    public EnemyObj() {
        super();
    }

    public EnemyObj(Image img, int x, int y, int width, int height, int speed, int hp, GamePanel panel) {
        super(img, x, y, width, height, speed, panel);
        this.hp = hp;
    }

    @Override
    public void paint(Graphics gImage) {
        super.paint(gImage);
        y += speed;

        // 模拟我方飞机撞机敌机
        if (this.getRec().intersects(this.panel.plane.getRec())) {
            GamePanel.state = 3;
        }

        if (y > Data.HEIGHT) {
            this.x = -200;
            this.y = 200;
            Data.removeList.add(this);
        }

        // 模拟我方子弹击中敌方飞机
        for (ShellObj shellObj : Data.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                shellObj.setX(-100);
                shellObj.setY(100);
                Data.removeList.add(shellObj);
                this.hp--;
                GamePanel.score++;
            }
            if (hp <= 0) {
                ExplodeObj explodeObj = new ExplodeObj(this.x, this.y);
                Data.explodeObjList.add(explodeObj);
                Data.removeList.add(explodeObj);
                this.x = -200;
                this.y = 200;
                Data.removeList.add(this);
            }

        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
