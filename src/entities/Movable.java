package entities;

import exceptions.InvalidLocationException;

public interface Movable {
    void moveUp() throws InvalidLocationException;
    void moveDown() throws InvalidLocationException;
    void moveLeft() throws InvalidLocationException;
    void moveRight() throws InvalidLocationException;
}
