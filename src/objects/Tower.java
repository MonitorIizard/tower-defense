package objects;

import static helperMethod.Constant.Towers.*;

public class Tower {
    private int x, y, id;
    private int towerType;
    private float dmg, range, cooldown;
    public Tower( int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDefaultDMG();
        setDefaultRange();
        setDefaultCoolDown();
    }

    private void setDefaultCoolDown() {
        cooldown = GetDefaultCooldown(towerType);
    }

    private void setDefaultRange() {
        range = GetDefaultRange(towerType);
    }

    private void setDefaultDMG() {
        dmg = GetDefaultDMG(towerType);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTowerType() {
        return towerType;
    }

    public void setTowerType(int towerType) {
        this.towerType = towerType;
    }

    public float getDmg() {
        return dmg;
    }

    public float getRange() {
        return range;
    }

    public float getCooldown() {
        return cooldown;
    }
}
