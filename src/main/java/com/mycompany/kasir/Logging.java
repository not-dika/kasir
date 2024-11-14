/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kasir;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logging {

    private static final String LOG_FOLDER = System.getProperty("user.home") + File.separator + ".pos";
    
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");

    private Logging() {
    }

    public static void log(String message) {
        try {
            File logFolder = new File(LOG_FOLDER);
            if (!logFolder.exists()) {
                logFolder.mkdirs();
            }

            String logFileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt";
            File logFile = new File(logFolder, logFileName);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
                String timeStamp = TIME_FORMAT.format(new Date());
                writer.write(timeStamp + " - " + message);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void logException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        log(sw.toString());
    }
}
