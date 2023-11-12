package managers;

import enemies.*;
import helperMethod.LoadSave;
import objects.PathPoint;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static helperMethod.Constant.Direction.*;
import static helperMethod.Constant.Enemies.*;
import static helperMethod.Constant.Tiles.*;

public class EnemyManager {
    private  PathPoint start, end;
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private Enemy testEnemy;
    private ArrayList<Enemy> enemies = new ArrayList<>();
//    private float speed = 0.5f;
    public EnemyManager(Playing playing, PathPoint start, PathPoint end) {
        this.playing = playing;
        enemyImgs = new BufferedImage[5];
        this.start = start;
        this.end = end;
        addEnemy(BANDIT);
        addEnemy( EYE);
        addEnemy(SPIDER);
        addEnemy(KNIGHT);
        addEnemy(BUSH);


        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();

        for ( int i = 0; i < 5; i++) {
            enemyImgs[i] = atlas.getSubimage(i * 64, 0, 64, 64);
        }
    }

    public void update() {
//        testEnemy.move(0.5f, 0);
        for ( Enemy e : enemies ) {
            // if is next tile road(pos, dir)
            updateEnemyMove(e);
        }
    }

    public void addEnemy(int enemyType) {
        int x = start.getxCord() * 64;
        int y = start.getyCord() * 64;


        switch (enemyType) {
            case SPIDER :
                enemies.add(new Spider(x, y, 0));
                break;
            case EYE :
                enemies.add(new Eye(x, y, 0));
                break;
            case BUSH:
                enemies.add(new Bush(x, y, 0));
                break;
            case KNIGHT:
                enemies.add(new Knight(x, y, 0));
                break;
            case BANDIT:
                enemies.add(new Bandit(x, y, 0));
                break;
        }
    }
    private void updateEnemyMove(Enemy e) {
        if (e.getLastDir() == -1) {
            setNewDirectionAndMove(e);
        }
        //e pos
        //e dir
        // tile at new possible pos
        int newX = (int)(e.getX() + getSpeedXAndWidth(e.getLastDir(), e.getEnemyType()));
        int newY = (int)(e.getY() + getSpeedYAndHeight(e.getLastDir(), e.getEnemyType()));

        if ( getTileType(newX, newY) == ROAD_TILE) {
            //forward
            e.move( GetSpeed(e.getEnemyType()) , e.getLastDir());
        } else if ( isAtEnd(e) ) {
            System.exit(0);
        } else {
            setNewDirectionAndMove(e);
        }
    }

    private void setNewDirectionAndMove(Enemy e) {
        int dir = e.getLastDir();

        // move into the current tile 100%
        int xCord = (int)(e.getX() / 64);
        int yCord = (int)(e.getY() / 64);

        fixEnemyOffSetTile(e, dir,  xCord, yCord);

        if (isAtEnd(e))
            return;

        if ( dir == LEFT || dir == RIGHT ) {
            int newY = (int)(e.getY() + getSpeedYAndHeight(UP, e.getEnemyType()));
            if (getTileType((int)e.getX(), newY) == ROAD_TILE ) {
                e.move(GetSpeed(e.getEnemyType()), UP);
            } else {
                e.move(GetSpeed(e.getEnemyType()), DOWN);
            }
        } else {
            int newX = (int)(e.getX() + getSpeedXAndWidth(e.getLastDir(), e.getEnemyType()));
            if (getTileType(newX, (int)e.getY()) == ROAD_TILE){
                e.move(GetSpeed(e.getEnemyType()), RIGHT);
            } else {
                e.move(GetSpeed(e.getEnemyType()), LEFT);
            }
        }
    }

    private void fixEnemyOffSetTile(Enemy e, int dir, int xCord, int yCord) {
        switch (dir) {
            case  RIGHT:
                if ( xCord < 19 )
                    xCord++;
                break;
            case  DOWN:
                if (yCord < 10)
                    yCord++;
                break;
        }

        e.setPos(xCord * 64, yCord * 64);
    }

    private int getTileType(int x, int y) {
        return playing.getTileType(x,y);
    }

    private float getSpeedXAndWidth(int dir, int type) {
        if  ( dir == LEFT )
                return -GetSpeed(type);
        else if (dir == RIGHT)
            return GetSpeed(type) + 64;

        return 0;
    }

    private boolean isAtEnd( Enemy e ) {
        if ( e.getX() == end.getxCord() * 64 )
            if ( e.getY() == end.getyCord() * 64 )
                return true;
        return  false;
    }

    private float getSpeedYAndHeight(int dir, int type) {
        if  ( dir == UP )
            return -GetSpeed(type);
        else if (dir == DOWN )
            return GetSpeed(type) + 64;

        return 0;
    }

    public void addEnemy( int x, int y ) {
    }
    public void draw(Graphics g) {
        for( Enemy e : enemies ) {
            if ( e.isAlive() ) {
                drawEnemy(e, g);
                drawHealthBar(e, g);
            }
        }
    }

    private void drawHealthBar(Enemy e, Graphics g) {
        g.setColor(Color.red);
        g.fillRect( (int) e.getX() + 15 - getNewBarWidth(e) / 2, (int) e.getY() - 10, getNewBarWidth(e), 3);
    }

    private int getNewBarWidth(Enemy e) {
        return (int) (e.getHealthBarFloat())  * 30;
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[e.getEnemyType()], (int)e.getX(), (int)e.getY(), null);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
