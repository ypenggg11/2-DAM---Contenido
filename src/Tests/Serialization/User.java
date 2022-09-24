package Tests.Serialization;

import java.io.Serial;
import java.io.Serializable;

public class User implements Serializable {

    //SerialVersionUID =	serialVersionUID is a unique ID,
    //					that verifies the sender and receiver of a serialized object.
    //					Ensures object will be compatible,
    //					Number must match, otherwise this will cause a InvalidClassException
    //					A SerialVersionUID will be calculated based on class properties, members, etc.
    //					A serializable class can declare its own serialVersionUID explicitly (recommended)

    //Explicit serialVersionUID declaration
    @Serial
    private static final long serialVersionUID = 123;

    String name;
    transient String password; //transient values are ignored when serializes (don't write it in the file)

    public void hello() {
        System.out.println("Welcome "+name);
    }
}
