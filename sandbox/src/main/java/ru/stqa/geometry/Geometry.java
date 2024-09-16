package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;
import ru.stqa.geometry.figures.Triangle;

import static java.lang.Math.PI;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(7.); /*собственные классы Squere, собственные функции printSquareArea(7.) */
        Square.printSquareArea(1.);
        Square.printSquareArea(4.);

        Rectangle.printRectangleArea(3.0,5.0);
        Rectangle.printRectangleArea(7.0,9.0);

        Triangle.printTriangleArea(10.0, 20.0, 15.0);

    }

}
