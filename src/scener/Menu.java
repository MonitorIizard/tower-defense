package scener;

import main.Game;
import ui.MyButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods {

    private BufferedImage img;
    private ArrayList<BufferedImage> sprite = new ArrayList<>();
    private Random random;

    private MyButton bPlaying, bSetting, bQuit;

    public Menu(Game game) {
        super(game);
        importImg();
        loadSprites();
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w/3;
        int x = 640 /2 - w/2;
        int y = 150;
        int yOffSet = 100;

        bPlaying = new MyButton("Play", x, y, w, h);
        bSetting = new MyButton("Setting", x, y + yOffSet, w, h);
        bQuit = new MyButton("Quit", x, y + yOffSet * 2, w, h);
    }

    @Override
    public void render(Graphics g) {
        drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        System.out.println(x + " " + y);
        if (bPlaying.getBounds().contains(x,y)) {
            System.out.println("set states");
            setGameStates(PLAYING);
        } else if (bQuit.getBounds().contains(x, y)) {
            System.exit(0);
        } else if (bSetting.getBounds().contains(x, y)) {
            setGameStates(SETTINGS);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        if (bPlaying.getBounds().contains(x,y)) {
            bPlaying.setMouseOver(true);
        }  else if (bSetting.getBounds().contains(x, y)) {
            bSetting.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        }
    }

    @Override
    public void mousePress(int x, int y) {
        if(bPlaying.getBounds().contains(x, y)) {
            bPlaying.setMousePressed(true);
        } else if (bSetting.getBounds().contains(x, y)) {
            bSetting.setMousePressed(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bPlaying.resetButton();
        bQuit.resetButton();
        bSetting.resetButton();
    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bSetting.draw(g);
        bQuit.draw(g);
    }

    private void loadSprites() {
        for (int y = 0; y < 20; y++ ) {
            for ( int x = 0; x < 20; x++ ) {
                sprite.add(img.getSubimage(5*64, 1*64, 64, 64) );
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
