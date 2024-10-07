package ru.stqa.collections.geometry;

import ru.stqa.collections.geometry.figures.Rectangle;
import ru.stqa.collections.geometry.figures.Square;
import ru.stqa.collections.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.)); /*собственные классы Squere, собственные функции printSquareArea(7.) */
        Square.printSquareArea(new Square(5.));
        Square.printSquareArea(new Square(3.));

        Rectangle.printRectangleArea(3.0,5.0);
        Rectangle.printRectangleArea(7.0,9.0);

        Triangle.printTriangleArea(new Triangle (10.0, 20.0, 15.0));
        Triangle.printTrianglePerimeter(new Triangle (10.0, 20.0, 15.0));

    }

}
