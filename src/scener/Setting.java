package scener;

import main.Game;

import java.awt.*;

public class Setting extends GameScene implements SceneMethods {

    public Setting(Game game) {
        super(game);
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0,0, 640, 640);
    }
}
