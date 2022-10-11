package PGV.UT1.Simulacro;

import java.io.*;
import java.util.ArrayList;

public class IOBuffered {

    private Process process;
    private InputStream inputStream;
    private OutputStream outputStream;

    public IOBuffered(Process process) {
        this.process = process;
    }

    //Lee el contenido y lo devuelve en forma de lista
    public ArrayList<String> readLines(){
        ArrayList<String> lines = new ArrayList<>();

        try {
            inputStream = process.getInputStream();
            //Necesario InputStreamReader()
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

    //Escribe la linea pasada por parámetro
    public void writeLine(String line){
        try {
            outputStream = process.getOutputStream();
            //Necesario OutputStreamWriter()
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            bufferedWriter.write(line);
            bufferedWriter.flush();

            bufferedWriter.close();
            outputStream.close();
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
