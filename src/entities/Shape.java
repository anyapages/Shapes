package entities;

import java.awt.*;

public abstract class Shape {
    private char printCharacter;
    private Color color;
    private int x, y;

    public Shape(char printCharacter, Color color, int x, int y) {
        this.printCharacter = printCharacter;
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public abstract void draw(char[][] canvas);

    public char getPrintCharacter() {
        return printCharacter;
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

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public abstract void zoom(double scaleFactor);
}
