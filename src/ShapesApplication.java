import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import entities.*;
import exceptions.*;

public class ShapesApplication {
    private static final Scanner input = new Scanner(System.in);
    private static final String FILE_NOT_FOUND_MESSAGE = "File not found. Terminating the program.";
    private static final String INVALID_FILE_CONTENT_MESSAGE =
            "Invalid file content. Rows, columns, background character not defined correctly. Terminating the program.";
    private static final String NO_FILE_INPUT_MESSAGE =
            "No file input given. We cannot set up the drawing console. Terminating the program.";
    private Canvas canvas;
    private String[] commandLineArgs;
    private List<Shape> shapes = new ArrayList<>();

    public static void main(String[] args) {
        ShapesApplication app = new ShapesApplication();
        app.run(args);
    }

    private void run(String[] args) {
        if (args.length == 0) {
            System.out.println(NO_FILE_INPUT_MESSAGE);
            return;
        }

        try {
            loadFile(args[0]);
            displayCanvasDetails();
            displayMainMenu();
            processUserInput();
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND_MESSAGE);
        } catch (InvalidFileException | InvalidLocationException | IllegalSizeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void loadFile(String filePath) throws IOException, InvalidFileException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException(FILE_NOT_FOUND_MESSAGE);
        }

        List<String> lines = Files.readAllLines(path);
        if (lines.isEmpty()) {
            throw new InvalidFileException(INVALID_FILE_CONTENT_MESSAGE);
        }

        String[] canvasSpecs = lines.get(0).split(",");
        if (canvasSpecs.length != 3) {
            throw new InvalidFileException(INVALID_FILE_CONTENT_MESSAGE);
        }

        int height = Integer.parseInt(canvasSpecs[0]);
        int width = Integer.parseInt(canvasSpecs[1]);
        char backgroundChar = canvasSpecs[2].charAt(0);

        this.canvas = new Canvas(width, height, backgroundChar);
    }

    private void displayCanvasDetails() {
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----");
        System.out.println("Current drawing canvas settings:");
        System.out.println("- Width: " + canvas.getWidth());
        System.out.println("- Height: " + canvas.getHeight());
        System.out.println("- Background character: " + canvas.getBackgroundChar());
        System.out.println();
    }

    private void displayMainMenu() {
        System.out.println("Please select an option.");
        System.out.println("1. Draw triangles");
        System.out.println("2. Draw rectangles");
        System.out.println("3. Draw squares");
        System.out.println("4. Compare the results");
        System.out.println("5. Exit");
    }

    private void processUserInput() throws InvalidLocationException, IllegalSizeException, IOException {
        boolean running = true;
        while (running) {
            try {
                int choice = input.nextInt();
                input.nextLine();

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
                        input.nextLine();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine();
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
            canvas.display(canvas.getCanvasArray());
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
            FileUtility.saveCanvasToFile(canvas, filename);
            System.out.println("Canvas saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving canvas: " + e.getMessage());
        }
        System.out.println("Goodbye");
    }

    private void handleDrawTriangle() throws InvalidLocationException, IllegalSizeException {
        System.out.print("Enter side: ");
        int side = input.nextInt();
        System.out.print("Please enter the printing character for triangle: ");
        char printingChar = input.next().charAt(0);
        System.out.print("Enter a color (either Red, Blue, Black): ");
        String color = input.next().toUpperCase();

        Triangle triangle = new Triangle(0, 0, side, printingChar, color, canvas);
        canvas.addShape(triangle);
        printShapeDetails(triangle);
        canvas.display(canvas.getCanvasArray());
        handleShapeOptions(triangle);
    }


    private void handleDrawRectangle() throws InvalidLocationException, IllegalSizeException {
        System.out.print("Enter length: ");
        int length = input.nextInt();
        System.out.print("Enter breadth: ");
        int breadth = input.nextInt();
        System.out.print("Please enter the printing character for rectangle: ");
        char printingChar = input.next().charAt(0);
        System.out.print("Enter a color (either Red, Blue, Black): ");
        String color = input.next().toUpperCase();

        Rectangle rectangle = new Rectangle(0, 0, length, breadth, printingChar, color, canvas);
        canvas.addShape(rectangle);
        printShapeDetails(rectangle);
        canvas.display(canvas.getCanvasArray());
        handleShapeOptions(rectangle);
    }

    private void handleDrawSquare() throws InvalidLocationException, IllegalSizeException {
        System.out.print("Enter side: ");
        int side = input.nextInt();
        System.out.print("Please enter the printing character for square: ");
        char printingChar = input.next().charAt(0);
        System.out.print("Enter a color (either Red, Blue, Black): ");
        String color = input.next().toUpperCase();

        Square square = new Square(0, 0, side, printingChar, color, canvas);
        canvas.addShape(square);
        printShapeDetails(square);
        canvas.display(canvas.getCanvasArray());
        handleShapeOptions(square);
    }

    private void printShapeDetails(Shape shape) {
        System.out.println("Type: " + shape.getClass().getSimpleName());
        System.out.println(shape.getSide1());
        System.out.println(shape.getSide2());
        System.out.println(shape.getColor());
        System.out.println("Area: " + shape.getArea());
    }

    private void handleShapeOptions(Shape shape) throws InvalidLocationException, IllegalSizeException {
        boolean inShapeMenu = true;
        while (inShapeMenu) {
            System.out.println("Type Z/M for zooming/moving. Use Q for quit and go back to main menu.");
            String choice = input.next().toUpperCase();

            switch (choice.toUpperCase()) {
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
        if (shape.isZoomedOrMoved()) {
            canvas.display(canvas.getCanvasArray());
        }
    }

    private void handleZoom(Shape shape) throws IllegalSizeException {
        System.out.println("Select an option to zoom the shape");
        System.out.println("[1] Zoom in");
        System.out.println("[2] Zoom out");
        System.out.println("[3] Go back to Shapes Menu");

        int choice = -1;
        try {
            choice = input.nextInt();
            input.nextLine();
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            input.nextLine();
            return;
        }
        switch (choice) {
            case 1:
                shape.zoomIn();
                break;
            case 2:
                shape.zoomOut();
                break;
            case 3:
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
        canvas.display(canvas.getCanvasArray());
    }

    private void handleMove(Shape shape) {
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
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            canvas.display(canvas.getCanvasArray());
        } catch (InvalidLocationException e) {
            System.out.println(e.getMessage());
        }
    }
}
