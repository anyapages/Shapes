package entities;

import exceptions.*;

/**
 * Rectangle class that represents a rectangle shape.
 * A rectangle has a location (x, y), a printing character, and a color.
 */

public class Rectangle extends Shape {
    private int length, breadth;
    private int side;
    private Canvas canvas;

    public Rectangle(int x, int y, int length, int breadth, char printingChar, String color, Canvas canvas) {
        super(x, y, printingChar, color);
        this.length = length;
        this.breadth = breadth;
        this.canvas = canvas;
        this.side = length;
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
        if (side + 1 > canvas.getWidth() || side + 1 > canvas.getHeight()) {
            throw new IllegalSizeException("Zoom in will make the shape bigger than the drawing canvas.");
        }
        side++;
        setZoomedOrMoved(true);
    }

    @Override
    public void zoomOut() throws IllegalSizeException {
        if (side - 1 < 1) {
            throw new IllegalSizeException("Zoom out will make the shape disappear.");
        }
        side--;
        setZoomedOrMoved(true);
    }

    @Override
    public void moveUp() throws InvalidLocationException {
        if (y - 1 < 0) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        y--;
    }

    @Override
    public void moveDown() throws InvalidLocationException {
        if (y + side >= canvas.getHeight()) {
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
        if (x + side >= canvas.getWidth()) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        x++;
    }
}
