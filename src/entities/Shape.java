package entities;

/**
 * Shape class that represents a shape.
 */
public abstract class Shape {
    protected int x, y;
    protected char printingChar;
    protected String color;

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
}
