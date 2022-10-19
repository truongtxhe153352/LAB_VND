package edu.java.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpClientExample {
    public static void main(String[] args) throws IOException {

       ExecutorService executor = Executors.newSingleThreadExecutor();
       executor.submit(new Runnable() {
           @Override
           public void run() {
               URL obj = null;
               try {
                   obj = new URL("http://localhost:8080/synch");
               } catch (MalformedURLException e) {
                   throw new RuntimeException(e);
               }
               HttpURLConnection con = null;
               try {
                   con = (HttpURLConnection)obj.openConnection();
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }

               try {
                   con.setRequestMethod("GET");
               } catch (ProtocolException e) {
                   throw new RuntimeException(e);
               }
               con.addRequestProperty("Accept-Encoding", "gzip");

               BufferedReader reader = null;
               try {
                   reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
               String line = "";
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(line);
            }
           }
       });

//        try {
//            URL url = new URL("http://localhost:8080/html");
//            HttpURLConnection con = (HttpURLConnection) url.openConnection();
//            con.setRequestMethod("GET");
//            con.addRequestProperty("Accept-Encoding", "gzip");
//
//            int responseCode = con.getResponseCode();
//            System.out.println("Response Code: " + responseCode);
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            if (reader != null) reader.close();
//        } finally {
//
//        }
    }
}
