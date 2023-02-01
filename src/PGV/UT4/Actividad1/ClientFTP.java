package PGV.UT4.Actividad1;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
 *   Create a ftp client and connects it to a ftp server
 * */
public class ClientFTP {
    private static final String FTP_SERVER = "192.168.10.1";
    private static final String USERNAME = "pgv";
    private static final String PASSWORD = "pgv";

    public static void main(String[] args) throws IOException {


        /* Connect to the ftp server as a client */
        FTPClient ftpClient = new FTPClient();

        ftpClient.connect(FTP_SERVER);
        ftpClient.enterLocalPassiveMode();

        /* Get the connection reply from the server */
        System.out.println(ftpClient.getReplyString());

        /* Login with an anonymous credential */
        ftpClient.login(USERNAME, PASSWORD);

        /* Print the current working directory (by default = / ) */
        //System.out.println(ftpClient.printWorkingDirectory());

        /* If the connections it's not successfully completed */
        if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            ftpClient.disconnect();
            System.out.println("Connection declined!");
            System.exit(0);
        }

        /* List files from the current directory and prints it */
        FTPFile[] ftpFiles = ftpClient.listFiles();
        System.out.println("Amount of files: " + ftpFiles.length);

        for (FTPFile file : ftpFiles) {
            System.out.println(file.getName());
        }

        System.out.println();

        /* Download files from server, saving the specified file content in the created file (mensaje.txt) */
        BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream("./src/PGV/UT4/Actividad1/mensaje.txt"));

        if (ftpClient.retrieveFile(ftpFiles[ftpFiles.length-1].getName(),bo)) {
            System.out.println("File retrieved");
        } else {
            System.out.println("Can't download file");
        }

        bo.close();

        ftpClient.disconnect();
        System.out.println("Connection finished successfully!");
    }
}
