package ru.stqa.geometry.figures;

public record Square (double side) {

    public static void printSquareArea(Square s) {
        System.out.println(String.format("Площадь квадрата со стороной %f=%f",s.side, s.area()));
    }

       public double area() {
        return this.side*this.side;
    }

    public double perimeter() {
        return 4*this.side;
    }
}
