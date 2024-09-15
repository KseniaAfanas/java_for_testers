public class Geometry {
    public static void main(String[] args) {
        printSquareArea(7.);
        printSquareArea(1.);
        printSquareArea(4.);

        printRectangleArea(3.0,5.0);
        printRectangleArea(7.0,9.0);

    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Площадь прямоугольникасо сторонами " + a + " и " + b + " = " + rectangleArea(a,b));
    }

    private static Double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + squareArea(side));
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
