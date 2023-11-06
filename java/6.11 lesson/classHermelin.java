import java.util.Arrays;
import java.util.Objects;

public class classHermelin {
    double [] grades;
    String teacherName;

    public classHermelin(int studentAmount, String teacherName) {
        this.grades = new double[studentAmount];
        this.teacherName = teacherName;
    }

    public void setGrades(double[] grades)
     throws Exception
    {
        if(grades.length > this.grades.length) throw new Exception("More grades then space in the array!");
        int i=0;
        for (double grade: grades) {
            if (i < this.grades.length) {
                if ((101 < grade) || (grade < 0)) {
                    throw new Exception("Not a valid Grade!");
                }
                this.grades[i] = grade;
                i++;
            }
        }

    }
    public double getGradeByIndex(int index) throws Exception
    {
        if ((index>this.grades.length)||(index<0)) throw new Exception("Index out of bound!");
        if(this.grades[index]==0){throw new Exception("No grade initialized!");}
        return this.grades[index];
    }

    @Override
    public String toString() {
        return "classHermelin{" +
                "grades=" + Arrays.toString(grades) +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        classHermelin that = (classHermelin) o;
        return Arrays.equals(grades, that.grades) && Objects.equals(teacherName, that.teacherName);
    }


}
