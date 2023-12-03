import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import entities.*;
import exceptions.*;

/**
 * This class is the entry point of the application.
 * It loads the file, displays the canvas details, and displays the main menu.
 * It also handles the user input and calls the appropriate methods.
 */

public class ShapesApplication {
    private static final Scanner input = new Scanner(System.in);
    private static final String FILE_NOT_FOUND_MESSAGE = "File not found. Terminating the program.";
    private static final String INVALID_FILE_CONTENT_MESSAGE =
            "Invalid file content, rows, columns, background character not defined correctly. Terminating the program.";
    private static final String NO_FILE_INPUT_MESSAGE =
            "No file input given, we can not setup the drawing console. Terminating the program.";
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

        this.commandLineArgs = new String[]{filePath};
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
            displayMainMenu();
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    if (handleDrawTriangle())
                        continue;
                    break;
                case 2:
                    if (handleDrawRectangle())
                        continue;
                    break;
                case 3:
                    if (handleDrawSquare())
                        continue;
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

    /**
     * This method handles the user input for comparing the results.
     * It reads the file and compares the canvas and shapes.
     * It then prints the result.
     */

    private void compareResults() {
        try {
            String fileContent = FileUtility.readFile(commandLineArgs[0]);
            System.out.println("Comparing results...");

            boolean canvasMatches = compareCanvas(fileContent);

            if (canvasMatches) {
                System.out.println("The current canvas matches the file.");
            } else {
                System.out.println("The drawing on canvas doesn't match the file.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * This method compares the canvas on the console with the canvas in the file.
     * It returns true if they match, false otherwise.
     *
     * @param fileContent the file content
     * @return boolean
     */

    private boolean compareCanvas(String fileContent) {
        String currentCanvasState = canvasToString().trim();
        String[] currentCanvasLines = currentCanvasState.split("\\r?\\n|\\n");
        String[] fileCanvasLines = fileContent.trim().split("\\r?\\n|\\n");

        fileCanvasLines = Arrays.copyOfRange(fileCanvasLines, 1, fileCanvasLines.length);
        for (int i = 0; i < fileCanvasLines.length; i++) {
            fileCanvasLines[i] = fileCanvasLines[i].replace(",", "");
        }

        if (currentCanvasLines.length != fileCanvasLines.length) {
            System.out.println("Line count mismatch: Canvas has " + currentCanvasLines.length + " lines, file has " + fileCanvasLines.length + " lines.");
            return false;
        }

        for (int i = 0; i < currentCanvasLines.length; i++) {
            if (!currentCanvasLines[i].equals(fileCanvasLines[i])) {
                System.out.println("Mismatch at line " + (i + 1));
                System.out.println("Canvas line: [" + currentCanvasLines[i] + "]");
                System.out.println("File line: [" + fileCanvasLines[i] + "]");
                return false;
            }
        }
        return true;
    }

    /**
         * This method converts the canvas array to a string.
         * It then returns the string.
         * @return String
         */
        private String canvasToString () {
            char[][] canvasArray = canvas.getCanvasArray();
            StringBuilder sb = new StringBuilder();
            for (char[] row : canvasArray) {
                sb.append(new String(row)).append("\n");
            }
            return sb.toString();
        }

        private void exitApplication () {
            System.out.println("Enter the file name to save the canvas.");
            String filename = input.next();
            try {
                FileUtility.saveCanvasToFile(canvas, filename);
            } catch (IOException e) {
                System.out.println("Error saving canvas: " + e.getMessage());
            }
            System.out.println("Goodbye");
        }

        /**
         * This method handles the user input for drawing a triangle.
         * It asks the user to enter the side, printing character, and color.
         * It then creates a triangle object and adds it to the canvas.
         * It then prints the shape details and displays the canvas.
         * It then calls the handleShapeOptions method to handle the zoom and move options.
         * @return boolean
         * @throws InvalidLocationException
         * @throws IllegalSizeException
         */

        private boolean handleDrawTriangle () throws InvalidLocationException, IllegalSizeException {
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

            return handleShapeOptions(triangle);
        }

        private boolean handleDrawRectangle () throws InvalidLocationException, IllegalSizeException {
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

            return handleShapeOptions(rectangle);
        }

        private boolean handleDrawSquare () throws InvalidLocationException, IllegalSizeException {
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

            return handleShapeOptions(square);
        }

        private void printShapeDetails (Shape shape){
            System.out.println("Type : " + shape.getClass().getSimpleName());
            System.out.println(shape.getSide1());
            System.out.println(shape.getSide2());
            System.out.println(shape.getColor());
            System.out.println("Area: " + shape.getArea());
        }

        private boolean handleShapeOptions (Shape shape){
            while (true) {
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
                        return true;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }

        private void handleZoom (Shape shape){
            while (true) {
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
                    continue;
                }

                try {
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
                            continue;
                    }
                    canvas.display(canvas.getCanvasArray());
                } catch (IllegalSizeException e) {
                    System.out.println(e.getMessage());
                    canvas.display(canvas.getCanvasArray());
                }
            }
        }

        private void handleMove (Shape shape) throws InvalidLocationException {
            while (true) {
                System.out.println("Select an option to move the shape");
                System.out.println("[1] Move up");
                System.out.println("[2] Move down");
                System.out.println("[3] Move left");
                System.out.println("[4] Move right");
                System.out.println("[5] Go back to Shapes Menu");

                int choice = 0;
                try {
                    choice = input.nextInt();
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
                } catch (java.util.InputMismatchException e) {

                    String userInput = input.next().toUpperCase();
                    if (userInput.equals("Q")) {
                        return;
                    } else {
                        System.out.println("Invalid input. Please enter a number.");
                    }
                } catch (InvalidLocationException e) {
                    System.out.println(e.getMessage());
                }
                canvas.display(canvas.getCanvasArray());
            }
        }
    }