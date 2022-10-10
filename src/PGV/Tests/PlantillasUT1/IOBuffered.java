package PGV.Tests.PlantillasUT1;

import java.io.*;
import java.util.ArrayList;

public class IOBuffered {

    private Process process;
    private InputStream inputStream;
    private OutputStream outputStream;

    public IOBuffered(Process process) {
        this.process = process;
    }

    public ArrayList<String> readLines(){
        ArrayList<String> lines = new ArrayList<>();

        try {
            inputStream = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine())!=null) {
                lines.add(line);
            }

            bufferedReader.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lines;
    }

    public void writeLine(String line){
        try {
            outputStream = process.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            bufferedWriter.write(line);
            bufferedWriter.flush();

            bufferedWriter.close();
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
