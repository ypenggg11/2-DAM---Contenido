package AED.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

//El objeto a escribir tiene que implementar la interfaz serializable
public class ObjectIO {

    private File file;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ObjectIO(String path) {
        this.file = new File(Objects.requireNonNull(this.getClass().getResource(path)).getFile());
    }

    public void readObject() {
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(this.file));

            try {
                while (true) {
                    //Castea al nombre del objeto correspondiente
                    System.out.println(objectInputStream.readObject().toString());
                }
            }catch (EOFException e) {}

            objectInputStream.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<Object> readObject(boolean toList) {
        //Cambiar según el tipo de objeto
        ArrayList<Object> objectList = new ArrayList<>();

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(this.file));

            try {
                while (true) {
                    objectList.add(objectInputStream.readObject());
                }
            }catch (EOFException e) {}

            objectInputStream.close();
        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objectList;
    }

    public void writeObject(int numOfObjects) {
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.file));

            for (int i = 0;i<numOfObjects;i++) {
                //Cambiar según el objeto
                objectOutputStream.writeObject(new Object(/*nombre.get(i),dni.get(i),edad.get(i),salario.get(i)*/));
            }

            objectOutputStream.close();
        } catch (IOException i) {
            i.printStackTrace();
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
