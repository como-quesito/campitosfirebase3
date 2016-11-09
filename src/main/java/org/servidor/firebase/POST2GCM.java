package org.servidor.firebase;

/**
 * Created by campitos on 5/28/15.
 */

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class POST2GCM {

    public static void post(String apiKey, Content content){

        try{

            // 1. URL
            //URL url = new URL("https://android.googleapis.com/gcm/send");
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            // 2. Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 3. Especificamos  metodo POST
            conn.setRequestMethod("POST");

            // 4. SAjustamos headerts
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "key="+apiKey);

            conn.setDoOutput(true);

            // 5. Agregamos JSON

            //`5.1
            ObjectMapper mapper = new ObjectMapper();

            // 5.2
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

            // 5.3
            mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
            mapper.writeValue((DataOutput) wr, content);

            // 5.4 Enviaos request
            wr.flush();

            // 5.5 close
            wr.close();

            // 6. Obtenemos respuesta
            int responseCode = conn.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // 7. Imprimimos resulatdos
            System.out.println(response.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}