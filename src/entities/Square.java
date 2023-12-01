package entities;

public class Square extends Shape {
    private int side;

    public Square(int x, int y, int side, char printingChar, String color) {
        super(x, y, printingChar, color);
        this.side = side;
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

        // Draw new square
        for (int i = this.y; i < this.y + this.side && i < canvas.length; i++) {
            for (int j = this.x; j < this.x + this.side && j < canvas[i].length; j++) {
                canvas[i][j] = this.printingChar;
            }
        }
    }

    @Override
    public void zoomIn() {
        this.side++;
    }

    @Override
    public void zoomOut() {
        if (this.side > 1) this.side--;
    }

    @Override
    public double getArea() {
        return side * side;
    }

}
