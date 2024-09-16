package ru.stqa.geometry.figures;

public class Square {
    public static void printSquareArea(double side) {
        System.out.println(String.format("Площадь квадрата со стороной %f=%f",side, area(side)));
    }

    public static double area(double a) {
        return a * a;
    }

    public static double perimeter(double a) {
        return 4*a;
    }
}
