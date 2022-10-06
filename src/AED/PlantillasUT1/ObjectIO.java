package AED.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

//El objeto a escribir tiene que implementar la interfaz serializable
public class ObjectIO {

    private File file;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ObjectIO(String path) {
        this.file = new File(path);

        try {
            this.objectInputStream = new ObjectInputStream(new FileInputStream(this.file));
            this.objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readObject() {
        try {

            try {
                while (true) {
                    //Castea al nombre del objeto correspondiente
                    System.out.println(objectInputStream.readObject().toString());
                }
            }catch (EOFException e) {}

        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Object> readObjectToList() {
        //Cambiar según el tipo de objeto
        ArrayList<Object> objectList = new ArrayList<>();

        try {

            try {
                while (true) {
                    objectList.add(objectInputStream.readObject());
                }
            }catch (EOFException e) {}

        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objectList;
    }

    public <T> void writeObject(ArrayList<T> objectList) {
        try {
            for (int i = 0;i<objectList.size();i++) {
                //Cambiar según el objeto
                objectOutputStream.writeObject(objectList.get(i));
            }

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void closeWriter() {
        try {
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeReader() {
        try {
            objectInputStream.close();
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

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }
}
