package AED.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

public class DataIO {

    enum PrimitiveTypes {
        INT, DOUBLE, CHAR, STRING, BOOLEAN, FLOAT;
    }

    private File file;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public DataIO(String path) {
        this.file = new File(path);

        try {
            this.dataOutputStream = new DataOutputStream(new FileOutputStream(this.file));
            this.dataInputStream = new DataInputStream(new FileInputStream(this.file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<String> readFile(PrimitiveTypes[] dataTypes) {
        ArrayList<String> dataList = new ArrayList<>();

        try {

            //Repetirá infinitamente hasta que ocurra la EOFException (cuando no queden datos a leer del fichero)
            try {
                while (true) {

                    for (PrimitiveTypes type : dataTypes) {
                        switch (type) {
                            case INT -> dataList.add(String.valueOf(dataInputStream.readInt()));
                            case CHAR -> dataList.add(String.valueOf(dataInputStream.readChar()));
                            case STRING -> dataList.add(String.valueOf(dataInputStream.readUTF()));
                            case BOOLEAN -> dataList.add(String.valueOf(dataInputStream.readBoolean()));
                            case FLOAT -> dataList.add(String.valueOf(dataInputStream.readFloat()));
                            case DOUBLE -> dataList.add(String.valueOf(dataInputStream.readDouble()));
                        }
                    }

                }
            } catch (EOFException e) {
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }

    public <T> void writeFile(PrimitiveTypes dataTypes, T dataValue) {
        try {

            try {

                switch (dataTypes) {
                    case INT -> dataOutputStream.writeInt((Integer) dataValue);
                    case CHAR -> dataOutputStream.writeChar((Character) dataValue);
                    case STRING -> dataOutputStream.writeUTF((String) dataValue);
                    case BOOLEAN -> dataOutputStream.writeBoolean((Boolean) dataValue);
                    case FLOAT -> dataOutputStream.writeFloat((Float) dataValue);
                    case DOUBLE -> dataOutputStream.writeDouble((Double) dataValue);
                }

            } catch (EOFException e) {
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void closeWriter() {
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeReader() {
        try {
            dataInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
