package Paint;

import java.awt.*;

public interface Shape {
    public int getCenterX();
    public int getCenterY();
    Color getColor();
    void setRadius(int radius);
    void draw(Graphics g);
    void setColor(Color color);
    public void setVertices(int[] xPoints, int[] yPoints);
    public void setDotted(boolean isDashed);
    public boolean isDotted();
    }
