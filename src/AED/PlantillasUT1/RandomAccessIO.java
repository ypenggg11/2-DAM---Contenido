package AED.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

public class RandomAccessIO {

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

    public void initWriter() {
        try {
            this.randomAccessFile = new RandomAccessFile(path, "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void writeFile(BYTES dataType, T dataValue) {
        try {

            switch (dataType) {
                case INT -> this.randomAccessFile.writeInt((Integer) dataValue);
                case CHAR -> this.randomAccessFile.writeChar((Character) dataValue);
                case STRINGBUFFER -> {
                    StringBuffer stringBuffer = new StringBuffer(dataValue.toString());
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

    public void closeWriter() {
        try {
            this.randomAccessFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<String> readAllFile(BYTES[] bytesTypes) {
        ArrayList<String> dataList = new ArrayList<>();
        int bytePos = 0, totalBytes = getTotalSize(bytesTypes);

        try {
            this.randomAccessFile = new RandomAccessFile(path, "r");

            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {
                //Dependiendo del tipo de dato, lo leerá con su correspondiente método.
                readByType(bytesTypes, dataList);

                bytePos+=totalBytes;
                randomAccessFile.seek(bytePos);
            }

            this.randomAccessFile.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }

    public ArrayList<String> readByPosition(int initBytePos,BYTES[] bytesTypes) {
        ArrayList<String> dataList = new ArrayList<>();
        int bytePos = initBytePos;

        try {
            this.randomAccessFile = new RandomAccessFile(path, "r");
            this.randomAccessFile.seek(bytePos);

            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {
                //Dependiendo del tipo de dato, lo leerá con su correspondiente método.
                readByType(bytesTypes, dataList);
            }

            this.randomAccessFile.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }

    public int searchPositionById(String id,BYTES[] bytesTypes) {

        int bytePos = 0, totalBytes = getTotalSize(bytesTypes);
        int idPosition = 0;

        try {
            this.randomAccessFile = new RandomAccessFile(path, "r");
            this.randomAccessFile.seek(bytePos);

            while (!(randomAccessFile.getFilePointer() >= randomAccessFile.length())) {
                //Dependiendo del tipo de dato, lo leerá con su correspondiente método.
                for (BYTES type : bytesTypes) {
                    String auxId = "";

                    switch (type) {
                        case INT -> auxId = String.valueOf(this.randomAccessFile.readInt());
                        case CHAR -> auxId = String.valueOf(this.randomAccessFile.readChar());
                        case STRINGBUFFER -> {
                            char[] string = new char[BYTES.STRINGBUFFER_LENGTH.byteSize];

                            for (int i = 0;i<string.length;i++) {
                                string[i] = this.randomAccessFile.readChar();
                            }

                            auxId = new String(string).trim();
                        }
                        case DOUBLE -> auxId = String.valueOf(this.randomAccessFile.readDouble());
                    }

                    if (auxId.equals(id)) {
                        idPosition = (int) randomAccessFile.getFilePointer();
                    }
                }

                bytePos+=totalBytes;
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

    private void readByType(BYTES[] bytesTypes, ArrayList<String> dataList) throws IOException {
        for (BYTES type : bytesTypes) {
            switch (type) {
                case INT -> dataList.add(String.valueOf(this.randomAccessFile.readInt()));
                case CHAR -> dataList.add(String.valueOf(this.randomAccessFile.readChar()));
                case STRINGBUFFER -> {
                    char[] string = new char[BYTES.STRINGBUFFER_LENGTH.byteSize];

                    for (int i = 0;i<string.length;i++) {
                        string[i] = this.randomAccessFile.readChar();
                    }

                    dataList.add(new String(string).trim());
                }
                case DOUBLE -> dataList.add(String.valueOf(this.randomAccessFile.readDouble()));
            }
        }
    }

    private int getTotalSize(BYTES[] allSizes) {
        int totalSize = 0;
        for (BYTES size : allSizes) {
            totalSize += size.byteSize;
        }
        return totalSize;
    }

}
