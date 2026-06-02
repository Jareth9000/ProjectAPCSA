import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DisplayPanel extends JPanel implements KeyListener {
    private int heroX;
    private int heroY;
    private BufferedImage hero;
    private BufferedImage background;

    public DisplayPanel () {
        heroX = 0;
        heroY = 275;
        try {
            hero = ImageIO.read(new File("src/pictures/download.png"));
            background = ImageIO.read(new File("src/pictures/background.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        addKeyListener(this);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,null);
        g.drawImage(hero,heroX,heroY,null);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            heroY += 5;
        } else if (keyCode == KeyEvent.VK_S) {
            heroY -= 5;
        } else if (keyCode == KeyEvent.VK_A) {
            heroX -= 5;
        } else if (keyCode == KeyEvent.VK_D) {
            heroX += 5;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveHero() {
        heroX += 5;
    }

    public void actionPerformed (ActionEvent e) {

    }
}