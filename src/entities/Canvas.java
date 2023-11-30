/**
 * Canvas class represents the canvas on which shapes are drawn.
 * It contains the width, height, background character and a list of shapes.
 * It also contains methods to add a shape, render the canvas, move a shape, zoom a shape, etc.
 */

/**
 * Canvas class represents the canvas on which shapes are drawn.
 * It contains the width, height, background character and a list of shapes.
 * It also contains methods to add a shape, render the canvas, move a shape, zoom a shape, etc.
 */

package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Canvas {
    private char[][] grid;
    private char backgroundChar;
    private int width, height;
    public List<Shape> shapes;

    public Canvas(int width, int height, char backgroundChar) {
        this.width = width;
        this.height = height;
        this.backgroundChar = backgroundChar;
        this.grid = new char[height][width];
        this.shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void render() {
        char[][] canvasArray = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(canvasArray[i], backgroundChar);
        }

        for (Shape shape : shapes) {
            shape.draw(canvasArray);
        }

        for (char[] row : canvasArray) {
            System.out.println(new String(row));
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char getBackgroundChar() {
        return backgroundChar;
    }

    public boolean matchesLoadedFile(Canvas loadedCanvas) {
        if (this.width != loadedCanvas.width || this.height != loadedCanvas.height) {
            return false;
        }
        if (this.shapes.size() != loadedCanvas.shapes.size()) {
            return false;
        }
        // Additional checks can be added here for more detailed comparisons
        return true;
    }

    public void moveShape(Shape shape, int dx, int dy) throws InvalidLocationException {
        shape.move(dx, dy, this.width, this.height);
    }

    public void zoomShape(Shape shape, double scaleFactor) {
        shape.zoom(scaleFactor);
    }

    public Shape[] getShapes() {
        return shapes.toArray(new Shape[0]);
    }

    // Additional methods for moving, zooming shapes, etc.
}

