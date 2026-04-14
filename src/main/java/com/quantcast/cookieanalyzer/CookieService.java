package com.quantcast.cookieanalyzer;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CookieService {
    
    public void readFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                
                String cookie = parts[0];
                String timestamp = parts[1];


                System.out.println("Cookie: " + cookie);
                System.out.println("Timestamp: " + timestamp);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage());
        }
    }

}
