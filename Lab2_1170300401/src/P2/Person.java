package P2;

public class Person {
    //TODO fields
    private String name;
    //Representation invariant:
    //TODO name!=null
    //Abstraction function:
    //TODO AF(name)==a person
    //Safety from rep exposure:
    //TODO All fields are private
    public String getName() {
        return name;
    }
    
    //TODO constructor
    public Person(String name) {
        this.name = name;
        checkRep();
    }
    //TODO checkRep
    private void checkRep() {
        assert name!=null;
    }
    //TODO toString()
    @Override public String toString() {
        return getName();
    }
}
