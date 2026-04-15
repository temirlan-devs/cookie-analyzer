package com.quantcast.cookieanalyzer;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CookieServiceTest {

    private String getResourcePath(String fileName) {
        URL resource = getClass().getClassLoader().getResource(fileName);
        assertNotNull(resource, "Test resource not found: " + fileName);

        try {
            return Paths.get(resource.toURI()).toString();
        } catch (Exception e) {
            throw new RuntimeException("Error loading test resource: " + fileName, e);
        }
    }

    @Test
    void shouldReturnMostActiveCookie() {
        CookieService service = new CookieService();

        String filePath = getResourcePath("cookie_log.csv");

        List<String> result = service.findMostActiveCookies(filePath, "2018-12-09");

        assertEquals(1, result.size());
        assertEquals("AtY0laUfhglK3lC7", result.get(0));
    }

    @Test
    void shouldReturnMultipleCookiesWhenTie() {
        CookieService service = new CookieService();

        String filePath = getResourcePath("tie_cookie_log.csv");

        List<String> result = service.findMostActiveCookies(filePath, "2018-12-09");

        assertEquals(2, result.size());
        assertTrue(result.contains("A"));
        assertTrue(result.contains("B"));
    }

    @Test
    void shouldReturnEmptyListWhenNoCookiesForDate() {
        CookieService service = new CookieService();

        String filePath = getResourcePath("cookie_log.csv");

        List<String> result = service.findMostActiveCookies(filePath, "2018-12-10");

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldThrowExceptionForInvalidCsvRow() {
        CookieService service = new CookieService();

        String filePath = getResourcePath("invalid_cookie_log.csv");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
            service.findMostActiveCookies(filePath, "2018-12-09")
        );

        assertTrue(exception.getMessage().contains("Invalid CSV row"));
    }

    @Test
    void shouldThrowExceptionForMissingFile() {
        CookieService service = new CookieService();

        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
            service.findMostActiveCookies("non_existent_file.csv", "2018-12-09")
        );

        assertTrue(exception.getMessage().contains("Error reading file"));
    }

}