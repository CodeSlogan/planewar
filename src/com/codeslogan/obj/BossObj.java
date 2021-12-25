package com.codeslogan.obj;

import com.codeslogan.GamePanel;
import com.codeslogan.utils.Data;

import java.awt.*;

public class BossObj extends GameObj {
    int hp = 30;

    public BossObj() {
        super();
    }

    public BossObj(Image img, int x, int y, int width, int height, int speed, GamePanel panel) {
        super(img, x, y, width, height, speed, panel);
    }

    @Override
    public void paint(Graphics gImage) {
        super.paint(gImage);
        gImage.drawImage(Data.hpBox, 10, -10, null);
        gImage.setColor(Color.orange);
        gImage.fillRect(106, -2, this.hp*100/10, 15);
        if (x > 450 || x < -50) {
            speed = - speed;
        }
        x += speed;
        // 我方子弹对boss造成伤害
        for (ShellObj shellObj : Data.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())) {
                shellObj.setY(100);
                shellObj.setX(-100);
                Data.removeList.add(shellObj);
                hp--;
                GamePanel.score += 5;
            }
            if (hp <= 0) {
                GamePanel.state = 4;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
