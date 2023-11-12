package ui;

import helperMethod.Constant;
import objects.Tower;
import scenes.Playing;
import java.awt.*;
import java.awt.image.BufferedImage;

import static main.GameStates.MENU;
import static main.GameStates.setGameStates;

public class ActionBar extends Bar{
    private Tower displayTower;
    private Playing playing;
    private MyButton bMenu;

    private Tower selectedTower;
    private MyButton[] towerButtons;

    public ActionBar(int x, int y, int width, int height, Playing playing ) {
        super (x, y, width, height);
        this.playing = playing;

        initButton();
    }

    public void initButton() {

        this.bMenu = new MyButton("Menu", 0, 640, 100, 50 );

        towerButtons = new MyButton[2];

        int w = 64;
        int h = 128;
        int xStart = 110;
        int yStart = 650;
        int xOffSet = (int) (w * 1.1f);

        for ( int i = 0; i < towerButtons.length; i++ ) {
            towerButtons[i] = new MyButton("", xStart + xOffSet * i, yStart, w, h, i);
        }
    }



    public void draw( Graphics g ) {

        //Background
        g.setColor( new Color(220, 123, 15));
        g.fillRect(x, y, width, height);
        //button
        for ( MyButton b : towerButtons ) {
            b.draw(g);
        }
        drawButtons(g);
        drawDisplayedTower(g);
    }

    private void drawDisplayedTower(Graphics g) {
        if (displayTower != null) {
            g.setColor(Color.black);
//            g.fillRect();
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayTower.getTowerType()], 900, 640, 64, 128, null );
            g.drawString("" + Constant.Towers.GetName(displayTower.getTowerType()), 984 , 660);
            g.drawString("ID : " + displayTower.getId(), 984 , 680);

            drawDisplayedTowerBorder(g);
            drawTowerRange(g);
        }
    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.cyan);
        g.drawRect(displayTower.getX(), displayTower.getY(), 64, 128);
    }

    public void mouseClicked(int x, int y) {
        if( bMenu.getBounds().contains(x,y) ) {
            setGameStates(MENU);
        } else {
            for ( MyButton b : towerButtons ) {
                if ( b.getBounds().contains(x, y)) {
                    selectedTower = new Tower(0, 0, -1, b.getID());
                    playing.SetSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        for ( MyButton b : towerButtons ) {
            b.setMouseOver(false);
        }
        bMenu.setMouseOver(false);

        if ( bMenu.getBounds().contains(x, y) ) {
            bMenu.setMouseOver(true);
        } else {
            for ( MyButton b : towerButtons ) {
                if ( b.getBounds().contains(x, y))
                    b.setMouseOver(true);
            }
        }
    }

    public void mousePressed(int x, int y) {
        if ( bMenu.getBounds().contains(x, y) ) {
            bMenu.setMousePressed(true);
        } else {
            for ( MyButton b : towerButtons ) {
                if ( b.getBounds().contains(x, y))
                    b.setMousePressed(true);
            }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetButton();
        for ( MyButton b : towerButtons ) {
            b.resetButton();
        }
    }

    public void drawButtons(Graphics g) {
        this.bMenu.draw(g);

        int i = 0;
        for ( MyButton b : towerButtons ) {
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getID()], b.x, b.y , b.width, b.height, null);
            drawButtonFeedBack(g, b);
        }
    }

    public void drawTowerRange(Graphics g) {
        g.setColor(Color.WHITE);
        int range = (int)displayTower.getRange() * 2;
        g.drawOval(displayTower.getX() - range / 2 + 32, displayTower.getY() - range / 2 + 32, range, range) ;
    }

    public BufferedImage getButtImg(int id ) {
        return playing.getGame().getTileManager().getSprite(id);
    }
    public void displayTower(Tower t) {
        displayTower = t;
    }
}

