package com.quantcast.cookieanalyzer;

public class Main {

    public static void main(String[] args) {
        
        String file = null;
        String date = null;

        for (int i = 0; i < args.length; i++) {
            if ("-f".equals(args[i]) && i + 1 < args.length) {
                file = args[i + 1];
            } else if ("-d".equals(args[i]) && i + 1 < args.length) {
                date = args[i + 1];
            }
        }

        // Validate required CLI arguments
        if (file == null || date == null) {
            System.err.println("Usage: -f <filename> -d <date>");
            System.exit(1);
        }

        CookieService cookieService = new CookieService();
        
        try {
            cookieService.readFile(file);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

}

// $ ./[command] -f cookie_log.csv -d 2018-12-09