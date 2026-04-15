package com.quantcast.cookieanalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookieService {

    public List<String> findMostActiveCookies(String filePath, String targetDate) {
        Map<String, Integer> cookieCounts = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 2 || parts[0].isBlank() || parts[1].isBlank() || parts[1].length() < 10) {
                    throw new RuntimeException("Invalid CSV row: " + line);
                }

                String cookie = parts[0];
                String timestamp = parts[1];
                String date = timestamp.substring(0, 10);

                int comparison = date.compareTo(targetDate);

                if (comparison > 0) {
                    continue;
                }

                if (comparison < 0) {
                    break;
                } 

                cookieCounts.put(cookie, cookieCounts.getOrDefault(cookie, 0) + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage());
        }

        int maxCount = 0;
        for (int count : cookieCounts.values()) {
            maxCount = Math.max(maxCount, count);
        }

        List<String> mostActiveCookies = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cookieCounts.entrySet()) {
            if (entry.getValue() == maxCount) {
                mostActiveCookies.add(entry.getKey());
            }
        }

        return mostActiveCookies;
    }
}
