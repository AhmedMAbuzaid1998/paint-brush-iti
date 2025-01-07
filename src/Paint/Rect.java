package Paint;

import java.awt.*;

class Rect implements Shape, Filling {
    private boolean filled, isDotted;
    private int startX, startY, width, height;
    private Color rectColor;

    public Rect(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.rectColor = Color.BLACK;
        this.isDotted = false;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(this.rectColor);

        if (this.isDotted) {
            drawDottedRect(g2d);
        } else if (this.filled) {
            g2d.fillRect(
                    Math.min(this.startX, this.width),
                    Math.min(this.startY, this.height),
                    Math.abs(this.width - this.startX),
                    Math.abs(this.height - this.startY)
            );
        } else {
            g2d.drawRect(
                    Math.min(this.startX, this.width),
                    Math.min(this.startY, this.height),
                    Math.abs(this.width - this.startX),
                    Math.abs(this.height - this.startY)
            );
        }
    }

    private void drawDottedRect(Graphics2D g2d) {
        Stroke originalStroke = g2d.getStroke();
        float[] dashPattern = new float[]{5, 5};
        BasicStroke dashedStroke = new BasicStroke(
                1.0f,
                BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL,
                1.0f,
                dashPattern,
                0.0f
        );
        g2d.setStroke(dashedStroke);
        g2d.drawRect(
                Math.min(this.startX, this.width),
                Math.min(this.startY, this.height),
                Math.abs(this.width - this.startX),
                Math.abs(this.height - this.startY)
        );
        g2d.setStroke(originalStroke);
    }

    public int getCenterX() {
        return (this.startX + this.width) / 2;
    }

    public int getCenterY() {
        return (this.startY + this.height) / 2;
    }

    public void setRadius(int radius) {
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
        return this.rectColor;
    }

    public void setColor(Color color) {
        this.rectColor = color;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
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
