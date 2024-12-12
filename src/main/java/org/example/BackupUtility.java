package org.example;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BackupUtility {
    public static void main(String[] args) {
        String sourceDir = "src/main/resources";
        String backupDir = "./backup";

        createBackup(sourceDir, backupDir);
    }

    public static void createBackup(String sourceDir, String backupDir) {
        File sourceFolder = new File(sourceDir);
        File backupFolder = new File(backupDir);


        if (!backupFolder.exists()) {
            backupFolder.mkdir();
        }


        File[] files = sourceFolder.listFiles((dir, name) -> new File(dir, name).isFile());

        if (files != null) {
            for (File file : files) {
                try {
                    Path sourcePath = file.toPath();
                    Path backupPath = Paths.get(backupDir, file.getName());
                    Files.copy(sourcePath, backupPath);
                    System.out.println("Файл " + file.getName() + " успешно скопирован.");
                } catch (IOException e) {
                    System.err.println("Ошибка при копировании файла " + file.getName() + ": " + e.getMessage());
                }
            }
        } else {
            System.out.println("Исходная директория пуста или не найдена.");
        }
    }
}
