import java.io.*;
import java.lang.Math;

public class Main {
    public static void main(String[] args) throws Exception {
        /*
        classHermelin c = new classHermelin(20, "Tom");
        double [] grades = new double[20];
        for (int i=0;i<grades.length;i++){grades[i]=Math.random()*(100+1)+0;}
        c.setGrades(grades);
        System.out.println(c);
        System.out.println(c.getGradeByIndex(26));
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileReader r;
        FileWriter w;
        try {
            System.out.print("Source file name: ");
            String inFileName = br.readLine();
            r = new FileReader(inFileName);
            

            System.out.print("Destination file name: ");
            String outFileName = br.readLine();
            w = new FileWriter(outFileName);

            int c;
            while ((c = r.read()) != -1)
                w.write(c);

            w.flush();
        }
        catch (FileNotFoundException e){System.out.print(e.getMessage());}
        //catch (IOException io) {System.out.print(io.getMessage());}
        finally {

        }

    }
}