package ru.stqa.geometry.figures;

public class Rectangle {
    public static void printRectangleArea(double a, double b)
    {
    var text = String.format("Площадь прямоугольникасо сторонами %f и %f = %f",a,b,rectangleArea(a,b));
        System.out.println(text);
    }

    private static Double rectangleArea(double a, double b) {
        return a * b;
    }
    }

/*все понятно
static void printRectangleArea(double a, double b) {
    System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + rectangleArea(a,b));
}*/