package ru.stqa.geometry.figures;

public class Squere {
    public static void printSquareArea(double side) {
        System.out.println(String.format("Площадь квадрата со стороной %f=%f",side, squareArea(side)));
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
