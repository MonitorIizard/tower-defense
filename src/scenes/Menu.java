package scenes;

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
    private MyButton bPlaying,  bEdit, bSetting, bQuit;

    public Menu(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {
        int w = 150;
        int h = w/3;
        int x = 1280 /2 - w/2;
        int y = 150;
        int yOffSet = 100;

        bPlaying = new MyButton("Play", x, y, w, h);
        bEdit = new MyButton("Edit", x, y + yOffSet, w, h);
        bSetting = new MyButton("Setting", x, y + yOffSet * 3, w, h);
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
        } else if (bEdit.getBounds().contains(x, y)) {
            setGameStates(EDIT);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bPlaying.setMouseOver(false);
        bEdit.setMouseOver(false);
        bSetting.setMouseOver(false);
        bQuit.setMouseOver(false);

        if (bPlaying.getBounds().contains(x,y)) {
            bPlaying.setMouseOver(true);
        }  else if (bSetting.getBounds().contains(x, y)) {
            bSetting.setMouseOver(true);
        } else if (bQuit.getBounds().contains(x, y)) {
            bQuit.setMouseOver(true);
        } else if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMouseOver(true);
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
        } else if (bEdit.getBounds().contains(x, y)) {
            bEdit.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bPlaying.resetButton();
        bEdit.resetButton();
        bSetting.resetButton();
        bQuit.resetButton();
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    private void drawButtons(Graphics g) {
        bPlaying.draw(g);
        bEdit.draw(g);
//        bSetting.draw(g);
        bQuit.draw(g);
    }

}
