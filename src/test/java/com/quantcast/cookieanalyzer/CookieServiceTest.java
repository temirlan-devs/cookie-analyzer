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

        // load file from test resources
        URL resource = getClass().getClassLoader().getResource("cookie_log.csv");
        assertNotNull(resource);

        String filePath = getResourcePath("cookie_log.csv");

        List<String> result = service.findMostActiveCookies(filePath, "2018-12-09");

        assertEquals(1, result.size());
        assertEquals("AtY0laUfhglK3lC7", result.get(0));
    }

}