public class Quad
{
    protected double[]ar;  //    ארבע צלעות של מרובע

    public Quad (double x1, double x2, double x3, double x4)
    {
        this.ar = new double[4];
        this.ar[0] = x1;
        this.ar[1] = x2;
        this.ar[2] = x3;
        this.ar[3] = x4;
    }
    public double getPerimeter()    //הפעולה מחזירה את הקף המרובע
    {
        double sum=0;
        for (double v : this.ar) sum += v;
        return sum;
    }
}

