public class Person {
    protected String name;
    protected Mother mom;

    public Person(String name)
    {
        this.name = name;
        this.mom = null;
    }

    public String GetName() {return this.name;}

    public void setMom(Mother mom) {this.mom = mom;}

    public String ToString()
    { return "person:" + this.name + " mother:" + this.mom.GetName();}

}
