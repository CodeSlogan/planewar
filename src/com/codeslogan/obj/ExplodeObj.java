package com.codeslogan.obj;


import javax.tools.Tool;
import java.awt.*;

public class ExplodeObj extends GameObj {

    static Image[] pic = new Image[16];

    int explodeCount = 0;

    static {
        for (int i = 0; i < pic.length; i++) {
            pic[i] = Toolkit.getDefaultToolkit().getImage("imgs/explode/e"+(i+1)+".gif");
        }
    }

    public ExplodeObj(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(Graphics gImage) {
        if (explodeCount < 16) {
            img = pic[explodeCount];
            super.paint(gImage);
            explodeCount++;
        }
    }
}
