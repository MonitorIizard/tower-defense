package scenes;

import helperMethod.LoadSave;
import main.Game;
import objects.PathPoint;
import objects.Tile;
import ui.ToolBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethod.Constant.Tiles.ROAD_TILE;

public class Editor extends GameScene implements  SceneMethods{

    private int[][] lvl;
    private Tile selectedTile;

    private int mouseX, mouseY;
    private boolean drawSelect;
    private int lastTileX, lastTileY;
    private int lastTileID;
    private ToolBar toolbar;

    private PathPoint start, end;

    public void update() {
        updateTick();
    }

    public Editor(Game game) {
        super(game);
        loadDefaultLevel();
//        lvl = LevelBuilder.getLevelData();
//        bottomBar = new BottomBar(0, 640, 640, 110, this);
        toolbar = new ToolBar(0, 640, 1280, 110, this);
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

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }


    @Override
    public void render(Graphics g) {
        drawLevel(g);
        toolbar.draw(g);
        drawSelectedTile(g);
        drawPathPoints(g);
    }

    private void drawPathPoints(Graphics g) {
        if ( start != null ) {
            g.drawImage(toolbar.getStartPathImg(), start.getxCord() * 64, start.getyCord() * 64, 64, 64, null );
        }

        if ( end != null ) {
            g.drawImage(toolbar.getEndPathImg(), end.getxCord() * 64, end.getyCord() * 64, 64, 64, null );
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
    private void drawSelectedTile(Graphics g) {
        if ( selectedTile != null && drawSelect ) {
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 64, 64, null);
        }
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = tile;
        drawSelect = true;
    }

    private void changeTile( int x, int y ) {
        if ( selectedTile != null ) {
            int tileX = x / 64;
            int tileY = y / 64;

            if(selectedTile.getId() >= 0) {
                if ( lastTileX == tileX && lastTileY == tileY &&
                        lastTileID == selectedTile.getId() )
                    return;

                lastTileX = tileX;
                lastTileY = tileY;
                lastTileID = selectedTile.getId();

                System.out.println(selectedTile.getId());
                lvl[tileY][tileX] = selectedTile.getId();
            } else {
                int id = lvl[tileY][tileX];
                if ( game.getTileManager().getTile(id).getTileType() == ROAD_TILE) {
                    if(selectedTile.getId() == -1)
                        start = new PathPoint(tileX, tileY);
                    if(selectedTile.getId() == -2)
                        end = new PathPoint(tileX, tileY);
                }
            }
        }
    }

    @Override
    public void mouseClicked(int x, int y) {
        if (y >=640 )
            toolbar.mouseClicked(x, y);
        else
            changeTile(mouseX, mouseY);
    }

    @Override
    public void mouseMoved(int x, int y) {
        toolbar.mouseMoved(x, y);
        drawSelect = false;

        if( y <= 640 ) {
            drawSelect = true;
            mouseX = x / 64;
            mouseY = y / 64;
            mouseX *= 64;
            mouseY *= 64;
        }
    }

    @Override
    public void mousePress(int x, int y) {
        toolbar.mousePressed(x,y);
    }

    @Override
    public void mouseReleased(int x, int y) {
        toolbar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if( y >= 640 ) {

        } else {
            changeTile(x, y);
        }
    }

    public void saveLevel() {
        LoadSave.SaveLevel("new_level", lvl, start, end);
        game.getPlaying().setLevel(lvl);
    }

    public void keyPressed( KeyEvent e ) {
        if ( e.getKeyCode() == KeyEvent.VK_R)
            toolbar.rotateSprite();
    }
}
