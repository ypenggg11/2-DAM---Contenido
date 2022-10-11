package PGV.UT1.Simulacro.Proceso2JAR.src;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOBytes {

    private Process process;
    private InputStream inputStream;
    private OutputStream outputStream;

    public IOBytes(Process process) {
        this.process = process;
    }

    //Lectura (en bytes) de los datos recuperados en el proceso hijo.
    public String readStream() throws IOException {
        StringBuilder text = new StringBuilder();

        inputStream = process.getInputStream();

        int c;
        while ((c = inputStream.read())!=-1) {
            text.append((char) c);
        }

        inputStream.close();

        return text.toString();
    }

    //Escritura (en bytes) desde el padre, hacia el hijo.
    public void writeStream(String line) throws IOException {
        outputStream = process.getOutputStream();

        outputStream.write(line.getBytes());
        outputStream.flush();

        outputStream.close();
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
