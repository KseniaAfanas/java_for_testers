package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Squere;
import ru.stqa.geometry.figures.Triangle;

import static java.lang.Math.PI;

public class Geometry {
    public static void main(String[] args) {
        Squere.printSquareArea(7.); /*собственные классы Squere, собственные функции printSquareArea(7.) */
        Squere.printSquareArea(1.);
        Squere.printSquareArea(4.);

        Rectangle.printRectangleArea(3.0,5.0);
        Rectangle.printRectangleArea(7.0,9.0);

        Triangle.printTriangleArea(10.0, 20.0, 15.0);

    }

}
