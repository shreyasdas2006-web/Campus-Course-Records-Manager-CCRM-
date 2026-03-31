package edu.ccrm.util.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * A utility class for file operations like exporting to CSV and creating
 * backups.
 */
public class FileHandler {

    /**
     * Exports a list of data rows to a CSV file.
     * 
     * @param filePath The path to the output file.
     * @param header   The header row.
     * @param rows     The data rows.
     * @throws IOException if a file error occurs.
     */
    public static void exportToCsv(Path filePath, List<String> header, List<List<String>> rows) throws IOException {
        Files.createDirectories(filePath.getParent());
        StringBuilder csvContent = new StringBuilder();
        csvContent.append(String.join(",", header)).append(System.lineSeparator());

        for (List<String> row : rows) {
            csvContent.append(String.join(",", row)).append(System.lineSeparator());
        }

        Files.writeString(filePath, csvContent.toString());
    }

    /**
     * Creates a timestamped backup folder.
     * 
     * @param baseDirectory The directory where the backup folder will be created.
     * @return The path to the newly created backup folder.
     * @throws IOException if a file error occurs.
     */
    public static Path createBackupFolder(Path baseDirectory) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        Path backupPath = baseDirectory.resolve("backup_" + LocalDateTime.now().format(formatter));
        Files.createDirectories(backupPath);
        return backupPath;
    }

    /**
     * Recursively calculates the total size of a directory.
     * 
     * @param directory The directory to measure.
     * @return The total size in bytes.
     * @throws IOException if a file error occurs.
     */
    public static long getFolderSize(Path directory) throws IOException {
        if (!Files.exists(directory)) {
            return 0L;
        }
        return Files.walk(directory)
                .filter(p -> Files.isRegularFile(p))
                .mapToLong(p -> {
                    try {
                        return Files.size(p);
                    } catch (IOException e) {
                        return 0L;
                    }
                }).sum();
    }
}
