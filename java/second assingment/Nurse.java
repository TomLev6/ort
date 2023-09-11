public class Nurse extends Employee{
   protected String typeNurse;
   protected boolean isAcademy;

    public Nurse(String typeNurse, boolean isAcademy,String id,String name) {
        super(id,name);
        this.typeNurse = typeNurse;
        this.isAcademy = isAcademy;
    }
}
