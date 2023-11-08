package managers;

import helperMethod.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {

    public Tile GRASS, WATER, ROAD;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        tiles.add(GRASS = new Tile(getSprite(4, 1)));
        tiles.add(WATER = new Tile(getSprite(5, 1)));
        tiles.add(ROAD = new Tile(getSprite(6, 1)));

    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite( int id ) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 64 , yCord * 64, 64, 64);
    }
}
