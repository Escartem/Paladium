package fr.paladium.palaforgeutils.lib.player;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.config.PalaForgeConfigManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;

public final class PaladiumData {
  public String toString() {
    return "OfflinePlayer.PaladiumData(rank=" + getRank() + ", description=" + getDescription() + ", factionName=" + getFactionName() + ", factionRank=" + getFactionRank() + ")";
  }
  
  public PaladiumData(String rank, String description, String factionName, String factionRank) {
    this.rank = rank;
    this.description = description;
    this.factionName = factionName;
    this.factionRank = factionRank;
  }
  
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private static final Cache<String, PaladiumData> CACHE = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  private final String rank;
  
  private final String description;
  
  private final String factionName;
  
  private final String factionRank;
  
  public String getRank() {
    return this.rank;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getFactionName() {
    return this.factionName;
  }
  
  public String getFactionRank() {
    return this.factionRank;
  }
  
  public String getRankImage() {
    if (this.rank == null || this.rank.isEmpty())
      return "https://pictures.paladium-pvp.fr/profile/rank/default.png"; 
    return "https://pictures.paladium-pvp.fr/profile/rank/" + this.rank + ".png";
  }
  
  @NonNull
  public static CompletableFuture<PaladiumData> load(@NonNull UUID uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return load(UUIDUtils.toString(uuid));
  }
  
  @NonNull
  public static CompletableFuture<PaladiumData> load(@NonNull String uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    if (ForgeEnv.isIDE())
      return CompletableFuture.completedFuture(new PaladiumData("developpeur", uuid, uuid, uuid)); 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
      return CompletableFuture.completedFuture(null); 
    if (CACHE.getIfPresent(uuid) != null)
      return CompletableFuture.completedFuture(CACHE.getIfPresent(uuid)); 
    CompletableFuture<PaladiumData> future = new CompletableFuture<>();
    try {
      URL url = new URL("https://api.paladium.games/v1/paladium/player/profile/" + uuid);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/json");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Authorization", "Bearer " + PalaForgeConfigManager.getInstance().getPublicApiToken());
      connection.setDoOutput(true);
      connection.connect();
      int responseCode = connection.getResponseCode();
      if (responseCode != 200) {
        future.complete(null);
        return future;
      } 
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder response = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null)
        response.append(line); 
      reader.close();
      connection.disconnect();
      if (response.toString().isEmpty()) {
        future.complete(null);
        return future;
      } 
      JsonObject jsonObject = (JsonObject)GSON.fromJson(response.toString(), JsonObject.class);
      if (jsonObject == null) {
        future.complete(null);
        return future;
      } 
      String rank = jsonObject.has("rank") ? jsonObject.get("rank").getAsString() : null;
      String description = jsonObject.has("description") ? jsonObject.get("description").getAsString() : null;
      String factionName = jsonObject.has("faction") ? jsonObject.get("faction").getAsString() : null;
      String factionRank = jsonObject.has("factionRank") ? jsonObject.get("factionRank").getAsString() : null;
      PaladiumData paladiumData = new PaladiumData(rank, description, factionName, factionRank);
      CACHE.put(uuid, paladiumData);
      future.complete(paladiumData);
    } catch (Exception e) {
      future.completeExceptionally(e);
    } 
    return future;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\player\OfflinePlayer$PaladiumData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */