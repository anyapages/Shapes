package entities;

import java.awt.*;

public class Triangle extends Shape {
    private int sideLength;

    public Triangle(int sideLength, char printChar, Color color, int x, int y) {
        super(printChar, color, x, y);
        this.sideLength = sideLength;
    }

    @Override
    public void draw(char[][] canvas) {
        int startX = getX();
        int startY = getY();

        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j <= i; j++) {
                int x = startX + j;
                int y = startY + i;

                if (y < canvas.length && x < canvas[y].length) {
                    canvas[y][x] = getPrintChar();
                }
                if (startX < 0 || startY < 0 || startX >= canvas[0].length || startY >= canvas.length) {
                    return; // Out of bounds
                }
            }
        }
    }

    @Override
    public void zoom(double scaleFactor) {
        this.sideLength = (int) (this.sideLength * scaleFactor);
    }

    @Override
    public double calculateArea() {
        return 0.5 * sideLength * sideLength;
    }
}
