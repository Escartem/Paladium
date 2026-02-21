package fr.paladium.palamod.modules.luckyblock.utils.wishcard;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import fr.paladium.palamod.modules.luckyblock.config.WishCardConfig;
import fr.paladium.palamod.util.FastUUID;
import java.util.Date;
import java.util.UUID;
import org.bson.Document;
import org.bson.conversions.Bson;

public class WishCardDataBaseManager {
  private final MongoDatabase db;
  
  private final MongoCollection<Document> playersCollection;
  
  private final MongoCollection<Document> logsCollection;
  
  public WishCardDataBaseManager(WishCardConfig config) {
    String uri;
    if (config.getUsername() != null && !config.getUsername().isEmpty()) {
      uri = "mongodb://" + config.getUsername() + ":" + config.getPassword() + "@" + config.getHost() + "/wish_card";
    } else {
      uri = "mongodb://" + config.getHost() + "/wish_card";
    } 
    MongoClient client = MongoClients.create(uri);
    this.db = client.getDatabase("wish_card");
    this.db.listCollectionNames();
    this.playersCollection = this.db.getCollection("players");
    this.logsCollection = this.db.getCollection("logs");
    System.out.println("[WishCard] Mongo is loaded !");
  }
  
  public InsertOneResult insert(Document dbObjects) {
    return this.playersCollection.insertOne(dbObjects);
  }
  
  public DeleteResult remove(Document query) {
    return this.playersCollection.deleteOne((Bson)query);
  }
  
  public UpdateResult update(Document query, Document dbObject) {
    return this.playersCollection.updateOne((Bson)query, (Bson)dbObject);
  }
  
  public Document find(Document query) {
    return (Document)this.playersCollection.find((Bson)query).first();
  }
  
  public InsertOneResult log(Document document) {
    document.put("_id", FastUUID.toString(UUID.randomUUID()));
    document.put("date", new Date());
    return this.logsCollection.insertOne(document);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\wishcard\WishCardDataBaseManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */