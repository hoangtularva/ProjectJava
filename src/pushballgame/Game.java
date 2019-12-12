package pushballgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class Game extends JPanel {

    public Ball ball = new Ball(this);
    public Racquet racquet = new Racquet(this);
    public Racquet2 racquet2 = new Racquet2(this);
    public double speedBall = 2;
    public double speed = 8;
    public int score1 = 0;
    public int score2 = 0;

    public Game() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racquet.keyReleased(e);
                racquet2.keyReleased(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                racquet.keyPressed(e);
                racquet2.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    Ball.ballMoving = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(ABORT);
                }
            }
        });
        setFocusable(true);
    }

    private void move() {
        ball.move();
        racquet.move();
        racquet2.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racquet.paint(g2d);
        racquet2.paint(g2d);
        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 40));
        g2d.drawString(String.valueOf(score1), 300, 650);
        g2d.drawString(String.valueOf(score2), 300, 70);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));
        if (!Ball.ballMoving) {
            g.drawString("Nhấn space để chơi, ESC để thoát", 150, 300);
        }
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Điểm của người chơi 1: " + score1 + "\nĐiểm của người chơi 2: " + score2, "Trò chơi kết thúc", JOptionPane.YES_NO_OPTION);
        score1 = 0;
        score2 = 0;
        Ball.ballMoving = false;
        ball.viTriDefault();
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Push Ball Game");
        Game game = new Game();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.setSize(650, 720);

        frame.setVisible(true);
        frame.setResizable(false);
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
