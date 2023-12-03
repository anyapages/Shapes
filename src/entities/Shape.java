package entities;

import exceptions.*;

/**
 * This class represents a shape.
 * A shape has a location (x, y), a printing character, and a color.
 * A shape can be drawn on a canvas.
 * A shape can be moved up, down, left, or right.
 * A shape can be zoomed in or out.
 * A shape can be queried for its area, sides, and color.
 */

public abstract class Shape {
    protected int x, y;
    protected char printingChar;
    protected String color;
    private boolean zoomedOrMoved;

    public Shape(int x, int y, char printingChar, String color) {
        this.x = x;
        this.y = y;
        this.printingChar = printingChar;
        this.color = color;
    }

    public abstract void draw(char[][] canvas);
    public abstract double getArea();
    public abstract String getSide1();
    public abstract String getSide2();
    public abstract String getColor();
    public abstract void zoomIn() throws IllegalSizeException;

    public abstract void zoomOut() throws IllegalSizeException;

    public void moveUp() throws InvalidLocationException {
        try {
            if (y - 1 < 0) {
                throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
            }
            y--;
        } catch (InvalidLocationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void moveDown() throws InvalidLocationException {
        try {
            if (y + 1 >= Canvas.HEIGHT) {
                throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
            }
            y++;
        } catch (InvalidLocationException e) {
            System.out.println(e.getMessage());
        }
    }

    public void moveLeft() throws InvalidLocationException {
        if (x - 1 < 0) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        x--;
    }

    public void moveRight() throws InvalidLocationException {
        if (x + 1 >= Canvas.WIDTH) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        x++;
    }
    public boolean isZoomedOrMoved() {
        return zoomedOrMoved;
    }

    protected void setZoomedOrMoved(boolean zoomedOrMoved) {
        this.zoomedOrMoved = zoomedOrMoved;
    }
}
