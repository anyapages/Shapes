package entities;

public class Square extends Shape {
    private int side;

    public Square(int x, int y, int side, char printingChar, String color, Canvas canvas) {
        super(x, y, printingChar, color);
        this.side = side;
    }

    @Override
    public void draw(char[][] canvas) {
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                if (canvas[i][j] == this.printingChar) {
                    canvas[i][j] = ' ';
                }
            }
        }

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
}
