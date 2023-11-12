package scenes;

import main.Game;

import java.awt.image.BufferedImage;

//all game scene we have
public class GameScene {
    protected int animationIndex;
    protected int ANIMATION_SPEED = 25;
    protected int tick;
    protected Game game;
    public GameScene( Game game )  {
        this.game = game;
    }

    protected boolean isAnimation( int spriteID ) {
        return game.getTileManager().isSpriteAnimation(spriteID);
    }

    protected BufferedImage getSprite(int spriteID ) {
        return game.getTileManager().getSprite(spriteID);
    }

    protected BufferedImage getSprite(int spriteID, int animationIndex ) {
        return game.getTileManager().getAniSprite(spriteID, animationIndex);
    }
    public Game getGame() {
        return game;
    }
}
