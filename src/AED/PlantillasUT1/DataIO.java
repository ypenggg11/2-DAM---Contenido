package AED.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

public class DataIO {

    //Enum con los tipos de primitivos permitidos.(los fundamentales)
    enum PrimitiveTypes {
        INT, DOUBLE, CHAR, STRING, BOOLEAN, FLOAT;
    }

    private File file;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public DataIO(String path) {
        this.file = new File(path);
    }

    //Lee el fichero y devuelve un ArrayList con todos los datos primitivos.
    //(Se tiene que saber el orden y el tipo de primitivo que fueron introducidos)
    //Para ello, usaremos un array de nuestro Enum PrimitivesTypes
    //(Ej: [PrimitiveTypes.INT,PrimitiveTypes.DOUBLE]...)
    public ArrayList<String> readFile(PrimitiveTypes[] dataTypes) {
        ArrayList<String> dataList = new ArrayList<>();

        try {
            this.dataInputStream = new DataInputStream(new FileInputStream(this.file));

            //Repetirá infinitamente hasta que ocurra la EOFException (cuando no queden datos a leer del fichero)
            try {
                while (true) {
                    //Dependiendo del tipo de dato, lo leerá con su correspondiente método.
                    for (PrimitiveTypes type : dataTypes) {
                        switch (type) {
                            case INT -> dataList.add(String.valueOf(this.dataInputStream.readInt()));
                            case CHAR -> dataList.add(String.valueOf(this.dataInputStream.readChar()));
                            case STRING -> dataList.add(this.dataInputStream.readUTF());
                            case BOOLEAN -> dataList.add(String.valueOf(this.dataInputStream.readBoolean()));
                            case FLOAT -> dataList.add(String.valueOf(this.dataInputStream.readFloat()));
                            case DOUBLE -> dataList.add(String.valueOf(this.dataInputStream.readDouble()));
                        }
                    }

                }
            } catch (EOFException e) {
            }

            this.dataInputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }

    //Escribe en el fichero, cualquier primitivo pasado por parametro (T dataValue)
    //T permitirá cualquier tipo de variable (generics) y hay q indicarlo con <T>
    //Se deberá indicar el tipo de dato (dataTypes) y si lo añadiremos o sobreescribiremos (append)
    public <T> void writeFile(PrimitiveTypes dataTypes, T dataValue,boolean append) {
        try {

            this.dataOutputStream = new DataOutputStream(new FileOutputStream(this.file,append));

            switch (dataTypes) {
                case INT -> this.dataOutputStream.writeInt((Integer) dataValue);
                case CHAR -> this.dataOutputStream.writeChar((Character) dataValue);
                case STRING -> this.dataOutputStream.writeUTF((String) dataValue);
                case BOOLEAN -> this.dataOutputStream.writeFloat((Float) dataValue);
                case DOUBLE -> this.dataOutputStream.writeDouble((Double) dataValue);
            }

            this.dataOutputStream.close();

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
