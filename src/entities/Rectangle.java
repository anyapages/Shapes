package entities;

import java.awt.Color;

public class Rectangle extends Shape {
    private int length;
    private int breadth;

    public Rectangle(int length, int breadth, char printChar, Color color, int x, int y) {
        super(printChar, color, x, y);
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public void draw(char[][] canvas) {
        for (int i = getY(); i < getY() + breadth; i++) {
            for (int j = getX(); j < getX() + length; j++) {
                if (i >= 0 && i < canvas.length && j >= 0 && j < canvas[i].length) {
                    canvas[i][j] = getPrintChar();
                }
            }
        }
    }

    @Override
    public void zoom(double scaleFactor) {
        this.length = (int) (this.length * scaleFactor);
        this.breadth = (int) (this.breadth * scaleFactor);
    }

    @Override
    public double calculateArea() {
        return 0;
    }
}
