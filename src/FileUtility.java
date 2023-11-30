import entities.Canvas;
import entities.Shape;
import entities.Triangle;
import entities.Rectangle;
import entities.Square;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileUtility {

    public static Canvas loadFile(String filename) throws FileNotFoundException, InvalidFileException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);

        if (!scanner.hasNextLine()) {
            throw new InvalidFileException("Invalid file content");
        }

        String[] firstLine = scanner.nextLine().split(",");
        if (firstLine.length != 3) {
            throw new InvalidFileException("Invalid file content, rows, columns, background character not defined correctly. Terminating the program.");
        }

        int rows = Integer.parseInt(firstLine[0].trim());
        int columns = Integer.parseInt(firstLine[1].trim());
        char backgroundChar = firstLine[2].trim().charAt(0);

        Canvas canvas = new Canvas(columns, rows, backgroundChar);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                String shapeType = parts[0];
                char printChar = parts[2].trim().charAt(0);
                Color color = getColorFromString(parts[3].trim());

                switch (shapeType) {
                    case "Triangle":
                        int side = Integer.parseInt(parts[1].trim());
                        Triangle triangle = new Triangle(side, printChar, color);
                        canvas.addShape(triangle);
                        break;
                    case "Rectangle":
                        String[] dimensions = parts[1].split("x");
                        int length = Integer.parseInt(dimensions[0].trim());
                        int breadth = Integer.parseInt(dimensions[1].trim());

                        Rectangle rectangle = new Rectangle(length, breadth, printChar, color, 0, 0);
                        canvas.addShape(rectangle);
                        break;
                    case "Square":
                        int squareSide = Integer.parseInt(parts[1].trim());

                        Square square = new Square(squareSide, printChar, color, 0, 0);
                        canvas.addShape(square);
                        break;
                }
            }
        }

        scanner.close();
        return canvas;
    }

    private static Color getColorFromString(String colorStr) {
        switch (colorStr.toUpperCase()) {
            case "RED":
                return Color.RED;
            case "BLUE":
                return Color.BLUE;
            case "BLACK":
                return Color.BLACK;
            default:
                throw new IllegalArgumentException("Invalid color");
        }
    }

    public static void saveCanvasToFile(Canvas canvas, String filename) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(canvas.getHeight() + "," + canvas.getWidth() + "," + canvas.getBackgroundChar());
            for (Shape shape : canvas.getShapes()) {
                writer.println(shape.toString());
            }
        }
    }

}
