package fr.paladium.palaforgeutils.lib.player;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import fr.paladium.palaforgeutils.lib.config.PalaForgeConfigManager;
import fr.paladium.palaforgeutils.lib.env.ForgeEnv;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class OfflinePlayer {
  public String toString() {
    return "OfflinePlayer(name=" + getName() + ", uuid=" + getUuid() + ", uuidString=" + getUuidString() + ", data=" + getData() + ", player=" + getPlayer() + ")";
  }
  
  private static final Cache<String, OfflinePlayer> CACHE_UUID = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  private static final Cache<String, OfflinePlayer> CACHE_NAME = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES).build();
  
  private final String name;
  
  private final UUID uuid;
  
  private final String uuidString;
  
  private PaladiumData data;
  
  public String getName() {
    return this.name;
  }
  
  public UUID getUuid() {
    return this.uuid;
  }
  
  public String getUuidString() {
    return this.uuidString;
  }
  
  public PaladiumData getData() {
    return this.data;
  }
  
  private transient Optional<EntityPlayer> player = Optional.empty();
  
  public Optional<EntityPlayer> getPlayer() {
    return this.player;
  }
  
  private OfflinePlayer(@NonNull String name, @NonNull UUID uuid, EntityPlayer player, PaladiumData data) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    this.name = name;
    this.uuid = uuid;
    this.uuidString = UUIDUtils.toString(uuid);
    this.player = Optional.ofNullable(player);
    this.data = data;
  }
  
  @NonNull
  public static OfflinePlayer of(@NonNull UUID uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return new OfflinePlayer(UUIDUtils.toString(uuid), uuid, null, null);
  }
  
  @NonNull
  public static OfflinePlayer of(@NonNull String name, @NonNull UUID uuid) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return new OfflinePlayer(name, uuid, null, null);
  }
  
  @NonNull
  public static OfflinePlayer of(@NonNull String name, @NonNull UUID uuid, PaladiumData data) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    return new OfflinePlayer(name, uuid, null, data);
  }
  
  @NonNull
  public static OfflinePlayer of(@NonNull GameProfile profile) {
    if (profile == null)
      throw new NullPointerException("profile is marked non-null but is null"); 
    return new OfflinePlayer(profile.getName(), profile.getId(), null, null);
  }
  
  @NonNull
  public static OfflinePlayer of(@NonNull GameProfile profile, PaladiumData data) {
    if (profile == null)
      throw new NullPointerException("profile is marked non-null but is null"); 
    return new OfflinePlayer(profile.getName(), profile.getId(), null, data);
  }
  
  @NonNull
  public static OfflinePlayer of(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return new OfflinePlayer(player.func_70005_c_(), player.func_110124_au(), player, null);
  }
  
  @NonNull
  public static OfflinePlayer of(@NonNull EntityPlayer player, PaladiumData data) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    return new OfflinePlayer(player.func_70005_c_(), player.func_110124_au(), player, data);
  }
  
  @NonNull
  public static CompletableFuture<OfflinePlayer> load(@NonNull EntityPlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    OfflinePlayer cachedPlayer = fromCache(player.func_110124_au());
    if (cachedPlayer != null)
      return CompletableFuture.completedFuture(cachedPlayer.update()); 
    CompletableFuture<OfflinePlayer> future = new CompletableFuture<>();
    PaladiumData.load(player.func_110124_au()).whenComplete((data, e) -> {
          if (e != null) {
            future.completeExceptionally(e);
            return;
          } 
          future.complete(cache(of(player, data)));
        });
    return future;
  }
  
  @NonNull
  public static CompletableFuture<OfflinePlayer> load(@NonNull String name) {
    if (name == null)
      throw new NullPointerException("name is marked non-null but is null"); 
    OfflinePlayer cachedPlayer = fromCache(name);
    if (cachedPlayer != null)
      return CompletableFuture.completedFuture(cachedPlayer.update()); 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT && (Minecraft.func_71410_x()).field_71441_e != null) {
      EntityPlayer player = (Minecraft.func_71410_x()).field_71441_e.func_72924_a(name);
      if (player != null)
        return CompletableFuture.completedFuture(cache(of(player))); 
    } 
    CompletableFuture<OfflinePlayer> future = new CompletableFuture<>();
    (new Thread(() -> {
          if (FMLCommonHandler.instance().getSide() == Side.SERVER && MinecraftServer.func_71276_C().func_71203_ab() != null) {
            EntityPlayerMP entityPlayerMP = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(name);
            if (entityPlayerMP != null) {
              PaladiumData.load(entityPlayerMP.func_110124_au()).whenComplete(());
              return;
            } 
          } 
          BufferedReader reader = null;
          try {
            reader = new BufferedReader(new InputStreamReader((new URL("https://api.mojang.com/users/profiles/minecraft/" + name)).openStream()));
            JsonObject object = (JsonObject)(new JsonParser()).parse(reader);
            String uuidString = object.get("id").toString().replace("\"", "");
            String parsedName = object.get("name").toString().replace("\"", "");
            UUID uuid = UUIDUtils.from(uuidString);
            PaladiumData.load(uuid).whenComplete(());
            reader.close();
            return;
          } catch (Exception e) {
            future.completeExceptionally(e);
            if (reader != null)
              try {
                reader.close();
              } catch (Exception exception) {} 
            return;
          } 
        }"OfflinePlayerLoader/Name")).start();
    return future;
  }
  
  @NonNull
  public static CompletableFuture<OfflinePlayer> load(@NonNull UUID uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    OfflinePlayer cachedPlayer = fromCache(uuid);
    if (cachedPlayer != null)
      return CompletableFuture.completedFuture(cachedPlayer.update()); 
    if (FMLCommonHandler.instance().getSide() == Side.CLIENT && (Minecraft.func_71410_x()).field_71441_e != null) {
      EntityPlayer player = (Minecraft.func_71410_x()).field_71441_e.func_152378_a(uuid);
      if (player != null)
        return CompletableFuture.completedFuture(cache(of(player))); 
    } 
    CompletableFuture<OfflinePlayer> future = new CompletableFuture<>();
    (new Thread(() -> {
          if (FMLCommonHandler.instance().getSide() == Side.SERVER && MinecraftServer.func_71276_C() != null)
            for (WorldServer worldServer : (MinecraftServer.func_71276_C()).field_71305_c) {
              EntityPlayer player = worldServer.func_152378_a(uuid);
              if (player != null) {
                PaladiumData.load(player.func_110124_au()).whenComplete(());
                return;
              } 
            }  
          BufferedReader reader = null;
          try {
            reader = new BufferedReader(new InputStreamReader((new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + UUIDUtils.toString(uuid).replace("-", ""))).openStream()));
            String name = ((JsonObject)(new JsonParser()).parse(reader)).get("name").toString().replace("\"", "");
            PaladiumData.load(uuid).whenComplete(());
            reader.close();
            return;
          } catch (Exception e) {
            future.completeExceptionally(e);
            if (reader != null)
              try {
                reader.close();
              } catch (Exception exception) {} 
            return;
          } 
        }"OfflinePlayerLoader/UUID")).start();
    return future;
  }
  
  @NonNull
  private static OfflinePlayer cache(@NonNull OfflinePlayer player) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    CACHE_UUID.put(player.getUuidString(), player);
    CACHE_NAME.put(player.getName().toLowerCase(), player);
    return player;
  }
  
  private static OfflinePlayer fromCache(@NonNull UUID uuid) {
    if (uuid == null)
      throw new NullPointerException("uuid is marked non-null but is null"); 
    OfflinePlayer player = (OfflinePlayer)CACHE_UUID.getIfPresent(UUIDUtils.toString(uuid));
    if (player != null)
      return player; 
    return null;
  }
  
  private static OfflinePlayer fromCache(@NonNull String username) {
    if (username == null)
      throw new NullPointerException("username is marked non-null but is null"); 
    OfflinePlayer player = (OfflinePlayer)CACHE_NAME.getIfPresent(username.toLowerCase());
    if (player != null)
      return player; 
    return null;
  }
  
  @NonNull
  public OfflinePlayer update() {
    List<World> worlds = new ArrayList<>();
    if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
      worlds.addAll((Collection)Arrays.asList((Object[])(MinecraftServer.func_71276_C()).field_71305_c));
    } else {
      worlds.add((Minecraft.func_71410_x()).field_71441_e);
    } 
    for (World world : worlds) {
      EntityPlayer player = world.func_152378_a(this.uuid);
      if (player != null) {
        this.player = Optional.of(player);
        break;
      } 
    } 
    if (this.player == null)
      this.player = Optional.empty(); 
    return this;
  }
  
  public boolean isOnline() {
    return (this.player != null && this.player.isPresent() && ((EntityPlayer)this.player.get()).func_70089_S() && ((EntityPlayer)this.player.get()).field_70170_p != null && ((EntityPlayer)this.player.get()).field_70170_p.func_73045_a(((EntityPlayer)this.player.get()).func_145782_y()) != null);
  }
  
  public boolean isOnline(@NonNull World world) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    return (world.func_152378_a(this.uuid) != null);
  }
  
  @NonNull
  public Optional<EntityPlayer> getPlayer(@NonNull World world) {
    if (world == null)
      throw new NullPointerException("world is marked non-null but is null"); 
    EntityPlayer entityPlayer = world.func_152378_a(this.uuid);
    if (entityPlayer != null) {
      this.player = Optional.of(entityPlayer);
    } else {
      this.player = Optional.empty();
    } 
    return this.player;
  }
  
  @NonNull
  public CompletableFuture<OfflinePlayer> loadData() {
    if (this.data != null)
      return CompletableFuture.completedFuture(this); 
    CompletableFuture<OfflinePlayer> future = new CompletableFuture<>();
    PaladiumData.load(this.uuid).whenComplete((data, e) -> {
          if (e != null)
            return; 
          this.data = data;
          future.complete(this);
        });
    return future;
  }
  
  public boolean hasData() {
    return (this.data != null);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.uuidString });
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (obj == null || getClass() != obj.getClass())
      return false; 
    OfflinePlayer other = (OfflinePlayer)obj;
    return Objects.equals(this.uuidString, other.uuidString);
  }
  
  public static final class PaladiumData {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palaforgeutils\lib\player\OfflinePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */