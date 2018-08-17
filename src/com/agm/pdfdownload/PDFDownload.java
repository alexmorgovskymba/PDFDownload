package com.agm.pdfdownload;

import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.Properties;

public class PDFDownload {

    public static void main(String[] args) {

        try {

            Properties properties = new Properties();
            InputStream inputStream = null;

            inputStream = new FileInputStream("config.properties");

            properties.load(inputStream);

            String urlPropertyValue = properties.getProperty("url");
            String minPropertyValue = properties.getProperty("min");
            String maxPropertyValue = properties.getProperty("max");
            String numberOfDigitsPropertyValue = properties.getProperty("numberOfDigits");

            String[] urlParts = urlPropertyValue.split("#");
            String urlStart = urlParts[0];
            String urlFinish = urlParts[1];

            String decimalFormat = "";

            for (int i = 0; i < Integer.valueOf(numberOfDigitsPropertyValue); i++) {
                decimalFormat += "0";
            }

            File outputDirectory = new File("pdf");
            if (outputDirectory.exists()) {
                deleteDirectory(outputDirectory);
            }
            outputDirectory.mkdir();
            String fullOutputPathString;

            String urlString;
            URL url;

            String fileName;
            int responseCode;
            InputStream in;
            OutputStream os;
            int byteCount;
            byte[] buffer;

            int countSuccessful = 0;
            int countFailed = 0;

            for (int index = Integer.valueOf(minPropertyValue); index <= Integer.valueOf(maxPropertyValue); index++) {
                try {
                    urlString = urlStart + index + urlFinish;
                    System.out.println("The URL String is " + urlString);
                    responseCode = getResponseCode(urlString);

                    if (responseCode != 200) {
                        System.out.println("Sorry, " + urlString + " is not accessible! Proceeding to next item.");
                        countFailed++;
                        continue;
                    }

                    url = new URL(urlString);
                    fileName = urlString.split("/")[urlString.split("/").length - 1];
                    fullOutputPathString = outputDirectory.getAbsolutePath() + "/" + fileName;
                    in = url.openStream();
                    os = new FileOutputStream(fullOutputPathString);
                    buffer = new byte[1024];
                    System.out.println("[START]: " + fullOutputPathString);
                    while ((byteCount = in.read(buffer)) != -1) {
                        os.write(buffer, 0, byteCount);
                    }
                    System.out.println("[END]: " + fullOutputPathString);
                    countSuccessful++;
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
            System.out.println("There were " + countSuccessful + " successful downloads.");
            System.out.println("There were " + countFailed + " failures.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getResponseCode(String urlString) throws IOException {
        int responseCode = -1;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        responseCode = connection.getResponseCode();
        connection.disconnect();
        return responseCode;
    }

    public static void deleteDirectory(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File f : listFiles) {
                deleteDirectory(f);
            }
        }
        file.delete();
    }

}
