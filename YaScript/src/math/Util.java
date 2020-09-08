package math;

public class Util {
    public static int signum(int n) {
        return n % 2 == 0 ? 1 : -1;
    }

    public static double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
