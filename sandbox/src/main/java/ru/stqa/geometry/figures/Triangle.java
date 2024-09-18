package ru.stqa.geometry.figures;

import static java.lang.Math.sqrt;

public class Triangle {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void printTriangleArea() {
        var text = String.format("Площадь треугольника со сторонами %f и %f и %f = %f", a, b, c, triangleArea());
        System.out.println(text);
    }

    public Double triangleArea() {
        double p = (a + b + c) / 2;
        double sqrt1 = sqrt(p * (p - a) * (p - b) * (p - c));
        return sqrt1;
    }

    public double perimeter() {
        return a + b + c;
    }
    public void printTriangleperimeter() {
        var text = String.format("Периметр треугольника со сторонами %f и %f и %f = %f", a, b, c, perimeter());
        System.out.println(text);
    }
}
/*Функция для вычисления квадратного корня
public static double sqrt(double a)
Returns the correctly rounded positive square root of a double value. Special cases:

If the argument is NaN or less than zero, then the result is NaN.
If the argument is positive infinity, then the result is positive infinity.
If the argument is positive zero or negative zero, then the result is the same as the argument.
        Otherwise, the result is the double value closest to the true mathematical square root of the argument value.
        Parameters:
a - a value.
Returns:
the positive square root of a. If the argument is NaN or less than zero, the result is NaN.*/

/*константа пи  для использования пишем: math.PI
static double	PI
The double value that is closer than any other to pi, the ratio of the circumference of a circle to its diameter.*/

/* S = √p(p - a)(p - b)(p - c) */