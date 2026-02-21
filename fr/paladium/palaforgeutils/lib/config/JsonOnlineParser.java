package fr.paladium.palaforgeutils.lib.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class JsonOnlineParser {
  public static JsonObject parse(String urlString) {
    try {
      URL url = new URL(urlString);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/json");
      connection.setDoOutput(true);
      try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null)
          response.append(responseLine.trim()); 
        return (new JsonParser()).parse(response.toString()).getAsJsonObject();
      } 
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\config\JsonOnlineParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */