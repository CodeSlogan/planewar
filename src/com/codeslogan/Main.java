package com.codeslogan;


import java.io.IOException;

/**
 * @Author: codeslogan
 * @Date: 2021-12-04 19:53
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GameFrame gameFrame = new GameFrame();
        GamePanel panel = new GamePanel(gameFrame);
        gameFrame.add(panel);
        gameFrame.setVisible(true);
    }
}
