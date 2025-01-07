package Paint;

import java.awt.*;

class Line implements Shape {
    private int startX, startY, endX, endY;
    private Color lineColor;
    private boolean isDotted;

    public Line(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.lineColor = Color.BLACK;
        this.isDotted = false;
    }

    private void drawDottedLine(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.lineColor);

        float[] dashPattern = {5, 5}; // Dash length and space length
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, dashPattern, 0));

        g2d.drawLine(this.startX, this.startY, this.endX, this.endY);

        g2d.setStroke(new BasicStroke());
    }

    public void draw(Graphics g) {
        if (this.isDotted) {
            this.drawDottedLine(g);
        } else {
            g.setColor(this.lineColor);
            g.drawLine(this.startX, this.startY, this.endX, this.endY);
        }
    }

    public int getCenterX() {
        return (this.startX + this.endX) / 2;
    }

    public int getCenterY() {
        return (this.startY + this.endY) / 2;
    }

    public void setRadius(int radius) {
    }

    public int getStartX() {
        return this.startX;
    }

    public int getStartY() {
        return this.startY;
    }

    public int getEndX() {
        return this.endX;
    }

    public int getEndY() {
        return this.endY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public Color getColor() {
        return this.lineColor;
    }

    public void setColor(Color color) {
        this.lineColor = color;
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
