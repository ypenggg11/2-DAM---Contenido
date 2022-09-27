package AED.Actividad2Serializacion;

import java.io.*;
import java.text.DecimalFormat;

//Apartado 2
public class Main2 {
    private static String path = "./src/AED/Actividad2Serializacion/Persona.DAT";
    private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public static void main(String[] args) {

        leeEmpleados();

        leerFicheroDAT();
    }

    private static void leerFicheroDAT() {
        DataInputStream dataInputStream;

        try {
            dataInputStream = new DataInputStream(new FileInputStream(path));

            try {
                while (true) {
                    String nombre = dataInputStream.readUTF();
                    String DNI = dataInputStream.readUTF();
                    Integer edad = dataInputStream.readInt();
                    Double salario = dataInputStream.readDouble();


                    if (edad>=18) {
                        System.out.println("||----------||Persona||----------||\n" +
                                "Nombre: " +nombre + '\n' +
                                "DNI: " + DNI + '\n' +
                                "Edad: " + edad +'\n'+
                                "Salario: " + decimalFormat.format(salario) +
                                "€\n");
                    }
                }
            }catch (EOFException e) {}

            dataInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void leeEmpleados() {

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        DataOutputStream dataOutputStream;

        try {
            fileInputStream = new FileInputStream("./src/AED/Actividad2Serializacion/Persona.obj");
            objectInputStream = new ObjectInputStream(fileInputStream);

            dataOutputStream = new DataOutputStream(new FileOutputStream(path));

            try {
                while (true) {
                    escribeDato((Persona) objectInputStream.readObject(),dataOutputStream);
                }
            }catch (EOFException e) {}

            objectInputStream.close();
            fileInputStream.close();
            dataOutputStream.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void escribeDato(Persona object, DataOutputStream dataOutputStream) {

        try {

            dataOutputStream.writeUTF(object.getNombre());
            dataOutputStream.writeUTF(object.getDNI());
            dataOutputStream.writeInt(object.getEdad());
            dataOutputStream.writeDouble(object.getSalario());
            dataOutputStream.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
