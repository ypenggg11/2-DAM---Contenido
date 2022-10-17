package Tests.LambaWithFunctionalInterface;

//Lambdas works with FunctionalInterfaces, it means, Interfaces with only
//one abstract method in it.
public class Main {

    public static void main(String[] args) {
        //When you initialize the interface, you can override their method
        //with Lambdas, (parameters) -> {code to execute};
        //In the parameters, you can write whatever you want.
        //
        //(When there's only one parameter, you can not use the parenthesis)
        //(When there's only one line of code to execute, you can write it in one line)
        //(It works like an override method, but with less code)
        MyInterface myInterface = (t) -> System.out.println("Texto a printear: "+t);

        printIt(myInterface,"Hola Mundo!");
    }

    //When you use an Interface as a parameter value, it accepts too
    //classes that implements that Interface.
    private static void printIt(MyInterface myInterface,String text) {
        myInterface.printIt(text);
    }
}
