package scener;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Menu extends GameScene implements SceneMethods {

    private BufferedImage img;
    private ArrayList<BufferedImage> sprite = new ArrayList<>();
    private Random random;

    public Menu(Game game) {
        super(game);
    }
    @Override
    public void render(Graphics g) {
        random = new Random();
        importImg();
        loadSprites();
        g.drawImage(sprite.get(1), random.nextInt(640), 0, null);
    }

    private void loadSprites() {
        for (int y = 0; y < 20; y++ ) {
            for ( int x = 0; x < 20; x++ ) {
                sprite.add(img.getSubimage(x*64, y*64, 64, 64) );
            }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/sprite-atlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e)  {
            e.printStackTrace();
        }
    }
}
