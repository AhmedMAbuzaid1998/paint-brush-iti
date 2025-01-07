
package Paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.List;

class FreeHand implements Shape {
    private List<Point> points;
    private Color drawColor;

    public FreeHand(List<Point> points) {
        this.points = points;
        this.drawColor = Color.BLACK;
    }

    public FreeHand(List<Point> points, Color drawColor) {
        this.points = points;
        this.drawColor = drawColor;
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public void draw(Graphics g) {
        g.setColor(this.drawColor);

        for(int i = 1; i < this.points.size(); ++i) {
            Point p1 = (Point)this.points.get(i - 1);
            Point p2 = (Point)this.points.get(i);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

    }

    public int getCenterX() {
        return 0;
    }

    public int getCenterY() {
        return 0;
    }

    public void setRadius(int radius) {
    }

    public int getStartX() {
        return 0;
    }

    public int getStartY() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public int getHight() {
        return 0;
    }

    public void setWidth(int width) {
    }

    public void setHight(int height) {
    }

    public Color getColor() {
        return this.drawColor;
    }

    public void setColor(Color color) {
        this.drawColor = color;
    }

    public void setVertices(int[] xPoints, int[] yPoints) {
    }

    public void setDotted(boolean isDotted) {
    }

    public boolean isDotted() {
        return false;
    }
}
