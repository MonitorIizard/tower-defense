package main;


import helperMethod.LoadSave;
import managers.TileManager;
import objects.PathPoint;
import scenes.Editor;
import scenes.Menu;
import scenes.Playing;
import scenes.Setting;

import javax.swing.*;

public class Game extends JFrame implements Runnable {
    private GameScreen gameScreen;
    private Thread gameThread;

    private TileManager tileManager;
    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    // Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Setting setting;
    private Editor editor;

    public Game() {
        LoadSave.CreateFolder();

        createDefaultLevel();
        initClasses();

        add(gameScreen);
        pack();
    }

    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        editor = new Editor(this);
        setting = new Setting(this);
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
                updateGame();
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
        switch(GameStates.gameState) {
            case MENU -> {
                editor.update();
            }
            case PLAYING -> {
                playing.update();
            }
            case EDIT -> {
            }
            case SETTINGS -> {
            }
        }
    }

    public static void main( String[] args ) {
        Game game = new Game();
        game.gameScreen.initInput();
//        game.setSize(640, 64);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);
        game.setResizable(false);

        //game.loopGame(); -> game.start()

        game.start();
    }

    private void createDefaultLevel() {
        // 640 * 640 / 64 * 64 = 100
        int[] arr = new int[(640 * 1280) / 64 * 64];
        for ( int i = 0; i < arr.length; i++ ) {
            arr[i] = 0;
        }

        LoadSave.CreateLevel("new level", arr);
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

    public Editor getEditor() {
        return editor;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
