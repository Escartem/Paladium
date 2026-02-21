package fr.paladium.palavote.server.manager;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palavote.PalaVoteMod;
import fr.paladium.palavote.common.data.PalaVotePlayer;
import fr.paladium.palavote.server.config.PalaVoteConfig;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.NonNull;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import org.bson.Document;

public class PalaVoteManager {
  private static final ThreadPoolExecutor EXECUTOR = (ThreadPoolExecutor)Executors.newFixedThreadPool(1);
  
  private static MongoDatabase database;
  
  private static MongoCollection<Document> collection;
  
  public static void init(@NonNull PalaVoteConfig config) {
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    try {
      MongoClient client = MongoClients.create(config.getMongoURI());
      database = client.getDatabase("palavote");
      collection = database.getCollection(config.getId());
    } catch (Exception e) {
      throw new RuntimeException("Failed to connect to the MongoDB database", e);
    } 
    if (database == null)
      throw new RuntimeException("Failed to get the database from the MongoDB client"); 
    if (collection == null)
      throw new RuntimeException("Failed to get the collection from the MongoDB database"); 
    System.out.println("[PalaVote] Loaded votes from config");
    System.out.println("[-] ID: " + config.getId());
    System.out.println("[-] Name: " + config.getName());
    System.out.println("[-] Enabled: " + config.isEnabled());
    System.out.println("[-] Choices: " + String.join(", ", (CharSequence[])Arrays.<PalaVoteConfig.PalaVoteChoice>asList(config.getChoices()).stream().map(choice -> "[" + choice.getName() + "]").toArray(x$0 -> new String[x$0])));
  }
  
  public static boolean vote(@NonNull EntityPlayerMP player, @NonNull String choiceName) {
    if (player == null)
      throw new NullPointerException("player is marked non-null but is null"); 
    if (choiceName == null)
      throw new NullPointerException("choiceName is marked non-null but is null"); 
    PalaVoteConfig config = PalaVoteMod.getServer().getConfig();
    if (!config.isEnabled())
      return false; 
    Optional<PalaVoteConfig.PalaVoteChoice> optionalChoice = config.getChoice(choiceName);
    if (!optionalChoice.isPresent())
      return false; 
    PalaVoteConfig.PalaVoteChoice choice = optionalChoice.get();
    PalaVotePlayer data = PalaVotePlayer.get((Entity)player);
    if (data.hasVoted(config.getId()))
      return false; 
    data.getVotedMap().put(config.getId(), choice.getName());
    String uuid = UUIDUtils.toString((Entity)player);
    EXECUTOR.execute(() -> {
          Document document = (new Document("uuid", uuid)).append("choice", choice.getName()).append("date", Long.valueOf(System.currentTimeMillis()));
          collection.insertOne(document);
        });
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\server\manager\PalaVoteManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */