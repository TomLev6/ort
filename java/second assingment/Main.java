// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {



    }
    public int similarname(Mother[] mothers, String kidName) {
        int i,count=0;
        for (i=0;i<mothers.length;i++){
            if (mothers[i]!=null){
                if(mothers[i].hasKid(kidName)) count++;
            }
        }
        return count;
    }
}