import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Game extends JFrame implements Runnable {

    private BufferedImage img;
    private GameScreen gameScreen;
    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    public Game() {
        importImg();
        gameScreen = new GameScreen(img);
        add(gameScreen);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/sprite-atlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e)  {
            e.printStackTrace();
        }
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

        while (true) {

            //update
            if ( System.nanoTime() - lastUpdate >= timePerUpdate) {
                lastUpdate = System.nanoTime();
                updates++;
            }

            //render
            if( System.nanoTime() - lastFrame >= timePerFrame) {
                lastFrame = System.nanoTime();
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

        //render
        //update
        //checking fps and ups
    }

    private void updateGame() {

        //System.out.println("Game updated");
    }

    public static void main( String[] args ) {
        Game game = new Game();
        game.setSize(640, 640);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setLocationRelativeTo(null);
        game.setVisible(true);

        //game.loopGame(); -> game.start()

        game.start();

    }
}
