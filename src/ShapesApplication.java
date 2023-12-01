import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.*;

public class ShapesApplication {
    private Scanner input;
    private Canvas canvas;
    private String[] commandLineArgs;

    private List<Shape> shapes = new ArrayList<>();

    public ShapesApplication() {
        this.input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        ShapesApplication app = new ShapesApplication();
        app.run(args);
    }

    public void run(String[] args) {
        this.commandLineArgs = args;
        try {
            if (args.length == 0) {
                throw new IllegalArgumentException("No file input given, we cannot setup the drawing console. Terminating the program.");
            }

            String filename = args[0];
            canvas = FileUtility.loadCanvasFromFile(filename);
            showMainMenu();
            processUserInput();
        } catch (IllegalArgumentException | IOException | InvalidFileException | InvalidLocationException |
                 IllegalSizeException e) {
            System.out.println(e.getMessage());
        } finally {
            input.close();
        }
    }

    private void showMainMenu() {
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----");
        System.out.println("Current drawing canvas settings:");
        System.out.println("- Width: " + canvas.getWidth());
        System.out.println("- Height: " + canvas.getHeight());
        System.out.println("- Background character: " + canvas.getBackgroundChar());
        System.out.println("\nPlease select an option.");
        System.out.println("1. Draw triangles");
        System.out.println("2. Draw rectangles");
        System.out.println("3. Draw squares");
        System.out.println("4. Compare the results");
        System.out.println("5. Exit");
    }

    private void processUserInput() throws InvalidLocationException, IllegalSizeException {
        boolean running = true;
        while (running) {
            int choice = input.nextInt();
            switch (choice) {
                case 1:
                    handleDrawTriangle();
                    break;
                case 2:
                    handleDrawRectangle();
                    break;
                case 3:
                    handleDrawSquare();
                    break;
                case 4:
                    compareResults();
                    break;
                case 5:
                    exitApplication();
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void compareResults() {
        try {
            String currentCanvasState = canvasToString();
            String fileContent = FileUtility.readFile(commandLineArgs[0]);

            if (currentCanvasState.equals(fileContent)) {
                System.out.println("The current canvas matches the file.");
            } else {
                System.out.println("The drawing on canvas doesn't match the file.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    private String canvasToString() {
        char[][] canvasArray = canvas.getCanvasArray();
        StringBuilder sb = new StringBuilder();
        for (char[] row : canvasArray) {
            sb.append(new String(row)).append("\n");
        }
        return sb.toString();
    }

    private void exitApplication() {
        System.out.println("Enter the file name to save the canvas.");
        String filename = input.next();
        try {
            FileUtility.saveCanvasToFile(filename, canvasToString());
            System.out.println("Canvas saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving canvas: " + e.getMessage());
        }
        System.out.println("Goodbye");
    }

    private void handleDrawTriangle() throws InvalidLocationException, IllegalSizeException {
        System.out.println("Enter side length:");
        int side = input.nextInt();
        System.out.println("Enter printing character for triangle:");
        char printingChar = input.next().charAt(0);
        System.out.println("Enter a color (either Red, Blue, Black):");
        String color = input.next().toUpperCase();

        Triangle triangle = new Triangle(0, 0, side, printingChar, color);
        triangle.draw(canvas.getCanvasArray());
        canvas.display();
        handleShapeOptions(triangle);

        shapes.add(triangle);
    }

    private void handleDrawRectangle() throws InvalidLocationException, IllegalSizeException {
        System.out.println("Enter length:");
        int length = input.nextInt();
        System.out.println("Enter breadth:");
        int breadth = input.nextInt();
        System.out.println("Please enter the printing character for rectangle:");
        char printingChar = input.next().charAt(0);
        System.out.println("Enter a color (either Red, Blue, Black):");
        String color = input.next().toUpperCase();

        Rectangle rectangle = new Rectangle(0, 0, length, breadth, printingChar, color);
        rectangle.draw(canvas.getCanvasArray());
        canvas.display();
        handleShapeOptions(rectangle);

        shapes.add(rectangle);
    }

    private void handleDrawSquare() throws InvalidLocationException, IllegalSizeException {
        System.out.println("Enter side length:");
        int side = input.nextInt();
        System.out.println("Please enter the printing character for square:");
        char printingChar = input.next().charAt(0);
        System.out.println("Enter a color (either Red, Blue, Black):");
        String color = input.next().toUpperCase();

        Square square = new Square(0, 0, side, printingChar, color);
        square.draw(canvas.getCanvasArray());
        canvas.display();
        handleShapeOptions(square);

        shapes.add(square);
    }

    private void handleShapeOptions(Shape shape) throws InvalidLocationException, IllegalSizeException {
        boolean inShapeMenu = true;
        while (inShapeMenu) {
            System.out.println("Type Z/M for zooming/moving. Use Q for quit and go back to main menu.");
            String choice = input.next().toUpperCase();
            switch (choice) {
                case "Z":
                    handleZoom(shape);
                    break;
                case "M":
                    handleMove(shape);
                    break;
                case "Q":
                    inShapeMenu = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void handleZoom(Shape shape) throws IllegalSizeException {
        System.out.println("Select an option to zoom the shape");
        System.out.println("[1] Zoom in");
        System.out.println("[2] Zoom out");
        System.out.println("[3] Go back to Shapes Menu");

        int choice = input.nextInt();
        switch (choice) {
            case 1:
                shape.zoomIn();
                break;
            case 2:
                shape.zoomOut();
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        shape.draw(canvas.getCanvasArray());
        canvas.display();
    }

    private void handleMove(Shape shape) {
        while (true) {
            try {
                System.out.println("Select an option to move the shape");
                System.out.println("[1] Move up");
                System.out.println("[2] Move down");
                System.out.println("[3] Move left");
                System.out.println("[4] Move right");
                System.out.println("[5] Go back to Shapes Menu");

                int choice = input.nextInt();
                switch (choice) {
                    case 1:
                        shape.moveUp();
                        break;
                    case 2:
                        shape.moveDown();
                        break;
                    case 3:
                        shape.moveLeft();
                        break;
                    case 4:
                        shape.moveRight();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                shape.draw(canvas.getCanvasArray());
                canvas.display();
                break;
            } catch (InvalidLocationException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
