package entities;

import java.awt.Color;

public class Square extends Rectangle {
    public Square(int side, char printCharacter, Color color, int x, int y) {
        super(side, side, printCharacter, color, x, y);
    }
}
