package com.codeslogan.obj;

import com.codeslogan.GamePanel;
import com.codeslogan.utils.Data;

import java.awt.*;

public class ShellObj extends GameObj {
    public ShellObj() {
        super();
    }

    public ShellObj(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }

    public ShellObj(Image img, int x, int y, int width, int height, int speed, GamePanel panel) {
        super(img, x, y, width, height, speed, panel);
    }

    @Override
    public void paint(Graphics gImage) {
        super.paint(gImage);
        y -= speed;
        if (y < 0) {
            this.x = -100;
            this.y = 100;
            Data.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
