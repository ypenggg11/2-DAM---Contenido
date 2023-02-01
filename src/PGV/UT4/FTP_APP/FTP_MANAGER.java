package PGV.UT4.FTP_APP;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class FTP_MANAGER {

    private final FTPClient client;
    private final String USERNAME;
    private final String PASSWORD;

    public FTP_MANAGER(String username, String password) {
        this.USERNAME = username;
        this.PASSWORD = password;

        this.client = new FTPClient();
        this.client.enterLocalPassiveMode();
    }

    public boolean connect(String serverIp) {
        try {
            client.connect(serverIp);
            client.login(USERNAME, PASSWORD);

            if (!FTPReply.isPositiveCompletion(client.getReplyCode())) {
                disconnect();
                return false;
            }

            return true;

        } catch (IOException e) {
            return false;
        }
    }

    public boolean uploadOneFile(String localPath,String destinationFilePath) {
        try {
            BufferedInputStream bi = new BufferedInputStream(new FileInputStream(localPath));

            return client.storeFile(destinationFilePath, bi);
        } catch (IOException e) {
            return false;
        }
    }

    public boolean downloadOneFile(String destinationLocalPath,String remoteFilePath) {
        try {
            BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(destinationLocalPath+"/"+remoteFilePath.split("/")[remoteFilePath.split("/").length-1]));

            return client.retrieveFile(remoteFilePath, bo);
        } catch (IOException e) {
            return false;
        }
    }

    public ArrayList<String> getDirList(){

        ArrayList<String> dirList = new ArrayList<>();

        try {
            for (FTPFile ftpFile : client.listDirectories()) {
                dirList.add(ftpFile.getName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dirList;
    }

    private ArrayList<String> directories = new ArrayList<>();
    private HashMap<String, FTPFile> fileList = new HashMap<>();

    public HashMap<String, FTPFile> getAllFiles(String path) throws IOException {
        do {
            FTPFile[] files = client.listFiles(path);
            for (FTPFile file : files) {
                if (file.isDirectory()) {
                    directories.add(path + "/" + file.getName());
                } else {
                    fileList.put(path + "/"+file.getName(),file);
                }
            }
            if (!directories.isEmpty()) {
                String directory = directories.get(0);
                directories.remove(0);
                getAllFiles(directory);
            }
        }while(!directories.isEmpty());

        return fileList;
    }

    public void clearFilesList() {
        directories.clear();
        fileList.clear();
    }

    public boolean makeDir(String pathName) {
        try {
            return  client.makeDirectory(pathName);
        } catch (IOException e) {
            return false;
        }
    }

    public void disconnect() {
        try {
            if (client.isConnected()) {
                client.disconnect();
            }
        } catch (IOException ignored) {
        }
    }

    public FTPClient getClient() {
        return client;
    }
}
