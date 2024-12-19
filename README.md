# Shapes Application 🧲

## Description
This Java application allows users to draw and manipulate geometric shapes on a digital canvas. It provides functionality to create triangles, rectangles, and squares as well as zoom and move these shapes within the canvas.

## Features
- Draw triangles, rectangles, and squares
- Customise shapes with different colours and printing characters
- Zoom in and out of shapes
- Move shapes around the canvas
- Compare the current canvas with a file
- Save the canvas to a file

## Installation
To use this project, clone the repository to your local machine:

```bash
git clone https://github.com/anyapages/Shapes.git
cd Shapes
```

Ensure you have Java Development Kit (JDK) installed on your system.

## Usage
Compile and run the Java files using your preferred method. Here's an example using command line:

```bash
javac ShapesApplication.java
java ShapesApplication <input_file>
```

Replace `<input_file>` with the path to your input file containing the initial canvas configuration.

## Input file format
The input file should have the following format:
- First line: `<height>,<width>,<background_character>`
- Subsequent lines: Canvas representation

Example:
```
6,10,-
----------
----------
--**###---
--*-###---
----------
----------
```

## Main menu options
1. Draw triangles
2. Draw rectangles
3. Draw squares
4. Compare the results
5. Exit

## Shape operations
- Zoom: Increase or decrease the size of a shape
- Move: Reposition a shape on the canvas (up, down, left, right)

## Comparing results
The application allows you to compare the current canvas state with a file. It will indicate whether the canvas matches the file content or not.

## Saving canvas
You can save the current canvas state to a file when exiting the application.

## Error handling
The application includes robust error handling for various scenarios, such as:
- File not found
- Invalid file content
- Invalid shape sizes or locations

## Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.