public class Treasure {
    protected Box[] boxes;
    public Treasure(){
        this.boxes = new Box[100];

    }
    public int totalGold(){
        int i,count=0;
        for (i = 0; i < this.boxes.length; i++) {
            if (this.boxes[i]!=null){
                count+=this.boxes[i].getGold();
            }
        }
        return count;
    }
    public boolean fullTreasure(){
        for (int i = 0; i < this.boxes.length; i++) {
            if (this.boxes[i]!=null){
                if (this.boxes[i].getGold()!=this.boxes[i].getVolume()) return false;
            }
        }
        return true;
    }
}
