public class Mother extends Person{

    protected Person[] kids;
    public Mother(String name) {
        super(name);
        this.kids = new Person[12];
    }
    public boolean hasKid(String KidName)
    {
        int i;
        for (i=0;i<kids.length;i++)
        {
            if (kids[i]!=null)
            {
                if (kids[i].name.equals(KidName)) return true;
            }
        }
        return false;
    }
    public void newBorn(Person baby){
        if (hasKid(baby.GetName())) {
            System.out.print("there is already a kid with this name!");
        }
        else
        {
            int i=0;
            while ((kids[i].GetName()!=null)&&(i<kids.length))
            {
                i++;
            }
            kids[i] = baby;
            baby.setMom(mom);
        }
    }

}
