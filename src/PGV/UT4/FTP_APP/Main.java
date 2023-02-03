package PGV.UT4.FTP_APP;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    /* This computer local ip */
    private static String FTP_SERVER;

    /* Filezilla created user login credentials */
    private static String USERNAME;
    private static String PASSWORD;
    private static FTP_MANAGER ftpManager;
    private static ArrayList<File> dirFiles;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        boolean connected = false;

        while (!connected) {
            System.out.print("Username: ");
            USERNAME = scanner.nextLine();

            System.out.print("Password: ");
            PASSWORD = scanner.nextLine();

            ftpManager = new FTP_MANAGER(USERNAME, PASSWORD);

            System.out.print("Server IP: ");
            FTP_SERVER = scanner.nextLine();

            if (ftpManager.connect(FTP_SERVER)) {
                System.out.println("CONNECTED!");
                connected = true;

                System.out.print("\n[0] UPLOAD | [1] DOWNLOAD | [2] DELETE REMOTE: ");
                String choose = scanner.nextLine();

                switch (choose.toUpperCase()) {
                    case "0", "UPLOAD" -> {

                        dirFiles = new ArrayList<>();
                        loadAllLocalFiles(getLocalDir());

                        if (uploadRequest()) {
                            upload();
                            showAllRemoteFiles();
                        }
                    }
                    case "1", "DOWNLOAD" -> {
                        showAllRemoteFiles();
                        String localDir = getLocalDir().getAbsolutePath();
                        downloadFile(createNewLocalDir(localDir));
                    }
                    case "2", "DELETE REMOTE" -> {
                        showAllRootDirectories();
                        deleteRemoteDir();
                    }
                    default -> System.out.println("?????????");
                }

                ftpManager.disconnect();
                scanner.close();
                System.out.println("\nDISCONNECTED!");

            } else {
                System.out.println("WRONG CREDENTIALS!");
            }
        }
    }

    private static void showAllRootDirectories() {
        System.out.println();
        ftpManager.getDirList().forEach(System.out::println);
    }

    private static void deleteRemoteDir() {
        boolean deleted = false;

        do {
            System.out.println("\nEnter one directory name from one of above: ");
            String dirName = scanner.nextLine();
            System.out.println();

            if (!dirName.equals("")) {
                boolean delete = ftpManager.deleteDir(dirName);
                System.out.println(dirName+" deleted: "+delete);
            }

            System.out.println("\nDelete another file?");
            System.out.print("[0] YES | [1] NO: ");
            String input = scanner.nextLine();

            deleted = input.equalsIgnoreCase("YES") || input.equals("0");

        } while (deleted);
    }

    private static String createNewLocalDir(String destinationPath) {
        boolean dirCreated = false;
        String newDir;

        do {
            System.out.println("\nEnter a new local directory name/path (Ex: dir2 | dir2/subdir2 ): ");
            newDir = scanner.nextLine();

            if (!newDir.equals("")) {
                dirCreated = new File(destinationPath + "/" + newDir).mkdir();
            }
        } while (!dirCreated);

        newDir = destinationPath + "/" + newDir;

        return newDir;
    }

    private static void downloadFile(String destinationLocalPath) {
        boolean continueDownloading;

        do {
            System.out.println("\nEnter one directory from the remote list: ");
            String dir = scanner.nextLine();
            System.out.println();

            if (!dir.equals("")) {
                boolean downloaded = ftpManager.downloadAllFilesFromDir(destinationLocalPath, dir);
                System.out.println(dir + " downloaded: " + downloaded);
            }

            System.out.println("\nDownload another file?");
            System.out.print("[0] YES | [1] NO: ");
            String input = scanner.nextLine();

            continueDownloading = input.equalsIgnoreCase("YES") || input.equals("0");

        } while (continueDownloading);
    }

    private static void showAllRemoteFiles() throws IOException {

        HashMap<String, FTPFile> allFiles = ftpManager.getAllFiles("");
        ArrayList<String> filesPath = new ArrayList<>();

        allFiles.forEach((path, file) -> {
            filesPath.add(path);
        });

        Collections.sort(filesPath);

        System.out.println("\n||==========||Server files: " + filesPath.size() + "||==========||\n");

        System.out.println("DIRECTORIES: "+FTP_MANAGER.dirAmout+"\n");

        filesPath.forEach(System.out::println);

        ftpManager.clearFilesList();
    }

    private static void upload() {
        if (dirFiles.size() > 0) {

            boolean dirCreated = false;

            do {
                System.out.println("\nEnter a new remote directory name/path (Ex: dir2 | dir2/subdir2 ): ");
                String newDir = scanner.nextLine();

                if (!newDir.equals("")) {
                    dirCreated = ftpManager.makeDir(newDir);

                    if (dirCreated) {
                        System.out.println("Dir " + newDir + " created!");
                        System.out.println("\n||==========||Upload status||==========||\n");
                        int uploadCount = 0;

                        for (File file : dirFiles) {
                            boolean uploaded = ftpManager.uploadOneFile(file.getAbsolutePath(), "./" + newDir + "/" + file.getName());
                            System.out.println(file.getName() + " uploaded: " + uploaded);

                            if (uploaded) uploadCount++;
                        }

                        System.out.println("\nUPLOADED FILES: " + uploadCount);
                    }
                }
            } while (!dirCreated);

        } else {
            System.out.println("Empty directory, nothing uploaded...");
        }
    }

    private static boolean uploadRequest() {
        System.out.println("\n||==========||Available files: " + dirFiles.size() + "||==========||\n");
        dirFiles.forEach(it -> {
            System.out.println(it.getAbsolutePath());
        });

        System.out.println("\nDo you want to upload them to the server?");
        System.out.print("[0] YES | [1] NO : ");

        String input = scanner.nextLine();
        return input.equalsIgnoreCase("YES") || input.equals("0");
    }

    private static File getLocalDir() {
        File dir;

        do {
            System.out.println("\nInput a local directory absolute path");
            System.out.print("Dir: ");
            dir = new File(scanner.nextLine());
        } while (!dir.isDirectory() || !dir.isAbsolute() || !dir.exists());

        return dir;
    }

    private static void loadAllLocalFiles(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    loadAllLocalFiles(file);
                } else {
                    dirFiles.add(file);
                }
            }
        }
    }
}
