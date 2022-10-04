package AED.PlantillasUT1;

import java.io.*;
import java.util.Objects;

public class DataIO {

    private File file;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public DataIO(String path) {
        this.file = new File(path);
    }

    public void readFile() {
        try {
            dataInputStream = new DataInputStream(new FileInputStream(file));

            //Repetirá infinitamente hasta que ocurra la EOFException (cuando no queden datos a leer del fichero)
            try {
                while (true) {

                    //Modificar a gusto
                    String nombre = dataInputStream.readUTF();
                    String DNI = dataInputStream.readUTF();
                    Integer edad = dataInputStream.readInt();
                    Double salario = dataInputStream.readDouble();


//                    if (edad>=18) {
//                        System.out.println("||----------||Persona||----------||\n" +
//                                "Nombre: " +nombre + '\n' +
//                                "DNI: " + DNI + '\n' +
//                                "Edad: " + edad +'\n'+
//                                "Salario: " + decimalFormat.format(salario) +
//                                "€\n");
//                    }
                }
            } catch (EOFException e) {
            }

            dataInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile() {
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(file));

            //Repetirá infinitamente hasta que ocurra la EOFException (cuando no queden datos a leer del fichero)
            try {

                //Escribe los datos del objeto leído, y los guarda en un nuevo fichero como datos primitivos
                dataOutputStream.writeUTF("object.getNombre()"); //UTF escribe Strings
                dataOutputStream.writeUTF("object.getDNI()");
                dataOutputStream.flush();
            } catch (EOFException e) {
            }

            dataOutputStream.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }
}
