package entities;

import java.awt.Color;

public class Square extends Rectangle {
    public Square(int side, char printChar, Color color, int x, int y) {
        super(side, side, printChar, color, x, y);
    }
}
