package com.codeslogan.obj;


import java.awt.*;

public class BgObj extends GameObj {
    public BgObj() {
        super();
    }

    public BgObj(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }

    @Override
    public void paint(Graphics gImage) {
        super.paint(gImage);
        y += speed;
    }
}
