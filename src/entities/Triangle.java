package entities;

import java.awt.*;

public class Triangle extends Shape {
    private int sideLength;

    public Triangle(int sideLength, char printCharacter, Color color) {
        super(printCharacter, color, 0, 0);
        this.sideLength = sideLength;
    }

    @Override
    public void draw(char[][] canvas) {
        int canvasHeight = canvas.length;

        // The triangle is drawn from the top-left corner
        for (int i = 0; i < sideLength; i++) {
            for (int j = 0; j <= i; j++) {
                // Check if the point is within the canvas bounds
                if (i < canvasHeight && j < canvas[i].length) {
                    canvas[i][j] = getPrintCharacter();
                }
            }
        }
    }

    @Override
    public void zoom(double scaleFactor) {
        this.sideLength = (int) (this.sideLength * scaleFactor);
    }
}
