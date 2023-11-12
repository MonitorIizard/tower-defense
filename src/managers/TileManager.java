package managers;

import helperMethod.ImgFix;
import helperMethod.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethod.Constant.Tiles.*;

public class TileManager {

    public Tile GRASS, WATER;
    public Tile HORIZONTAL_ROAD, VERTICAL_ROAD;
    public Tile  BR_WATER_CORNER, TL_WATER_CORNER, TR_WATER_CORNER, BL_WATER_CORNER;
    public Tile  R_B_JOINT_ROAD, L_B_JOINT_ROAD, R_T_JOINT_ROAD, L_T_JOINT_ROAD;
    public Tile TBeach, LBeach, RBeach, BBeach;
    public Tile TIslands, LIslands, RIslands, BIslands;
    public Tile towerLocation;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();

    public ArrayList<Tile> roadsS= new ArrayList<>();
    public ArrayList<Tile> roadsC= new ArrayList<>();
    public ArrayList<Tile> corners= new ArrayList<>();
    public ArrayList<Tile> beaches= new ArrayList<>();
    public ArrayList<Tile> islands= new ArrayList<>();


    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(6, 1), id++, GRASS_TILE));
        tiles.add(WATER = new Tile(getAniSprites(7, 3), id++, WATER_TILE));

        roadsS.add(HORIZONTAL_ROAD = new Tile(getSprite(3, 1), id++, ROAD_TILE));
        roadsS.add(VERTICAL_ROAD = new Tile( ImgFix.getRotImg( getSprite(3, 1), 90), id++, ROAD_TILE));

        roadsC.add(L_B_JOINT_ROAD =
                new Tile(
                        ImgFix.getRotImg( getSprite(3, 2), 0), id++, ROAD_TILE));
        roadsC.add(R_B_JOINT_ROAD =
                new Tile(
                        ImgFix.getRotImg( getSprite(3, 2), 270), id++, ROAD_TILE));
        roadsC.add(L_T_JOINT_ROAD =
                new Tile(
                        ImgFix.getRotImg( getSprite(3, 2), 90), id++, ROAD_TILE));
        roadsC.add(R_T_JOINT_ROAD =
                new Tile(
                        ImgFix.getRotImg( getSprite(3, 2), 180), id++, ROAD_TILE));

        corners.add(BR_WATER_CORNER
                = new Tile(
                        ImgFix.getBuildRotImg(getAniSprites(7, 3),getSprite(7,1), 270), id++, WATER_TILE));
        corners.add(TL_WATER_CORNER
                = new Tile(
                        ImgFix.getBuildRotImg(getAniSprites(7, 3),getSprite(7,1), 90),id++,WATER_TILE));
        corners.add(TR_WATER_CORNER
                = new Tile(
                ImgFix.getBuildRotImg(getAniSprites(7, 3),getSprite(7,1), 180),id++,WATER_TILE));
        corners.add(BL_WATER_CORNER
                = new Tile(
                ImgFix.getBuildRotImg(getAniSprites(7, 3),getSprite(7,1), 0),id++,WATER_TILE));

        beaches.add(TBeach =
                new Tile(ImgFix.getBuildRotImg(
                        getAniSprites(7, 3),getSprite(7,2),180),id++,WATER_TILE));
        beaches.add(RBeach =
                new Tile(ImgFix.getBuildRotImg(
                        getAniSprites(7, 3),getSprite(7,2),270),id++,WATER_TILE));
        beaches.add(BBeach =
                new Tile(ImgFix.getBuildRotImg(
                        getAniSprites(7, 3),getSprite(7,2), 0 ),id++,WATER_TILE));
        beaches.add(LBeach =
                new Tile(ImgFix.getBuildRotImg(
                        getAniSprites(7, 3),getSprite(7,2), 90),id++,WATER_TILE));

        islands.add(TIslands =
                new Tile(ImgFix.getBuildRotImg(
                        getAniSprites(7, 3),getSprite(8,1), 180 ),id++,WATER_TILE));
        islands.add(RIslands =
                new Tile(ImgFix.getBuildRotImg(
                        getAniSprites(7, 3),getSprite(8,1), 270),id++,WATER_TILE));
        islands.add(BIslands =
                new Tile(ImgFix.getBuildRotImg(
                        getAniSprites(7, 3),getSprite(8,1), 0 ),id++,WATER_TILE));
        islands.add(LIslands =
                new Tile(ImgFix.getBuildRotImg(
                        getAniSprites(7, 3),getSprite(8,1), 90),id++,WATER_TILE));

        tiles.addAll(roadsS);
        tiles.addAll(roadsC);
        tiles.addAll(corners);
        tiles.addAll(beaches);
        tiles.addAll(islands);

        tiles.add(towerLocation = new Tile((getSprite(6, 2)), id++, LOCATION));

    }

    private BufferedImage[] getImgs(int firstX, int firstY, int secX, int secY) {
        return new BufferedImage[]{getSprite(firstX, firstY), getSprite(secX, secY)};
    }
    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public BufferedImage getSprite( int id ) {
        return tiles.get(id).getSprite();
    }

    private BufferedImage[] getAniSprites( int xCord, int yCord ) {
        BufferedImage[] arr = new BufferedImage[3];
        for ( int i = 0; i < 3; i++ ) {
            arr[i] = getSprite(xCord + i, yCord);
        }
        return arr;
    }
    private BufferedImage getSprite(int xCord, int yCord) {
        return atlas.getSubimage(xCord * 64 , yCord * 64, 64, 64);
    }

    public BufferedImage getAniSprite( int id, int animationIndex ) {
        return tiles.get(id).getSprite(animationIndex);
    }

    public Tile getTile( int id ) {
        return tiles.get(id);
    }

    public ArrayList<Tile> getRoadsS() {
        return roadsS;
    }

    public ArrayList<Tile> getRoadsC() {
        return roadsC;
    }

    public ArrayList<Tile> getCorners() {
        return corners;
    }

    public ArrayList<Tile> getBeaches() {
        return beaches;
    }

    public ArrayList<Tile> getIslands() {
        return islands;
    }

    public boolean isSpriteAnimation(int spriteID) {
        return tiles.get(spriteID).isAnimation();
    }
}
