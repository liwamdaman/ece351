void code(double r, double x, double y) {
    double tmpx, tmpy;
    if (r <= 0.01) {
        tmpx = 0;
        tmpy = 0.16 * y;
    } else if (r <= 0.15) {
        a = -0.15 * x;
        b = 0.28 * y;
        tmpx = a + b;
        c = 0.26 * x;
        d = 0.24 * y;
        e = c + d;
        tmpy = e + 0.44;
    }
}