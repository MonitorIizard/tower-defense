package objects;

import java.awt.image.BufferedImage;

public class Tile {
    private int tileType;
    private BufferedImage[] sprite;
    private int id;

    public Tile( BufferedImage sprite, int id, int tileType ) {
        this.sprite = new BufferedImage[1];
        this.sprite[0] = sprite;
        this.tileType = tileType;
        this.setId(id);
    }

    public Tile( BufferedImage[] sprite, int id,  int tileType) {
        this.sprite = sprite;
        this.setId(id);
        this.tileType = tileType;
    }

    public int getTileType() {
        return tileType;
    }

    public BufferedImage getSprite( ) {
        return sprite[0];
    }
    public BufferedImage getSprite( int animationIndex ) {
        return sprite[animationIndex];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAnimation() {
        return sprite.length > 1;
    }
}
