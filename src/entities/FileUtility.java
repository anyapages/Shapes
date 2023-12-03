package entities;

import java.io.*;

/**
 * FileUtility class that contains methods for saving and reading files.
 */
public class FileUtility {

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
