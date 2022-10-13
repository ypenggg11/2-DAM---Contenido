package AED.UT1.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

//Si da error, borrar el fichero creado y ejecutar de nuevo. (puede dar error al escribir, ya que no sobreescribe)
//Ejemplo de utilización en TesteoFuncionamiento/MainPruebas.java
public class RandomAccessIO {

    //Tamaño por defecto de bytes.
    public enum BYTES {
        INT(4),
        CHAR(2),
        STRINGBUFFER_LENGTH(40),
        STRINGBUFFER(CHAR.byteSize * STRINGBUFFER_LENGTH.byteSize),
        DOUBLE(8);

        int byteSize;

        //Constructor del Enum
        BYTES(int byteSize) {
            this.byteSize = byteSize;
        }
    }

    private String path;
    private RandomAccessFile randomAccessFile;

    public RandomAccessIO(String path) {
        this.path = path;
    }

    /**
     * Inicia el proceso de escritura.
     */
    public void initWriter() {
        try {
            this.randomAccessFile = new RandomAccessFile(path, "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Escribe en el fichero el tipo de dato pasado por parámetro.
     * (Requiere initWriter al principio y closeWriter al terminar)
     *
     * @param dataType  Indicar el tipo de dato.
     * @param dataValue Indicar el valor del dato.
     * @param <T>       Permite cualquier tipo de dato, pero tiene q concordar con el tipo de BYTES. (INT, 6)
     */
    public <T> void writeFile(BYTES dataType, T dataValue) {
        try {
            switch (dataType) {
                case INT -> this.randomAccessFile.writeInt((Integer) dataValue);
                case CHAR -> this.randomAccessFile.writeChar((Character) dataValue);
                case STRINGBUFFER -> {
                    StringBuilder stringBuffer = new StringBuilder(dataValue.toString());
                    stringBuffer.setLength(BYTES.STRINGBUFFER_LENGTH.byteSize);

                    this.randomAccessFile.writeChars(stringBuffer.toString());
                }
                case DOUBLE -> this.randomAccessFile.writeDouble((Double) dataValue);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Escribe en una posición concreta, el tipo de dato pasado por parámetro. (Para sobreescribir datos)
     * (Requiere initWriter al principio y closeWriter al terminar)
     *
     * @param dataType  Indicar el tipo de dato.
     * @param dataValue Indicar el valor del dato.
     * @param <T>       Permite cualquier tipo de dato, pero tiene q concordar con el tipo de BYTES. (INT, 6)
     * @param bytePos   Posición del dato a modificar/ lugar a escribir.
     */
    public <T> void writeFileByPos(BYTES dataType, T dataValue, int bytePos) {
        try {
            this.randomAccessFile.seek(bytePos);

            switch (dataType) {
                case INT -> this.randomAccessFile.writeInt((Integer) dataValue);
                case CHAR -> this.randomAccessFile.writeChar((Character) dataValue);
                case STRINGBUFFER -> {
                    StringBuilder stringBuffer = new StringBuilder(dataValue.toString());
                    stringBuffer.setLength(BYTES.STRINGBUFFER_LENGTH.byteSize);

                    this.randomAccessFile.writeChars(stringBuffer.toString());
                }
                case DOUBLE -> this.randomAccessFile.writeDouble((Double) dataValue);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Cierra el randomAccessFile.
     */
    public void closeWriter() {
        try {
            this.randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Lee el contenido del fichero.
     *
     * @param bytesOrder Orden en el que fueron escritos los bytes.
     * @return Lista con el contenido leido.
     */
    public ArrayList<String> readAllFile(BYTES[] bytesOrder) {
        ArrayList<String> dataList = new ArrayList<>();
        int bytePos = 0, totalBytes = getTotalSize(bytesOrder);

        try {
            this.randomAccessFile = new RandomAccessFile(path, "r");

            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {

                //Dependiendo del tipo de dato, lo leerá con su correspondiente método.
                readByType(bytesOrder, dataList);

                bytePos += totalBytes;
                this.randomAccessFile.seek(bytePos);
            }

            this.randomAccessFile.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }

    /**
     * Lee un registro de un fichero, desde un lugar en concreto.
     *
     * @param initBytePos Posición desde donde quieras leer.
     * @param bytesOrder  Orden en el que fueron escritos los bytes.
     * @return Lista con el contenido leído.
     */
    public ArrayList<String> readByPosition(int initBytePos, BYTES[] bytesOrder) {
        ArrayList<String> dataList = new ArrayList<>();
        int bytePos = initBytePos;

        try {
            this.randomAccessFile = new RandomAccessFile(path, "r");
            this.randomAccessFile.seek(bytePos);

            readByType(bytesOrder, dataList);

            this.randomAccessFile.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }

    /**
     * Busca una posición en concreto, si coincide con el 'id'.
     *
     * @param id         String a buscar dentro del fichero.
     * @param bytesTypes Orden en el que fueron escritos los bytes.
     * @return Array de int, [0] = posición del registro / [1] = posición concreta.
     */
    public int[] searchPositionById(String id, BYTES[] bytesTypes) {

        int bytePos = 0, totalBytes = getTotalSize(bytesTypes);
        int[] idPosition = new int[2];

        try {
            this.randomAccessFile = new RandomAccessFile(path, "r");
            this.randomAccessFile.seek(bytePos);

            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {
                //Dependiendo del tipo de dato, lo leerá con su correspondiente método.
                for (BYTES type : bytesTypes) {
                    String auxId = "";

                    //Debido al desplazamiento al leer, al final habrá que restarle una cantidad de bytes
                    //concreta para tener la posición exacta de la concordancia.
                    int auxPosFixer = 0;

                    switch (type) {
                        case INT -> {
                            auxId = String.valueOf(this.randomAccessFile.readInt());
                            auxPosFixer = BYTES.INT.byteSize;
                        }
                        case CHAR -> {
                            auxId = String.valueOf(this.randomAccessFile.readChar());
                            auxPosFixer = BYTES.CHAR.byteSize;
                        }
                        case STRINGBUFFER -> {
                            char[] string = new char[BYTES.STRINGBUFFER_LENGTH.byteSize];

                            for (int i = 0; i < string.length; i++) {
                                string[i] = this.randomAccessFile.readChar();
                            }

                            auxId = new String(string).trim();
                            auxPosFixer = BYTES.STRINGBUFFER.byteSize;
                        }
                        case DOUBLE -> {
                            auxId = String.valueOf(this.randomAccessFile.readDouble());
                            auxPosFixer = BYTES.DOUBLE.byteSize;
                        }
                    }

                    if (auxId.equals(id)) {
                        idPosition[0] = bytePos;
                        idPosition[1] = (int) this.randomAccessFile.getFilePointer() - auxPosFixer;
                    }
                }

                bytePos += totalBytes;
                randomAccessFile.seek(bytePos);
            }

            this.randomAccessFile.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return idPosition;
    }

    /**
     * Lee del fichero el dato dependiendo del tipo, y lo guarda en una lista.
     *
     * @param bytesTypes Orden en el que fueron escritos los bytes.
     * @param dataList   Lista usada para almacenar los datos leidos.
     * @throws IOException Control de excepciones.
     */
    private void readByType(BYTES[] bytesTypes, ArrayList<String> dataList) throws IOException {

        for (BYTES type : bytesTypes) {
            switch (type) {
                case INT -> dataList.add(String.valueOf(this.randomAccessFile.readInt()));
                case CHAR -> dataList.add(String.valueOf(this.randomAccessFile.readChar()));
                case STRINGBUFFER -> {
                    char[] string = new char[BYTES.STRINGBUFFER_LENGTH.byteSize];

                    for (int i = 0; i < string.length; i++) {
                        string[i] = this.randomAccessFile.readChar();
                    }

                    dataList.add(new String(string).trim());
                }
                case DOUBLE -> dataList.add(String.valueOf(this.randomAccessFile.readDouble()));
            }
        }
    }

    /**
     * Devuelve el tamaño total de un registro.
     * Un registro = (Miguel, 21, Estudiante)
     *
     * @param allSizes Orden en el que fueron escritos los bytes.
     * @return El tamaño total.
     */
    private int getTotalSize(BYTES[] allSizes) {
        int totalSize = 0;
        for (BYTES size : allSizes) {
            totalSize += size.byteSize;
        }
        return totalSize;
    }

}
