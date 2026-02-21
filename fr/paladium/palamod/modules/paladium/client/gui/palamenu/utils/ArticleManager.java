package fr.paladium.palamod.modules.paladium.client.gui.palamenu.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

public class ArticleManager {
  public static void getArticle(String requestUrl, Consumer<Article> callback) {
    (new Thread(() -> {
          try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
              StringBuilder response = new StringBuilder();
              String responseLine = null;
              while ((responseLine = br.readLine()) != null)
                response.append(responseLine.trim()); 
              JsonObject result = (new JsonParser()).parse(response.toString()).getAsJsonObject().get("posts").getAsJsonArray().get(0).getAsJsonObject();
              callback.accept(new Article(result.get("feature_image").getAsString(), result.get("title").getAsString(), result.get("excerpt").getAsString(), "https://paladium-pvp.fr/articles/" + result.get("slug").getAsString()));
            } 
          } catch (Exception e) {
            e.printStackTrace();
          } 
          callback.accept(null);
        })).start();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\client\gui\palamen\\utils\ArticleManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */