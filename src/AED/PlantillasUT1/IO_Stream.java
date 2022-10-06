package AED.PlantillasUT1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class IO_Stream {

    private File file;
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;

    public IO_Stream(String path) {
        this.file = new File(path);

        try {
            this.fileInputStream = new FileInputStream(this.file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String line,boolean append) {
        try {
            fileOutputStream = new FileOutputStream(this.file,append);

            fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();

        }catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File doesn't exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String readFile() {

        StringBuffer text = new StringBuffer();

        try {
            fileInputStream = new FileInputStream(this.file);

            int character;

            while ((character = fileInputStream.read())!=-1) {
                text.append((char)character);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return text.toString();
    }

    public void closeWriter() {
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeReader() {
        try {
            fileInputStream.close();
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
