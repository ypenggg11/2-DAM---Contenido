package AED.Actividad2Serializacion;

import java.io.*;
import java.text.DecimalFormat;

//Apartado 2
public class Main2 {
    private static String path = "./src/AED/Actividad2Serializacion/Persona.DAT";
    private static DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public static void main(String[] args) {

        //Lee las personas del fichero Persona.obj y escribe los datos del objeto leído en Persona.DAT (DataOutputStream)
        leePersonas();

        //Lee el fichero Persona.DAT
        leerFicheroDAT();
    }

    private static void leerFicheroDAT() {
        //Lectura de datos primitivos
        DataInputStream dataInputStream;

        try {
            dataInputStream = new DataInputStream(new FileInputStream(path));

            //Repetirá infinitamente hasta que ocurra la EOFException (cuando no queden datos a leer del fichero)
            try {
                while (true) {

                    //Lee los datos primitivos del fichero Persona.DAT
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

    private static void leePersonas() {

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

        //Escritura de datos primitivos
        DataOutputStream dataOutputStream;

        try {
            fileInputStream = new FileInputStream("./src/AED/Actividad2Serializacion/Persona.obj");
            objectInputStream = new ObjectInputStream(fileInputStream);

            dataOutputStream = new DataOutputStream(new FileOutputStream(path));

            //Repetirá infinitamente hasta que ocurra la EOFException (cuando no queden datos a leer del fichero)
            try {
                while (true) {
                    //Escribe los datos del objeto leído, y los guarda en un nuevo fichero como datos primitivos
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

            //Escribe los atributos del objeto y los guarda como datos primitivos

            dataOutputStream.writeUTF(object.getNombre()); //UTF escribe Strings
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
