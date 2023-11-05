package scener;

import main.Game;

//all game scene we have
public class GameScene {
    private Game game;
    public GameScene( Game game )  {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
