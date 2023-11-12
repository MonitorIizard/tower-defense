package managers;

import enemies.Enemy;
import helperMethod.LoadSave;
import helperMethod.Utilz;
import objects.Tower;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helperMethod.Constant.Towers.*;

public class TowerManager {

    private final Playing playing;
    private BufferedImage[] towerImgs;
    private Tower tower;
    private ArrayList<Tower> towers = new ArrayList<>();
    private int towerAmount = 0;
    private int tick = 0;
    private long currentTime = 0;

    public TowerManager(Playing playing) {
        this.playing = playing;

        loadTowerImgs();
    }

    public void addTower(Tower selTower, int xPos, int yPos) {
        towerAmount++;

        towers.add(new Tower(xPos / 64 * 64, yPos / 64 * 64, towerAmount++, selTower.getTowerType()));
    }

    private void loadTowerImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        towerImgs = new BufferedImage[2];
        for (int i = 0; i < 2; i++) {
            towerImgs[i] = atlas.getSubimage(64 * (11 + i), 0, 64, 128);
        }
    }

    public BufferedImage[] getTowerImgs() {
        return towerImgs;
    }
    public void draw (Graphics g) {
        for ( Tower t : towers)
            g.drawImage(towerImgs[t.getTowerType()], t.getX(), t.getY(), null);
    }


    public void update() {
        attackEnemyIfClose();
        tick++;
    }

    private void attackEnemyIfClose() {
        for ( Tower t : towers ) {
            for (Enemy e : playing.getEnemyManager().getEnemies() ) {
                if ( e.isAlive() ) {
                    if ( isEnemyInRange(t, e) ){
                        System.out.println(( System.currentTimeMillis() - currentTime) / 1000f);
                        if ( ( System.currentTimeMillis() - currentTime) / 1000f >= t.getCooldown()) {
                            e.hurt((int)t.getDmg());
                            currentTime = System.currentTimeMillis();
                        }
                    } else {

                    }
                }
            }
        }
    }

    private boolean isEnemyInRange(Tower t, Enemy e) {
        int range = Utilz.GetHypoDistance(t.getX(), e.getX(), t.getY(), e.getY());

        return range < t.getRange();
    }

    public Tower getTowerAt(int x, int y) {
        for ( Tower t : towers ) {
            if ( t.getX() == x ) {
                if ( t.getY() == y ) {
                    return t;
                }
            }
        }
        return null;
    }
}
