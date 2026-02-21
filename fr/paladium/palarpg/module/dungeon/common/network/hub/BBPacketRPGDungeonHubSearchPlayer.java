package fr.paladium.palarpg.module.dungeon.common.network.hub;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.config.PalaForgeConfigManager;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.scheduler.FMLServerScheduler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketRPGDungeonHubSearchPlayer extends ForgePacket {
  public BBPacketRPGDungeonHubSearchPlayer() {}
  
  public BBPacketRPGDungeonHubSearchPlayer(String name) {
    this.name = name;
  }
  
  private static final Gson GSON = (new GsonBuilder()).create();
  
  private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(4);
  
  @PacketData
  private String name;
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    if (this.name == null || this.name.isEmpty()) {
      reply(new BBPacketDungeonSearchPlayerResult());
      return;
    } 
    EXECUTOR.submit(() -> {
          try {
            URL url = new URL("https://api.paladium.games/v1/paladium/player/search/" + this.name);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + PalaForgeConfigManager.getInstance().getPublicApiToken());
            connection.setDoOutput(true);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
              FMLServerScheduler.getInstance().add(new Runnable[] { () });
              return;
            } 
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null)
              response.append(line); 
            reader.close();
            connection.disconnect();
            if (response.toString().isEmpty()) {
              FMLServerScheduler.getInstance().add(new Runnable[] { () });
              return;
            } 
            List<BBPacketDungeonSearchPlayerResult.BBPacketDungeonSearchPlayerElement> result = (List<BBPacketDungeonSearchPlayerResult.BBPacketDungeonSearchPlayerElement>)GSON.fromJson(response.toString(), List.class);
            if (result == null || result.isEmpty()) {
              FMLServerScheduler.getInstance().add(new Runnable[] { () });
              return;
            } 
            FMLServerScheduler.getInstance().add(new Runnable[] { () });
          } catch (Exception e) {
            e.printStackTrace();
            FMLServerScheduler.getInstance().add(new Runnable[] { () });
          } 
        });
  }
  
  public class BBPacketDungeonSearchPlayerResult {
    private final List<BBPacketDungeonSearchPlayerElement> players;
    
    private BBPacketDungeonSearchPlayerResult() {
      this.players = new ArrayList<>();
    }
    
    private BBPacketDungeonSearchPlayerResult(List<BBPacketDungeonSearchPlayerElement> players) {
      if (players == null)
        throw new NullPointerException("players is marked non-null but is null"); 
      this.players = players;
    }
    
    public List<BBPacketDungeonSearchPlayerElement> getPlayers() {
      return this.players;
    }
    
    public class BBPacketDungeonSearchPlayerElement {
      private final String username;
      
      private final String uuid;
      
      private final String rank;
      
      private final int score;
      
      public BBPacketDungeonSearchPlayerElement(String username, String uuid, String rank, int score) {
        this.username = username;
        this.uuid = uuid;
        this.rank = rank;
        this.score = score;
      }
      
      public String getUsername() {
        return this.username;
      }
      
      public String getUuid() {
        return this.uuid;
      }
      
      public String getRank() {
        return this.rank;
      }
      
      public int getScore() {
        return this.score;
      }
    }
  }
  
  public class BBPacketDungeonSearchPlayerElement {
    private final String username;
    
    private final String uuid;
    
    private final String rank;
    
    private final int score;
    
    public BBPacketDungeonSearchPlayerElement(String username, String uuid, String rank, int score) {
      this.username = username;
      this.uuid = uuid;
      this.rank = rank;
      this.score = score;
    }
    
    public String getUsername() {
      return this.username;
    }
    
    public String getUuid() {
      return this.uuid;
    }
    
    public String getRank() {
      return this.rank;
    }
    
    public int getScore() {
      return this.score;
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\hub\BBPacketRPGDungeonHubSearchPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */