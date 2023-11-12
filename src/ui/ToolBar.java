package ui;

import helperMethod.LoadSave;
import objects.Tile;
import scenes.Editor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static main.GameStates.MENU;
import static main.GameStates.setGameStates;

public class ToolBar extends Bar{
    private Editor editor;
    private MyButton bMenu, bSave;
    private Tile selectedTile;
    private MyButton currentButton ;
    private MyButton  bPathStart, bPathEnd;
    private MyButton  bLocation;
    private int currentIndex = 0;
    private BufferedImage pathS, pathE;
    private Map<MyButton, ArrayList<Tile>> map = new HashMap<MyButton, ArrayList<Tile>>();

    private MyButton bGrass, bWater, bRoadS, bRoadC, bWaterC, bWaterB, bWaterI;
    public ToolBar(int x, int y, int width, int height, Editor editing) {
        super(x, y, width, height);
        this.editor = editing;
        initPathImgs();
        initButton();
    }

    private void initPathImgs() {
        pathS = LoadSave.getSpriteAtlas().getSubimage(3 * 64, 3 * 64, 64, 64);
        pathE = LoadSave.getSpriteAtlas().getSubimage(3 * 64, 4 * 64, 64, 64);
    }

    public void draw( Graphics g ) {

        //Background
        g.setColor( new Color(220, 123, 15));
        g.fillRect(x, y, width, height);
        //button
        drawButtons(g);

    }

    public void initButton() {
        this.bMenu = new MyButton("Menu", 0, 640, 100, 50 );
        this.bSave = new MyButton("Save", 0, 700, 100, 50 );

        int w = 64;
        int h = 64;
        int xStart = 110;
        int yStart = 650;
        int xOffSet = (int) (w * 1.1f);

        int i = 0;

        bGrass = new MyButton("Grass", xStart, yStart, w, h, i++ );
        bWater = new MyButton("Water", xStart + xOffSet, yStart, w, h, i++);

        initMapButton(bRoadS, editor.getGame().getTileManager().getRoadsS(), xStart, yStart, xOffSet, w, h, i++);
        initMapButton(bRoadC, editor.getGame().getTileManager().getRoadsC(), xStart, yStart, xOffSet, w, h, i++);
        initMapButton(bWaterC, editor.getGame().getTileManager().getCorners(), xStart, yStart, xOffSet, w, h, i++);
        initMapButton(bWaterB, editor.getGame().getTileManager().getBeaches(), xStart, yStart, xOffSet, w, h, i++);
        initMapButton(bWaterI, editor.getGame().getTileManager().getIslands(), xStart, yStart, xOffSet, w, h, i++);

        bPathStart = new MyButton("Pathstart", xStart + xOffSet * i, yStart, w, h, i++ );
        bPathEnd = new MyButton("PathEnd", xStart + xOffSet * i, yStart, w, h, i++ );

        bLocation = new MyButton("Location", xStart + xOffSet * i, yStart, w, h, 20 );
    }

    private void initMapButton(MyButton b, ArrayList<Tile> list, int x, int y, int xOff, int w, int h, int id) {
        b = new MyButton("" , x + xOff * id, y, w, h, id );
        map.put(b, list);
    }
    public void drawButtons(Graphics g) {
        this.bMenu.draw(g);
        this.bSave.draw(g);

//        this.bPathStart.draw(g);
//        this.bPathEnd.draw(g);
        drawPathButton(g, bPathStart, pathS);
        drawPathButton(g, bPathEnd, pathE);

//        drawTileButtons(g);
        drawNormalButton(g, bGrass);
        drawNormalButton(g, bWater);
        drawNormalButton(g, bLocation);
        drawMapButtons(g);
        drawSelectedTile(g);
    }

    private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
        g.drawImage(img, b.x, b.y, b.width, b.height, null);
        drawButtonFeedBack(g, b);
    }

    private void drawNormalButton(Graphics g, MyButton b) {
        g.drawImage(getButtImg(b.getID()), b.x, b.y, b.width, b.height, null);
        drawButtonFeedBack(g, b);
    }

    private void drawMapButtons(Graphics g) {
        for( Map.Entry<MyButton, ArrayList<Tile>> entry : map.entrySet()) {
            MyButton b = entry.getKey();
            BufferedImage img = entry.getValue().get(0).getSprite();

            g.drawImage(img, b.x, b.y, b.width, b.height, null );
            drawButtonFeedBack(g, b);
        }
    }

    private void drawSelectedTile(Graphics g) {
        if ( selectedTile != null ) {
            g.drawImage(selectedTile.getSprite(), 1200, 670, 50, 50, null);
            g.setColor(Color.black);
            g.drawRect(1200, 670, 50, 50);
        }
    }
//    private void drawTileButtons(Graphics g) {
//        for( MyButton b : tileButtons ) {
//            //Sprite
//            g.drawImage(getButtImg(b.getID()), b.x, b.y, b.width, b.height, null);
//
//            //MouseOver
//            if(b.isMouseOver()) {
//                g.setColor(Color.WHITE);
//            } else {
//                g.setColor(Color.BLACK);
//            }
//
//            //Border
//            g.drawRect(b.x, b.y, b.width, b.height);
//
//            if( b.isMousePressed() ) {
//                g.drawRect(b.x +1, b.y + 1, b.width -2, b.height - 2);
//                g.drawRect(b.x +2, b.y + 2, b.width -4, b.height - 4);
//            }
//        }
//    }

    public void mouseClicked(int x, int y) {
        if( bMenu.getBounds().contains(x,y) ) {
            setGameStates(MENU);
        } else if (bSave.getBounds().contains(x, y)) {
            saveLevel();
        } else if (bWater.getBounds().contains(x, y)) {
            selectedTile = editor.getGame().getTileManager().getTile(1);
            editor.setSelectedTile(selectedTile);
        } else if (bGrass.getBounds().contains(x, y)) {
            selectedTile = editor.getGame().getTileManager().getTile(0);
            editor.setSelectedTile(selectedTile);
        } else if (bPathStart.getBounds().contains(x, y)) {
            selectedTile = new Tile(pathS, -1, -1);
            editor.setSelectedTile(selectedTile);
        } else if (bPathEnd.getBounds().contains(x, y)) {
            selectedTile = new Tile(pathE, -2, -2);
            editor.setSelectedTile(selectedTile);
        } else if (bLocation.getBounds().contains(x, y)) {
            selectedTile = editor.getGame().getTileManager().getTile(20);
            editor.setSelectedTile(selectedTile);
        } else {
            for ( MyButton b : map.keySet() ) {
                if ( b.getBounds().contains(x, y)) {
                    selectedTile = map.get(b).get(0);
                    editor.setSelectedTile(selectedTile);
                    currentButton = b;
                    currentIndex = 0;
                    return;
                }
            }
        }
    }

    public void mouseMoved(int x, int y) {
        bSave.setMouseOver(false);
        bMenu.setMouseOver(false);
        for ( MyButton b : map.keySet() ) {
            b.setMouseOver(false);
        }
        bPathStart.setMouseOver(false);
        bPathEnd.setMouseOver(false);

        if ( bMenu.getBounds().contains(x, y) ) {
            bMenu.setMouseOver(true);
        }  else if (bSave.getBounds().contains(x, y)) {
            bSave.setMouseOver(true);
            saveLevel();
        }  else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMouseOver(true);
        }  else if (bWater.getBounds().contains(x, y)) {
            bWater.setMouseOver(true);
        }  else if (bPathStart.getBounds().contains(x, y)) {
            bPathStart.setMouseOver(true);
        }  else if (bPathEnd.getBounds().contains(x, y)) {
            bPathEnd.setMouseOver(true);
            saveLevel();
        } else {
            for ( MyButton b : map.keySet() ) {
                if ( b.getBounds().contains(x, y)) {
                   b.setMouseOver(true);
                   return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
//        for ( MyButton b : map.keySet() ) {
//           b.setMousePressed(false);
//        }

        if ( bMenu.getBounds().contains(x, y) ) {
            bMenu.setMousePressed(true);
        }  else if (bSave.getBounds().contains(x, y)) {
            saveLevel();
            bSave.setMousePressed(true);
        }  else if (bGrass.getBounds().contains(x, y)) {
            bGrass.setMousePressed(true);
        }  else if (bWater.getBounds().contains(x, y)) {
            bWater.setMousePressed(true);
        } else if (bPathStart.getBounds().contains(x, y)) {
                bPathStart.setMousePressed(true);
        }  else if (bPathEnd.getBounds().contains(x, y)) {
                bPathEnd.setMousePressed(true);
        } else {
            for ( MyButton b : map.keySet() ) {
                System.out.println("T");
                if ( b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    System.out.println("T");
                    return;
                }
            }
        }

    }

    private void saveLevel() {
        editor.saveLevel();
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetButton();
        bSave.resetButton();
        bWater.resetButton();
        bGrass.resetButton();
        for ( MyButton b : map.keySet() ) {
            b.resetButton();
        }
    }

    public BufferedImage getButtImg(int id ) {
//        System.out.println("id = " +  id);
        return editor.getGame().getTileManager().getSprite(id);
    }

    public void rotateSprite() {
        currentIndex++;
        if ( currentIndex >= map.get(currentButton).size()) {
            currentIndex = 0;
        }

        selectedTile = map.get(currentButton).get(currentIndex);
        editor.setSelectedTile(selectedTile);
    }

    public BufferedImage getStartPathImg() {
        return pathS;
    }

    public BufferedImage getEndPathImg() {
        return pathE;
    }
}
