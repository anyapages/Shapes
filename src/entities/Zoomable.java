package entities;

import exceptions.IllegalSizeException;

public interface Zoomable {
    void zoomIn() throws IllegalSizeException;
    void zoomOut() throws IllegalSizeException;
}
