# Shapes Application 🎨

## Overview
A Java-based digital canvas application for drawing and manipulating geometric shapes. Create, move and transform shapes in a text-based interface while maintaining precise control over their properties and positions.

## Features
- 🔷 Draw geometric shapes (triangles, rectangles, squares)
- 🎨 Customise with colours (RED, BLUE, BLACK) and characters
- 🔍 Zoom shapes in and out
- 🔄 Move shapes around the canvas
- 💾 Save and load canvas states
- ✅ Compare canvas with reference files

## Project Structure
```
src/
├── ShapesApplication.java     # Main application entry point
├── entities/                  # Core shape classes
│   ├── Canvas.java           # Canvas management
│   ├── Shape.java            # Abstract base shape
│   ├── Rectangle.java        # Rectangle implementation
│   ├── Square.java           # Square implementation
│   ├── Triangle.java         # Triangle implementation
│   ├── Color.java            # Color enumeration
│   ├── FileUtility.java      # File I/O operations
│   ├── Movable.java          # Movement interface
│   └── Zoomable.java         # Zoom interface
├── exceptions/               # Custom exceptions
│   ├── IllegalSizeException.java
│   ├── InvalidFileException.java
│   ├── InvalidLocationException.java
│   └── InvalidZoomException.java
└── tests/                    # Test files
    ├── compare.txt
    ├── invalidFile.txt
    └── preloaded.txt
```

## Quick Start
```bash
# Clone repository
git clone https://github.com/anyapages/Shapes.git
cd Shapes

# Compile the application
javac -d out src/**/*.java

# Run with a test file
java -cp out ShapesApplication src/tests/preloaded.txt
```

## Input Format
Canvas files should follow this structure:
```
width,height,background_character
canvas_row_1
canvas_row_2
...
canvas_row_height
```

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

## Available Commands
- **Draw shapes**: Create triangles, rectangles, or squares at specified positions
- **Move shapes**: Relocate existing shapes to new coordinates
- **Zoom shapes**: Scale shapes up or down while maintaining proportions
- **Save canvas**: Export current canvas state to file
- **Load canvas**: Import canvas from file
- **Compare**: Verify canvas matches reference file

## Error Handling
The application handles various error conditions:
- **InvalidFileException**: Malformed or corrupted input files
- **InvalidLocationException**: Attempts to place shapes outside canvas bounds
- **IllegalSizeException**: Invalid shape dimensions
- **InvalidZoomException**: Illegal zoom operations

## Development
Built with Java following object-oriented principles:
- **Inheritance**: Shape hierarchy with specialized implementations
- **Interfaces**: Movable and Zoomable for behavior contracts
- **Exception handling**: Custom exceptions for robust error management
- **File I/O**: Utility classes for canvas persistence

## License
MIT License - see LICENSE file for details