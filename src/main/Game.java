package main;

import inputs.KeyboardListener;
import inputs.MyMouseListener;
import scener.Menu;
import scener.Playing;
import scener.Setting;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private MyMouseListener myMouseListener;
    private KeyboardListener keyboardListener;

    // Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Setting setting;

    public Game() {
        initClasses();
        add(gameScreen);
        pack();
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        setting = new Setting(this);
    }

    private void initInput() {
        myMouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();

        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000_000_000.0 / FPS_SET;
        double timePerUpdate = 1000_000_000.0 / UPS_SET;

        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();

        int frames = 0;
        int updates = 0;

        long lastTimeCheck = System.currentTimeMillis();

        long now;

        while (true) {
            now = System.nanoTime();
            //update
            if ( now - lastUpdate >= timePerUpdate) {
                lastUpdate = now;
                updates++;
            }

            //render
            if( now - lastFrame >= timePerFrame) {
                lastFrame = now;
                gameScreen.repaint();
                frames++;
            }

            if( System.currentTimeMillis() - lastTimeCheck >= 1000 ) {
                System.out.printf("FPS : %d | UPS : %d\n", frames, updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }

    }

    private void updateGame() {

        //System.out.println("main.Game updated");
    }

    public static void main( String[] args ) {
        Game game = new Game();
        game.initInput();
        game.setSize(640, 640);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);

        //game.loopGame(); -> game.start()

        game.start();
    }

    public Render getRender() {
        return render;
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Setting getSetting() {
        return setting;
    }
}
