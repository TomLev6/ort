public class Doctor extends Employee{
    protected String profession;
    protected String state;

    public Doctor(){

    }
    public Doctor(String profession, String state,String id,String name) {
        super(id,name);
        this.profession = profession;
        this.state = state;
    }
}
