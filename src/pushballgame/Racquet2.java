package pushballgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Racquet2 {

    private static final int Y = 10;
    private static final int WIDTH = 120;
    private static final int HEIGHT = 20;
    private int x = 250;
    private double xa = 0;
    private Game game;

    public Racquet2(Game game) {
        this.game = game;
    }

    public void move() {
        if (x + xa > 0 && x + xa < game.getWidth() - WIDTH) {
            x += xa;
        }
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.green);
        g.fillRect(x, Y, WIDTH, HEIGHT);

    }

    public void keyReleased(KeyEvent e) {
        xa = 0;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            xa = -5;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            xa = 5;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, Y, WIDTH, HEIGHT);
    }

    public int getTopY() {
        return Y;
    }
}
