package ui;

import java.awt.*;

public class MyButton {

    private int x, y, width, height;
    private String text;
    private Rectangle bounds;

    private boolean mouseOver;
    private boolean mousePress;
    public MyButton(String text, int x, int y, int width, int height) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initBounds();
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw( Graphics g ) {
        //body
        drawBody(g);

        //border
        drawBoarder(g);

        //Text
        drawText(g);
    }

    private void drawBoarder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);

        if ( mousePress ) {
            g.drawRect(x + 1, y + 1, width - 2, height -2 );
            g.drawRect(x + 2, y + 2, width - 4, height -4 );
        }
    }

    private void drawBody(Graphics g) {
        if ( mouseOver )
            g.setColor(Color.gray);
        else
            g.setColor(Color.white);
        g.fillRect(x, y, width, height);
    }

    private void drawText(Graphics g) {
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w/2 + width / 2, y + h/2 + height / 2);
    }

    public void setMouseOver( boolean mouseOver ) {
        this.mouseOver = mouseOver;
    }
    public void setMousePressed( boolean isMousePress ) { this.mousePress = isMousePress; }
    public Rectangle getBounds() {
        return bounds;
    }

    public void resetButton() {
        this.mouseOver = false;
        this.mousePress = false;
    }
}
