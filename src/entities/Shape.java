/**
 * Shape.java
 *
 * Abstract class for all shapes.
 * Contains the print character, color, x and y coordinates of the shape.
 * Also contains the abstract methods draw, move and zoom.
 **/

package entities;

import java.awt.*;

public abstract class Shape {
    private char printChar;
    private Color color;
    private int x, y;

    public Shape(char printChar, Color color, int x, int y) {
        this.printChar = printChar;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    /**
     * Draw the shape on the canvas.
     * @param canvas The canvas on which the shape is to be drawn.
     */

    public abstract void draw(char[][] canvas);
    public abstract void zoom(double scaleFactor);
    public abstract double calculateArea();

    public char getPrintChar() {
        return printChar;
    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Move the shape by dx units in x direction and dy units in y direction.
     * @param dx The number of units to move in x direction.
     * @param dy The number of units to move in y direction.
     */

    public void move(int dx, int dy, int canvasWidth, int canvasHeight) throws InvalidLocationException {
        int newX = this.x + dx;
        int newY = this.y + dy;

        // Check if the new position is within the canvas bounds
        if (newX < 0 || newY < 0 || newX >= canvasWidth || newY >= canvasHeight) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }

        this.x = newX;
        this.y = newY;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "," + getPrintChar() + "," + getColor().toString() + "," + getX() + "," + getY();
    }
}
