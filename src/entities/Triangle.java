package entities;

import exceptions.*;

public class Triangle extends Shape {
    private int side;
    private Canvas canvas;

    public Triangle(int x, int y, int side, char printingChar, String color, Canvas canvas) {
        super(x, y, printingChar, color);
        this.side = side;
        this.canvas = canvas;
    }

    @Override
    public void draw(char[][] canvas) {
        for (int i = this.side - 1; i >= 0 && this.y + (this.side - 1 - i) < canvas.length; i--) {
            for (int j = 0; j <= i && this.x + j < canvas[this.y + (this.side - 1 - i)].length; j++) {
                canvas[this.y + (this.side - 1 - i)][this.x + j] = this.printingChar;
            }
        }
    }

    @Override
    public double getArea() {
        return 0.5 * side * side;
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
