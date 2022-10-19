package edu.java.web;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AsyncHttpClientExample {
    public static void main(String[] args) throws InterruptedException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        httpclient.start();
       HttpGet request = new HttpGet("http://localhost:8080/synch");
       httpclient.execute(request, new FutureCallback<HttpResponse>() {
           @Override
           public void completed(HttpResponse httpResponse) {
               try {
                   InputStream stream = httpResponse.getEntity().getContent();
                   BufferedReader reader = null;
                   String line = "";
                   while ((line = reader.readLine()) != null) {
                       System.out.println(line);
                   }
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }

           @Override
           public void failed(Exception e) {

           }

           @Override
           public void cancelled() {

           }
       });
       int counter = 1;
       while (counter < 10){
           System.out.println(" main thread is running " + counter);
           Thread.sleep(1000);
           counter++;
       }
    }
}
