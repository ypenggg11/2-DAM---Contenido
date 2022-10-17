package Tests.LambaWithFunctionalInterface;

//Defines the type of the interface, in this case, it will need
//an abstract method or there will display an error.
@FunctionalInterface
public interface MyInterface {

    void printIt(String text);
}
