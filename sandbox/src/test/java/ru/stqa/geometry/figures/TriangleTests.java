package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {


@Test
void canCalculateArea() {
    Triangle triangle = new Triangle(10.0, 20.0, 15.0);
    double result = triangle.triangleArea();
    Assertions.assertEquals(72.61843774138907, result);
}
@Test
void canCalculatePerimeter() {
    Triangle triangle = new Triangle(10.0, 20.0, 15.0);
    Assertions.assertEquals(45.0, triangle.perimeter());
}
}
