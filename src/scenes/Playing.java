package scenes;

import helperMethod.LoadSave;
import main.Game;
import managers.EnemyManager;
import managers.TowerManager;
import objects.PathPoint;
import objects.Tower;
import ui.ActionBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static helperMethod.Constant.Tiles.LOCATION;

public class Playing extends GameScene implements SceneMethods {
    private int[][] lvl;
    private ActionBar bottomBar;
    private EnemyManager enemyManager;
    private TowerManager towerManager;

    private int mouseX, mouseY;
    private int tick = 0;
    private int animationIndex = 0;
    private PathPoint start, end;
    private Tower selectedTower;

    public Playing(Game game) {
        super(game);
        loadDefaultLevel();
        //the lvl
        //title manager
//        lvl = LevelBuilder.getLevelData();
        bottomBar = new ActionBar(0, 640, 1280, 110, this);
        enemyManager = new EnemyManager(this, start, end);
        towerManager = new TowerManager(this);
    }

    private void drawSelectedTower(Graphics g) {
        if ( selectedTower != null )
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, null);
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        bottomBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        drawSelectedTower(g);
        drawHighLight(g);
        updateTick();
    }

    private void drawHighLight(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, 64,64);
    }

    private void updateTick() {
        tick++;
        if ( tick >= 20 ) {
            tick = 0;
            animationIndex++;
            if ( animationIndex >= 3)
                animationIndex = 0;
        }
    }

    private void drawLevel(Graphics g) {
        for ( int y = 0; y < lvl.length; y++ ) {
            for ( int x = 0; x < lvl[y].length; x++ ) {
                int id = lvl[y][x];
                if (isAnimation(id)) {
                    g.drawImage(getSprite(id, animationIndex), x*64, y*64, null);
                } else {
                    g.drawImage(getSprite(id), x * 64, y * 64, null);
                }
            }
        }
    }

    public void update() {
        updateTick();
        enemyManager.update();
        towerManager.update();
    }
    public void SetSelectedTower(Tower selectedTower) {
        this.selectedTower = selectedTower;
    }

//    private BufferedImage getSprite(int spriteID, int animationIndex ) {
//        return game.getTileManager().getAniSprite(spriteID, animationIndex);
//    }

    private void loadDefaultLevel() {

        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    public void setLevel(int[][] lvl) {
        this.lvl = lvl;
    }

    public int getTileType( int x, int y ) {
        int xCord = x/64;
        int yCord = y/64;

        if ( xCord < 0 || xCord > 19 ) {
            return 0;
        }

        if ( yCord < 0 || yCord > 10 ) {
            return 0;
        }

        int id = lvl[y/64][x/64];
        return game.getTileManager().getTile(id).getTileType();
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >=640 )
            bottomBar.mouseClicked(x, y);
        else {
            if (selectedTower != null ) {
                if ( getTileType(x, y + 64) == LOCATION ) {
                    if (getTowerAt(mouseX, mouseY) == null ) {
                        towerManager.addTower(selectedTower, x, y);
                        selectedTower = null;
                    }
                }
            } else {
                //get tower if exist on x y
                Tower t = getTowerAt(mouseX, mouseY);
                if ( t == null ) {
                    return;
                } else {
                    bottomBar.displayTower(t);
                }
            }
        }
    }
    @Override
    public void mouseMoved(int x, int y) {
        bottomBar.mouseMoved(x, y);

        if( y <= 640 ) {
            mouseX = x / 64;
            mouseY = y / 64;
            mouseX *= 64;
            mouseY *= 64;
        }
    }

    @Override
    public void mousePress(int x, int y) {
        if (y >=640 )
            bottomBar.mousePressed(x, y);
    }


    @Override
    public void mouseReleased(int x, int y) {
        bottomBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {

    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x,y);
    }


    public void keyPressed(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            selectedTower = null;
        }
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }
}
