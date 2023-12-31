package inputs;

import main.Game;
import main.GameStates;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private Game game;
    public MyMouseListener(Game game) {
        this.game = game;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        switch (GameStates.gameState) {
            case MENU:
                game.getMenu().mouseDragged(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseDragged(e.getX(), e.getY());
                break;
            case SETTINGS:
                break;
            case EDIT:
                game.getEditor().mouseDragged(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gameState) {
            case MENU:
                game.getMenu().mouseMoved(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseMoved(e.getX(), e.getY());
                break;
            case SETTINGS:
                break;
            case EDIT:
                game.getEditor().mouseMoved(e.getX(), e.getY());
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("game switch");

            switch (GameStates.gameState) {
            case MENU:
                game.getMenu().mouseClicked(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseClicked(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSetting().mouseClicked(e.getX(), e.getY());
                break;
            case EDIT:
                game.getEditor().mouseClicked(e.getX(), e.getY());
                break;
            default:
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println(e.getX() + " : " + e.getY());

            switch (GameStates.gameState) {
                case MENU:
                    game.getMenu().mousePress(e.getX(), e.getY());
                    break;
                case PLAYING:
                    game.getPlaying().mousePress(e.getX(), e.getY());
                    break;
                case SETTINGS:
                    game.getSetting().mousePress(e.getX(), e.getY());
                    break;
                case EDIT:
                    game.getEditor().mousePress(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("game switch");

            switch (GameStates.gameState) {
                case MENU:
                    game.getMenu().mouseReleased(e.getX(), e.getY());
                    break;
                case PLAYING:
                    game.getPlaying().mouseReleased(e.getX(), e.getY());
                    break;
                case SETTINGS:
                    game.getSetting().mouseReleased(e.getX(), e.getY());
                    break;
                case EDIT:
                    game.getEditor().mouseReleased(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
