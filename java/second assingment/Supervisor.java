public class Supervisor extends Doctor{
    protected Employee[] employees;
    protected static int currentCrews;

    public Supervisor(String profession, String state,
                      int maxCrews,String id,String name) {
        super(profession, state,id,name);
        this.employees = new Employee[maxCrews];

    }


}
