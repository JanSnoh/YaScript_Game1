package math;

import org.junit.jupiter.api.Test;

public class MathTest {
    @Test
    void testInverse() {
        //Matrix a = new Matrix(1, 2, 1, 0);
        double[] arr ={1.0,2.0,3.0,1.0,1,1,0,0,1};
        Matrix a = new Matrix(3,arr);
        System.out.println(a.get(0, 1));
        System.out.println(a);
        Matrix b = a.inverse();
        System.out.println(b);
        System.out.println(a.mult(b));
    }
}
