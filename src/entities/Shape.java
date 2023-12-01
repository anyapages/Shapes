package entities;

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
    public abstract void zoomIn() throws IllegalSizeException;

    public abstract void zoomOut() throws IllegalSizeException;

    public void moveUp() throws InvalidLocationException {
        if (y - 1 < 0) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        y--;
    }

    public void moveDown() throws InvalidLocationException {
        if (y + 1 >= Canvas.HEIGHT) {
            throw new InvalidLocationException("This move will move the shape out of the drawing canvas.");
        }
        y++;
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
}
