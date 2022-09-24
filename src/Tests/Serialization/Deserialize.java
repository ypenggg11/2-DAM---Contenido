package Tests.Serialization;

import java.io.*;
public class Deserialize {

    public static void main(String [] args) {

        User user = null;

        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream("H:\\Development\\IntelliJIdea\\IdeaProjects2\\src\\Tests\\Serialization\\employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            //if(ObjectStreamClass.lookup(user.getClass()).getSerialVersionUID() != another serial version UID) {
            //  DON'T CREATE THE OBJECT, TO AVOID THE EXCEPTION
            // }

            user = (User) in.readObject();
            in.close();
            fileIn.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        long serialVersionUID = ObjectStreamClass.lookup(user.getClass()).getSerialVersionUID();
        System.out.println("serialVersionUID: "+serialVersionUID);

        System.out.println("name: " + user.name);
        System.out.println("password: " + user.password);

        user.hello();

    }
}
