public class Box extends Rectan
{
    protected double hight;  // גובה התיבה
    protected int gold;  //      מספר מטילי הזהב
    public Box(double length,double width,double hight)
    {
        super(length,width);
        this.hight =hight;
        this.gold=0 ;
    }
    //פעולה המחזירה את נפח התיבה
    public double getVolume()
    {
        return super.getArea() * this.hight;
    }
    //פעולה המחשבת את מספר מטילי הזהב שהתיבה יכולה להכיל
    private int maxGold()
    {
        return ((int)this.getVolume()/10);
    }
    //פעולה המוסיפה מטילי זהב  לתיבה
    public int setGold (int amount)
    {
        if(amount>maxGold()) {
            this.gold = maxGold();
            return amount - maxGold();
        }
        this.gold = maxGold()-amount;
        return 0;
    }
    public int getGold()
    {
        return this.gold;
    }
}
