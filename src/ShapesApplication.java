import java.awt.*;
import java.io.*;
import java.util.Map;
import java.util.Scanner;
import entities.*;
import entities.Canvas;
import entities.Rectangle;
import entities.Shape;

public class ShapesApplication {

    private static final Scanner input = new Scanner(System.in);
    private Canvas canvas;
    private int currentX = 0;
    private int currentY = 0;


    public ShapesApplication(String filename) throws FileNotFoundException, InvalidFileException {
        this.canvas = FileUtility.loadFile(filename);
    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                throw new IllegalArgumentException("No file input given, we can not setup the drawing console. Terminating the program.");
            }

            String filename = args[0];
            ShapesApplication app = new ShapesApplication(filename);
            app.run();
        } catch (IOException e) {
            System.out.println("File not found. Terminating the program.");
        } catch (InvalidFileException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() throws IOException {
        showPreloadedFile();
        mainMenu();
    }
    private void mainMenu() throws IOException {
        boolean running = true;
        while (running) {
            System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----");
            System.out.println("Current drawing canvas settings:");
            System.out.println("- Width: " + canvas.getWidth());
            System.out.println("- Height: " + canvas.getHeight());
            System.out.println("- Background character: " + canvas.getBackgroundChar());
            System.out.println("\nPlease select an option.");
            System.out.println("1. Draw triangles\n2. Draw rectangles\n3. Draw squares\n4. Compare the results\n5. Exit");
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    drawTriangle();
                    break;
                case 2:
                    drawRectangle();
                    break;
                case 3:
                    drawSquare();
                    break;
                case 4:
                    compareResults();
                    break;
                case 5:
                    saveCanvas();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showPreloadedFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("tests/preloaded.txt")))  {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading preloaded.txt: " + e.getMessage());
        }
    }

    private boolean isWithinBounds(int x, int y, int width, int height) {
        // Check if the shape fits within the canvas
        return x + width <= canvas.getWidth() && y + height <= canvas.getHeight();
    }

    private void postShapeOptions() {
        System.out.println("Type Z/M for zooming/moving. Use Q for quit and go back to the main menu.");
        String option = input.next();
        switch (option.toUpperCase()) {
            case "Z":
                zoomShape();
                break;
            case "M":
                moveShape();
                break;
            case "Q":
                return;
            default:
                System.out.println("Invalid option. Returning to the main menu.");
        }
    }

    private double calculateTriangleArea(int side) {
        return (Math.sqrt(3) / 4) * Math.pow(side, 2);
    }

    private void drawTriangle() {
        System.out.println("\nFor Triangle -");
        System.out.println("Please enter the following details:");

        System.out.print("Enter side: ");
        int side = input.nextInt();

        System.out.print("Please enter the printing character for triangle: ");
        char printChar = input.next().charAt(0);

        System.out.print("Enter a color (either Red, Blue, Black): ");
        Color color = getColorFromString(input.next());

        // Check if the shape fits within the canvas bounds
        if (!isWithinBounds(currentX, currentY, side, side)) {
            System.out.println("This shape cannot fit in the current canvas location.");
            return;
        }

        Triangle triangle = new Triangle(side, printChar, color);
        canvas.addShape(triangle);

        System.out.println("Type: Triangle");
        System.out.println("Side 1: " + side);
        System.out.println("Color: " + color);
        double area = calculateTriangleArea(side);
        System.out.println("Area: " + area);

        // Adjust the position for the next shape
        currentX += side; // Update the current X position

        postShapeOptions();

        canvas.render();
    }


    private void drawRectangle() {
        System.out.println("\nFor Rectangle -");
        System.out.println("Please enter the following details:");

        System.out.print("Enter length: ");
        int length = input.nextInt();

        System.out.print("Enter breadth: ");
        int breadth = input.nextInt();

        System.out.print("Please enter the printing character for rectangle: ");
        char printChar = input.next().charAt(0);

        System.out.print("Enter a color (either Red, Blue, Black): ");
        Color color = getColorFromString(input.next());

        Rectangle rectangle = new Rectangle(length, breadth, printChar, color, currentX, currentY);
        canvas.addShape(rectangle);
        canvas.render();

        System.out.println("Type: Rectangle");
        System.out.println("Length: " + length);
        System.out.println("Breadth: " + breadth);
        System.out.println("Color: " + color);
        double area = length * breadth;
        System.out.println("Area: " + area);

        postShapeOptions();
    }

    private void drawSquare() {
        System.out.println("\nFor Square -");
        System.out.println("Please enter the following details:");

        System.out.print("Enter side length: ");
        int side = input.nextInt();

        System.out.print("Please enter the printing character for square: ");
        char printChar = input.next().charAt(0);

        System.out.print("Enter a color (either Red, Blue, Black): ");
        Color color = getColorFromString(input.next());

        Square square = new Square(side, printChar, color, currentX, currentY);
        canvas.addShape(square);
        canvas.render();

        System.out.println("Type: Square");
        System.out.println("Side: " + side);
        System.out.println("Color: " + color);
        double area = Math.pow(side, 2);
        System.out.println("Area: " + area);

        postShapeOptions();
    }

    private void moveShape() {
        selectShape();
        if (selectedShape == null) {
            return;
        }
        System.out.println("Select an option to move the shape");
        System.out.println("[1] Move up\n[2] Move down\n[3] Move left\n[4] Move right\n[5] Go back to Shapes Menu");

        int moveOption = input.nextInt();

        switch (moveOption) {
            case 1:
                canvas.moveShape(selectedShape, 0, -1); // Move up
                break;
            case 2:
                canvas.moveShape(selectedShape, 0, 1); // Move down
                break;
            case 3:
                canvas.moveShape(selectedShape, -1, 0); // Move left
                break;
            case 4:
                canvas.moveShape(selectedShape, 1, 0); // Move right
                break;
            case 5:
                // Return to Shapes Menu
                break;
            default:
                System.out.println("Invalid option. Returning to the main menu.");
        }

        canvas.render();
    }

    private static final Map<String, Color> COLOR_MAP = Map.of(
            "RED", Color.RED,
            "BLUE", Color.BLUE,
            "BLACK", Color.BLACK
    );

    private Color getColorFromString(String colorStr) {
        Color color = COLOR_MAP.get(colorStr.toUpperCase());
        if (color == null) {
            throw new IllegalArgumentException("Invalid color");
        }
        return color;
    }

    private Shape selectedShape;

    private void selectShape() {
        if (!canvas.shapes.isEmpty()) {
            selectedShape = canvas.shapes.get(canvas.shapes.size() - 1);
        } else {
            System.out.println("No shapes to select.");
            selectedShape = null;
        }
    }

    private void zoomShape() {
        selectShape();
        if (selectedShape == null) {
            return;
        }
        System.out.println("Select an option to zoom the shape");
        System.out.println("[1] Zoom in");
        System.out.println("[2] Zoom out");
        System.out.println("[3] Go back to Shapes Menu");

        int zoomOption = input.nextInt();

        switch (zoomOption) {
            case 1:
                canvas.zoomShape(selectedShape, 1.1); // Zoom in
                break;
            case 2:
                canvas.zoomShape(selectedShape, 0.9); // Zoom out
                break;
            case 3:
                // Return to Shapes Menu
                break;
            default:
                System.out.println("Invalid option. Returning to the main menu.");
        }
    }

    private void compareResults() {
        try {
            Canvas loadedCanvas = FileUtility.loadFile("tests/compare.txt");

            if (canvas.matchesLoadedFile(loadedCanvas)) {
                System.out.println("The current canvas matches the file.");
            } else {
                System.out.println("The drawing on canvas doesn't match the file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading canvas from file: " + e.getMessage());
        } catch (InvalidFileException e) {
            System.out.println("Invalid file format: " + e.getMessage());
        }
    }


    private void saveCanvas() throws IOException {
        System.out.println("Enter the file name to save the canvas.");
        String filename = input.next();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(canvas.getWidth() + "," + canvas.getHeight() + "," + canvas.getBackgroundChar());
            writer.newLine();
            // Write each shape's state
            for (Shape shape : canvas.getShapes()) {
                writer.write(shape.toString());
                writer.newLine();
            }
        }
        System.out.println("Canvas saved successfully.");
    }


    private void exitApplication() {
        System.out.println("Enter the file name to save the canvas.");
        String filename = input.next();
        System.out.println("Saving feature is not implemented yet.");
        // Implement logic to save the canvas state to the file
    }

    // Additional methods for drawing shapes, moving, zooming, etc.
}
