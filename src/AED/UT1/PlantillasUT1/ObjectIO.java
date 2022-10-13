package AED.UT1.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

//El objeto a escribir tiene que implementar la interfaz serializable
public class ObjectIO {

    private File file;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;

    public ObjectIO(String path) {
        this.file = new File(path);
    }

    /**
     * Lee los objetos del fichero y ejecuta su método .toString() correspondiente.
     */
    public void readObject() {
        try {
            this.objectInputStream = new ObjectInputStream(new FileInputStream(this.file));

            try {
                while (true) {
                    //Castea al nombre del objeto correspondiente
                    System.out.println(this.objectInputStream.readObject().toString());
                }
            }catch (EOFException e) {}

            this.objectInputStream.close();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Lee los objetos del fichero y lo almacena en un ArrayList
     * @return ArrayList (Cambiar el tipo según el objeto leído)
     */
    public ArrayList<Object> readObjectToList() {
        //Cambiar según el tipo de objeto
        ArrayList<Object> objectList = new ArrayList<>();

        try {
            this.objectInputStream = new ObjectInputStream(new FileInputStream(this.file));

            try {
                while (true) {
                    objectList.add(this.objectInputStream.readObject());
                }
            }catch (EOFException e) {}

            this.objectInputStream.close();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return objectList;
    }

    /**
     * Escribe la lista de objetos en un fichero
     * (de cualquier tipo o uno en concreto cambiandolo directamente)
     * @param objectList lista de objetos
     * @param <T> Permite cualquier tipo de dato (generics)
     */
    public <T> void writeObject(ArrayList<T> objectList) {
        try {
            this.objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.file));

            for (int i = 0;i<objectList.size();i++) {
                //Cambiar según el objeto
                this.objectOutputStream.writeObject(objectList.get(i));
            }

            this.objectOutputStream.close();

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
