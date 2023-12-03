package entities;

import java.util.*;

/**
 * Canvas class that represents the drawing canvas.
 */
public class Canvas {
    public static int WIDTH;
    public static int HEIGHT;
    private int width;
    private int height;
    private char backgroundCharacter;
    private char[][] canvasArray;
    private List<Shape> shapes = new ArrayList<>();

    /**
     * Creates a canvas with the specified width and height.
     * @param width the width of the canvas
     * @param height the height of the canvas
     */

    public Canvas(int width, int height, char backgroundCharacter) {
        this.width = width;
        this.height = height;
        this.backgroundCharacter = backgroundCharacter;
        this.shapes = new ArrayList<>();
        this.canvasArray = new char[height][width];
        clearCanvas();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getBackgroundChar() {
        return Character.toString(backgroundCharacter);
    }

    public char[][] getCanvasArray() {
        char[][] canvasArray = new char[height][width];
        for (char[] row : canvasArray) {
            Arrays.fill(row, backgroundCharacter);
        }

        for (Shape shape : shapes) {
            shape.draw(canvasArray);
        }

        return canvasArray;
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }
    public void display(char[][] canvasArray) {
        for (char[] row : canvasArray) {
            System.out.println(new String(row));
        }
    }

    public void clearCanvas() {
        for (char[] row : canvasArray) {
            Arrays.fill(row, backgroundCharacter);
        }
    }
    public char[][] render() {
        clearCanvas();
        for (Shape shape : shapes) {
            shape.draw(canvasArray);
        }
        return canvasArray;
    }
}
