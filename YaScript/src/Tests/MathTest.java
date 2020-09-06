package Tests;

import org.junit.jupiter.api.Test;
import math.*;
import org.junit.jupiter.api.Test;

public class MathTest {
    @Test
    void testInverse() {
        Matrix a = new Matrix(1, 2, 1, 0);
        System.out.println(a);
        Matrix b = a.inverse();
        System.out.println(b);
        System.out.println(a.mult(b));
    }
}
