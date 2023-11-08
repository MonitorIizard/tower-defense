package scener;

import helperMethod.LevelBuilder;
import main.Game;
import managers.TileManager;
import ui.MyButton;

import java.awt.*;

import static main.GameStates.MENU;
import static main.GameStates.setGameStates;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private TileManager tileManager;
    MyButton bMenu;

    public Playing(Game game) {
        super(game);

        //the lvl
        //title manager
        lvl = LevelBuilder.getLevelData();
        tileManager = new TileManager();

        initButton();
    }
    @Override
    public void render(Graphics g) {
        for ( int y = 0; y < lvl.length; y++ ) {
            for ( int x = 0; x < lvl[y].length; x++ ) {
                int id = lvl[y][x];
                g.drawImage(tileManager.getSprite(id), x*64, y*64, null);
            }
        }

        drawButton(g);
    }

    public void initButton() {
        this.bMenu = new MyButton("Menu", 640 - 100, 0, 100, 50 );
    }

    public void drawButton(Graphics g) {
        this.bMenu.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y) {
        if( bMenu.getBounds().contains(x,y) ) {
            setGameStates(MENU);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        if ( bMenu.getBounds().contains(x, y) ) {
            bMenu.setMouseOver(true);
        }
    }

    @Override
    public void mousePress(int x, int y) {
        if ( bMenu.getBounds().contains(x, y) ) {
            bMenu.setMousePressed(true);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetButton();
    }
}
