package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculateArea() {
    Assertions.assertEquals(72.61843774138907, new Triangle(10.0, 20.0, 15.0).triangleArea());
    }

@Test
void canCalculatePerimeter() {
    Assertions.assertEquals(45.0, new Triangle(10.0, 20.0, 15.0).perimeter());
}
    @Test
    void cannotCreateTriangleWithNegativeSide() {
        try{
            new Triangle(-5.0,3.0, 2.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception)  {
            //ок
        }

    }
    @Test
    void cannotCreateTriangleWithUnequalSide() {
        try{
            new Triangle(10.0,4.0, 15.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception)  {
            //ок
        }

    }
    @Test
    void testEquality (){
        var t1 = new Triangle(10.0,14.0, 15.0);
        var t2 = new Triangle(10.0,14.0, 15.0);
        Assertions.assertEquals(t1,t2);
    }
    @Test
    void testEquality2 (){
        var t1 = new Triangle(10.0,14.0, 15.0);
        var t2 = new Triangle(14.0,15.0, 10.0);
        Assertions.assertEquals(t1,t2);
    }

}

