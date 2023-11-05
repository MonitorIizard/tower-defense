import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {
    private Random random;
    private BufferedImage img;
    private ArrayList<BufferedImage> sprite = new ArrayList<>();
    public GameScreen( BufferedImage img ) {
        random = new Random();
        this.img = img;

        loadSprites();

    }

    private void loadSprites() {
        for (int y = 0; y < 20; y++ ) {
            for ( int x = 0; x < 20; x++ ) {
                sprite.add(img.getSubimage(x*64, y*64, 64, 64) );
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);

        g.drawImage(sprite.get(1), random.nextInt(640), 0, null);

    }

    private Color getRGBcolor() {
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);

        return new Color(r, g, b);
    }
}
