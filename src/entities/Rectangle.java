package entities;

import exceptions.*;

public class Rectangle extends Shape {
    private int length, breadth;

    public Rectangle(int x, int y, int length, int breadth, char printingChar, String color, Canvas canvas) {
        super(x, y, printingChar, color);
        this.length = length;
        this.breadth = breadth;
    }

    @Override
    public void draw(char[][] canvas) {
        // Clear previous drawing
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                if (canvas[i][j] == this.printingChar) {
                    canvas[i][j] = ' ';
                }
            }
        }

        // Draw new rectangle
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
    public void zoomIn() {
        this.length++;
        this.breadth++;
    }

    @Override
    public void zoomOut() {
        if (this.length > 1) this.length--;
        if (this.breadth > 1) this.breadth--;
    }

    @Override
    public void moveUp() throws InvalidLocationException {
        if (this.y > 0) this.y--;
        else throw new InvalidLocationException("Cannot move further up");
    }

    @Override
    public void moveDown() throws InvalidLocationException {
        this.y++;
        if (this.y + this.breadth > Canvas.HEIGHT) {
            this.y--;
            throw new InvalidLocationException("Cannot move further down");
        }
    }

    @Override
    public void moveLeft() throws InvalidLocationException {
        if (this.x > 0) this.x--;
        else throw new InvalidLocationException("Cannot move further left");
    }

    @Override
    public void moveRight() throws InvalidLocationException {
        this.x++;
        if (this.x + this.length > Canvas.WIDTH) {
            this.x--;
            throw new InvalidLocationException("Cannot move further right");
        }
    }
}
