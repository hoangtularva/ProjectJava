package pushballgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

    private Game game;
    private int center = 300;
    private static final int DIAMETER = 40;
    private int x = new Random().nextInt(630);
    private int y = center;

    private double xa = 2;
    private double ya = 2;

    public static boolean ballMoving = false;

    public Ball(Game game) {
        this.game = game;
    }

    public void viTriDefault() {
        x = new Random().nextInt(630);
        y = center;
    }

    void move() {
        if (Ball.ballMoving) {
            boolean changeDirection = true;
            if (x + xa < 0) {
                xa = game.speedBall;
            } else if (x + xa > game.getWidth() - DIAMETER) {
                xa = -game.speedBall;
            } else if (y + ya <= 0) {
                game.gameOver();
            } else if (y + ya > game.getHeight() - DIAMETER) {
                game.gameOver();
            } else if (collision1()) {
                ya = -game.speedBall;
                game.speedBall += 0.3;
                y = game.racquet.getTopY() - DIAMETER;
                game.score1++;
            } else if (collision2()) {
                ya = game.speedBall;
                game.speedBall += 0.3;
                y = game.racquet2.getTopY() + DIAMETER - 10;
                game.score2++;
            } else {
                changeDirection = false;
            }
            x += xa;
            y += ya;
        }
    }

    public boolean collision1() {
        return game.racquet.getBounds().intersects(getBounds());
    }

    public boolean collision2() {
        return game.racquet2.getBounds().intersects(getBounds());
    }

    public void paint(Graphics2D g) {
        g.setColor(Color.red);
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }
}
