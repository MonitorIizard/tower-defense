package enemies;

import helperMethod.Constant;

import java.awt.*;
import static helperMethod.Constant.Direction.*;

public abstract class Enemy {
    protected float x, y;
    protected Rectangle bounds;
    protected int health;
    protected int ID;
    protected int enemyType;

    protected int lastDir;

    protected int maxHealth;

    protected boolean alive = true;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.ID = id;
        this.enemyType = enemyType;
        bounds = new Rectangle((int)x, (int)y, 32, 32);
        lastDir = -1;
        setStartHealth();
    }

    private void setStartHealth() {
        health = Constant.Enemies.GetStartHealth(enemyType);
        maxHealth = health;
    }

    public float getHealthBarFloat() {
        return health / (float) maxHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void move(float speed, int dir ) {
        lastDir = dir;
        switch (dir) {
            case LEFT :
                this.x -= speed;
                break;
            case UP :
                this.y -= speed;
                break;
            case RIGHT:
                this.x += speed;
                break;
            case DOWN:
                this.y += speed;
                break;
        }
    }

    public void setPos( int x, int y ) {
        //for position fix
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(int enemyType) {
        this.enemyType = enemyType;
    }
    public int getLastDir() {
        return lastDir;
    }

    public void hurt(int dmg) {
        this.health -= dmg;
        if ( health <= 0 ) {
            alive = false;
        }
    }

    public boolean isAlive() {
        return alive;
    }
}
