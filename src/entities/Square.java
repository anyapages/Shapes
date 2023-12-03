package entities;

import exceptions.*;

/**
 * Square class that represents a square shape.
 * A square has a location (x, y), a printing character, and a color.
 */

public class Square extends Shape {
    private int side;
    private Canvas canvas;

    public Square(int x, int y, int side, char printingChar, String color, Canvas canvas) {
        super(x, y, printingChar, color);
        this.side = side;
        this.canvas = canvas;
    }

    @Override
    public void draw(char[][] canvas) {
        for (int i = this.y; i < this.y + this.side && i < canvas.length; i++) {
            for (int j = this.x; j < this.x + this.side && j < canvas[i].length; j++) {
                canvas[i][j] = this.printingChar;
            }
        }
    }

    /**
     * Gets the area of the square.
     *
     * @return the area of the square
     */

    @Override
    public double getArea() {
        return side * side;
    }

    @Override
    public String getSide1() {
        return "Side 1: " + side;
    }

    @Override
    public String getSide2() {
        return "Side 2: " + side;
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
        setZoomedOrMoved(true);
    }

    @Override
    public void moveDown() throws InvalidLocationException {
        if (y + side >= canvas.getHeight()) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        y++;
        setZoomedOrMoved(true);
    }

    @Override
    public void moveLeft() throws InvalidLocationException {
        if (x - 1 < 0) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        x--;
        setZoomedOrMoved(true);
    }

    @Override
    public void moveRight() throws InvalidLocationException {
        if (x + side >= canvas.getWidth()) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        x++;
        setZoomedOrMoved(true);
    }
}
