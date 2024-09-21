package ru.stqa.geometry.figures;

import java.util.Objects;

import static java.lang.Math.sqrt;

/*public class Triangle {
    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }*/

      public record Triangle(double a, double b, double c)
      {
        public Triangle {
            if (a < 0 || b < 0 ||c < 0){
                throw new IllegalArgumentException("Triangle side should be non-negative");
            }
            if ((a+b < c) || (b+c < a) ||(a+c < b)){
                throw new IllegalArgumentException("The sum of two of any two must be at least three times");
            }
        }

          public static void printTriangleArea(Triangle t) {
              System.out.println(String.format("Площадь треугольника со сторонами %f и %f и %f = %f", t.a, t.b, t.c, t.triangleArea()));
               }

          public double triangleArea() {
            double p = (this.a + this.b + this.c) / 2;
            double sqrt1 = sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
            return sqrt1;
        }

          public double perimeter() {
            return this.a + this.b + this.c;
        }

          public static void printTrianglePerimeter(Triangle t) {
              System.out.println(String.format("Периметр треугольника со сторонами %f и %f и %f = %f", t.a, t.b, t.c, t.perimeter()));
                  }

          @Override
          public boolean equals(Object o) {
              if (this == o) return true;
              if (o == null || getClass() != o.getClass()) return false;
              Triangle triangle = (Triangle) o;
              return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                      ||(Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.b) == 0)
                      ||(Double.compare(this.b, triangle.c) == 0 && Double.compare(this.a, triangle.c) == 0 && Double.compare(this.c, triangle.a) == 0)
                      ||(Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.a, triangle.c) == 0)
                      ||(Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0)
                      ||(Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.a) == 0)
                      ||(Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0)
                      ||(Double.compare(this.a, triangle.c) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.a) == 0)
                      ||(Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.c) == 0);
          }

          @Override
          public int hashCode() {
              return 1;
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
