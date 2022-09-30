package AED.PlantillasUT1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class IO_Stream {

    private File file;
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;

    public IO_Stream(String path) {
        this.file = new File(Objects.requireNonNull(this.getClass().getResource(path)).getFile());
    }

    public void writeFile(String line,boolean append) {
        try {
            fileOutputStream = new FileOutputStream(file,append);

            fileOutputStream.write(line.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();

            fileOutputStream.close();
        }catch (NullPointerException | FileNotFoundException e) {
            System.out.println("File doesn't exists");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFile() {

        try {
            fileInputStream = new FileInputStream(this.file);

            int character;

            while ((character = fileInputStream.read())!=-1) {
                System.out.print((char)character);
            }

            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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
