package First;

public class Triangle {
    private short a;
    private short b;
    private short c;

    Triangle(short firstSide, short secondSide, short thirdSide) throws IllegalArgumentException {
        if (firstSide <= 0 || secondSide <= 0 || thirdSide <= 0) {
            throw new IllegalArgumentException
                    ("Sides must be positive!");
        }
        if (firstSide >= secondSide + thirdSide || secondSide >= firstSide + thirdSide
                || thirdSide >= secondSide + firstSide) {
            throw new IllegalArgumentException
                    ("Sides must satisfy triangle inequality");
        }
        a = firstSide;
        b = secondSide;
        c = thirdSide;
    }

    public int getPerimeter() {
        return a + b + c;
    }

    public double getArea() {
        double p = getPerimeter() / 2f;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public TypeOfTriangle getTypeOfTriangle() {
        boolean aEb = a == b;
        boolean aEc = a == c;
        boolean bEc = b == c;

        if (aEb && aEc) {
            return TypeOfTriangle.EQUILATERAL;
        }
        if (aEb || aEc || bEc) {
            return TypeOfTriangle.ISOSCELES;
        }
        return TypeOfTriangle.VERSATILE;
    }

    public short getA() {
        return a;
    }

    public short getB() {
        return b;
    }

    public short getC() {
        return c;
    }

    @Override
    public String toString() {
        return getPerimeter() + "," + getArea()
                + "," + getTypeOfTriangle().toString();
    }

    enum TypeOfTriangle {
        EQUILATERAL,
        ISOSCELES,
        VERSATILE
    }
}
