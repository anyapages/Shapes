package entities;

public class Triangle extends Shape {
    private int side;

    public Triangle(int x, int y, int side, char printingChar, String color) {
        super(x, y, printingChar, color);
        this.side = side;
    }

    @Override
    public void draw(char[][] canvas) {
        // Draw new triangle
        for (int i = 0; i < this.side && this.y + i < canvas.length; i++) {
            for (int j = 0; j <= i && this.x + j < canvas[this.y + i].length; j++) {
                canvas[this.y + i][this.x + j] = this.printingChar;
            }
        }
    }

    @Override
    public double getArea() {
        return 0.5 * side * side;
    }

    @Override
    public void zoomIn() throws IllegalSizeException {
        if (side + 1 > Canvas.WIDTH || side + 1 > Canvas.HEIGHT) {
            throw new IllegalSizeException("Zoom in will make the shape bigger than the drawing canvas.");
        }
        side++;
    }

    @Override
    public void zoomOut() throws IllegalSizeException {
        if (side - 1 < 1) {
            throw new IllegalSizeException("Zoom out will make the shape disappear.");
        }
        side--;
    }
}
