package Paint;

import java.awt.Color;
import java.awt.Graphics;

class Eraser implements Shape {
    private int centerX,centerY,radius;
    private Color eraserColor;

    public Eraser(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.eraserColor = Color.WHITE;
    }

    public void draw(Graphics g) {
        g.setColor(this.eraserColor);
        g.fillOval(this.centerX - this.radius, this.centerY - this.radius, 2 * this.radius, 2 * this.radius);
    }

    public int getCenterX() {
        return this.centerX;
    }

    public int getCenterY() {
        return this.centerY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return this.eraserColor;
    }

    public void setColor(Color color) {
        this.eraserColor = color;
    }

    public void setVertices(int[] xPoints, int[] yPoints) {
    }

    public void setDotted(boolean isDotted) {
    }

    public boolean isDotted() {
        return false;
    }
}
