package entities;

import java.io.*;
import exceptions.*;

public class FileUtility {
    public static Canvas loadCanvasFromFile(String filename) throws IOException, InvalidFileException {
        String fileContent = readFile(filename);

        String[] lines = fileContent.split("\n");
        String[] firstLine = lines[0].split(",");
        if (firstLine.length != 3) {
            throw new InvalidFileException("Invalid file format");
        }
        int width = Integer.parseInt(firstLine[0].trim());
        int height = Integer.parseInt(firstLine[1].trim());
        char backgroundChar = firstLine[2].trim().charAt(0);
        Canvas canvas = new Canvas(width, height, backgroundChar);

        return canvas;
    }

    public static void saveCanvasToFile(Canvas canvas, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            char[][] canvasArray = canvas.render();
            for (char[] row : canvasArray) {
                writer.write(new String(row));
                writer.newLine();
            }
        }
    }

    public static String readFile(String file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
