package AED.PlantillasUT1;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class IO_Stream {

    private File file;
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;

    public IO_Stream(String path) {
        this.file = new File(path);
    }

    //Escribe en el fichero, la linea indicada en el parametro
    //En el caso de (append), se añadirá en vez de sobreescribir.
    public void writeFile(String line,boolean append) {
        try {
            this.fileOutputStream = new FileOutputStream(this.file,append);

            //FileI/OStream, trabaja en bytes.
            this.fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
            this.fileOutputStream.flush();

            this.fileOutputStream.close();
        }catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File doesn't exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Lee el contenido del fichero, y devuelve su contenido en una String
    public String readFile() {

        StringBuffer text = new StringBuffer();

        try {
            this.fileInputStream = new FileInputStream(this.file);

            int character;

            //Al trabajar en bytes, lo leeremos como int y lo casteamos a char.
            while ((character = this.fileInputStream.read()) != -1) {
                text.append((char) character);
            }

            this.fileInputStream.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text.toString();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }

    public void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    public FileInputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }
}
