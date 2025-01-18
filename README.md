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

## Quick start
```bash
# Clone repository
git clone https://github.com/anyapages/Shapes.git
cd Shapes

# Compile and run
javac ShapesApplication.java
java ShapesApplication <input_file>
```

## Input format
```
6,10,-
----------
----------
--**###---
--*-###---
----------
----------
```

## Usage
1. **Draw Shapes**: Create triangles, rectangles, or squares
2. **Transform**: Zoom in/out or move shapes
3. **Save/Load**: Preserve your work between sessions
4. **Compare**: Verify canvas against reference files

## Error handling
- Invalid file content
- Out-of-bounds movements
- Illegal shape sizes
- File I/O exceptions

## License
MIT