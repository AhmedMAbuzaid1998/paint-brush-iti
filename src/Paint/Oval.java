package Paint;

import java.awt.*;
import java.awt.geom.Ellipse2D;

class Oval implements Shape, Filling {
    private boolean isFilled, isDotted;
    private int startX, startY, width, height;
    private Color ovalColor;

    public Oval(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.ovalColor = Color.BLACK;
        this.isDotted = false;
    }

    private void drawDottedOval(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.ovalColor);

        float[] dashPattern = {5, 5};
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));

        int x = Math.min(this.startX, this.width);
        int y = Math.min(this.startY, this.height);
        int w = Math.abs(this.width - this.startX);
        int h = Math.abs(this.height - this.startY);

        g2d.draw(new Ellipse2D.Double(x, y, w, h));

        g2d.setStroke(new BasicStroke());
    }

    public void draw(Graphics g) {
        g.setColor(this.ovalColor);
        if (this.isDotted) {
            this.drawDottedOval(g);
        } else if (this.isFilled) {
            g.fillOval(Math.min(this.startX, this.width), Math.min(this.startY, this.height), Math.abs(this.width - this.startX), Math.abs(this.height - this.startY));
        } else {
            g.drawOval(Math.min(this.startX, this.width), Math.min(this.startY, this.height), Math.abs(this.width - this.startX), Math.abs(this.height - this.startY));
        }
    }

    public int getCenterX() {
        return (this.startX + this.width) / 2;
    }

    public int getCenterY() {
        return (this.startY + this.height) / 2;
    }

    public void setRadius(int radius) {
        // Not applicable for oval
    }

    public int getStartX() {
        return this.startX;
    }

    public int getStartY() {
        return this.startY;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHight() {
        return this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHight(int height) {
        this.height = height;
    }

    public Color getColor() {
        return this.ovalColor;
    }

    public void setColor(Color color) {
        this.ovalColor = color;
    }

    public void setFilled(boolean filled) {
        this.isFilled = filled;
    }

    public void setVertices(int[] xPoints, int[] yPoints) {
    }

    public void setDotted(boolean isDotted) {
        this.isDotted = isDotted;
    }

    public boolean isDotted() {
        return this.isDotted;
    }
}
