package inputs;

import main.GameStates;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.GameStates.*;

public class KeyboardListener implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("A is pressed!");
            GameStates.gameStates = MENU;
        }

        if(e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("S is pressed!");
            GameStates.gameStates = PLAYING;
        }

        if(e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("D is pressed!");
            GameStates.gameStates = SETTINGS;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
