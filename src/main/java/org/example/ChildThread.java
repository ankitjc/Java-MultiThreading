package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ChildThread implements Runnable {

    private String threadName;
    private static final String file = "https://ankitjc.github.io/images/github.png";

    public ChildThread() {}
    public ChildThread(String name) {
        threadName = name;
    }

    @Override
    public void run() {
        System.out.println("Inside Child Thread [" + threadName + "]");
        try {
            String fileName = threadName + "-" + Paths.get(file).getFileName().toString();

            // Create a Path for the destination file
            Path destinationPath = Paths.get("/Users/ankitchaudhari/images", fileName);

            System.out.println(">>Downloading File for [" + threadName + "]");
            downloadFile(file, destinationPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Exiting Child Thread [" + threadName + "]");
    }

    public static void downloadFile(String fileURL, Path destinationPath) throws IOException {
        URL url = new URL(fileURL);  // Create a URL object from the file URL
        try (InputStream inputStream = url.openStream()) {  // Open the stream to the file
            Files.copy(inputStream, destinationPath, StandardCopyOption.REPLACE_EXISTING);  // Download and save the file
            System.out.println("File downloaded successfully");
        } catch (IOException e) {
            System.err.println("Error downloading file: " + e.getMessage());
            throw e;  // Rethrow the exception
        }
    }
}
