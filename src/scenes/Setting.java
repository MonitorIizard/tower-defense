package scenes;

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

    @Override
    public void mouseClicked(int x, int y) {

    }

    @Override
    public void mouseMoved(int x, int y) {

    }

    @Override
    public void mousePress(int x, int y) {

    }

    @Override
    public void mouseReleased(int x, int y) {

    }

    @Override
    public void mouseDragged(int x, int y) {

    }
}
