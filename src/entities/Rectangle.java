package entities;

import exceptions.*;

/**
 * Rectangle class that represents a rectangle shape.
 * A rectangle has a location (x, y), a printing character, and a colour.
 */

public class Rectangle extends Shape implements Movable, Zoomable {
    private int length, breadth;
    private Canvas canvas;

    public Rectangle(int x, int y, int length, int breadth, char printingChar, String color, Canvas canvas) {
        super(x, y, printingChar, color);
        this.length = length;
        this.breadth = breadth;
        this.canvas = canvas;
    }

    /**
     * Draws the rectangle on the canvas.
     * @param canvas the canvas to draw on
     */

    @Override
    public void draw(char[][] canvas) {
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                if (canvas[i][j] == this.printingChar) {
                    canvas[i][j] = ' ';
                }
            }
        }

        for (int i = this.y; i < this.y + this.breadth && i < canvas.length; i++) {
            for (int j = this.x; j < this.x + this.length && j < canvas[i].length; j++) {
                canvas[i][j] = this.printingChar;
            }
        }
    }

    @Override
    public double getArea() {
        return length * breadth;
    }

    @Override
    public String getSide1() {
        return "Side 1: " + length;
    }

    @Override
    public String getSide2() {
        return "Side 2: " + breadth;
    }

    @Override
    public String getColor() {
        return "Color: " + color;
    }

    @Override
    public void zoomIn() throws IllegalSizeException {
        if (length + 1 > canvas.getWidth() || breadth + 1 > canvas.getHeight()) {
            throw new IllegalSizeException("Zoom in will make the shape bigger than the drawing canvas.");
        }
        length++;
        breadth++;
    }

    @Override
    public void zoomOut() throws IllegalSizeException {
        if (length - 1 < 1 || breadth - 1 < 1) {
            throw new IllegalSizeException("Zoom out will make the shape disappear.");
        }
        length--;
        breadth--;
    }

    /**
     * Moves the rectangle up by 1 unit.
     * @throws InvalidLocationException if the move will move the rectangle out of the canvas
     */

    @Override
    public void moveUp() throws InvalidLocationException {
        if (y - 1 < 0) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        y--;
    }

    @Override
    public void moveDown() throws InvalidLocationException {
        if (y + breadth >= canvas.getHeight()) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        y++;
    }

    @Override
    public void moveLeft() throws InvalidLocationException {
        if (x - 1 < 0) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        x--;
    }

    @Override
    public void moveRight() throws InvalidLocationException {
        if (x + length >= canvas.getWidth()) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        x++;
    }
}
