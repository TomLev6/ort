public class Rectan extends Quad
{
    //אין תכונות נוספות
    public Rectan (double length,double width)
    {
        super(length,width,length,width);
    }
    //הפעולה מחזירה את שטח המלבן
    public double getArea()
    {
        return this.ar[0]*this.ar[1];
    }
}
