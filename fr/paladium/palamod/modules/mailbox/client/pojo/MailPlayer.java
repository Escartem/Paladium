package fr.paladium.palamod.modules.mailbox.client.pojo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

public class MailPlayer {
  private String playerUUID;
  
  private ArrayList<String> readMails;
  
  private ArrayList<String> consumedMails;
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof MailPlayer))
      return false; 
    MailPlayer other = (MailPlayer)o;
    if (!other.canEqual(this))
      return false; 
    Object this$playerUUID = getPlayerUUID(), other$playerUUID = other.getPlayerUUID();
    if ((this$playerUUID == null) ? (other$playerUUID != null) : !this$playerUUID.equals(other$playerUUID))
      return false; 
    Object<String> this$readMails = (Object<String>)getReadMails(), other$readMails = (Object<String>)other.getReadMails();
    if ((this$readMails == null) ? (other$readMails != null) : !this$readMails.equals(other$readMails))
      return false; 
    Object<String> this$consumedMails = (Object<String>)getConsumedMails(), other$consumedMails = (Object<String>)other.getConsumedMails();
    return !((this$consumedMails == null) ? (other$consumedMails != null) : !this$consumedMails.equals(other$consumedMails));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof MailPlayer;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $playerUUID = getPlayerUUID();
    result = result * 59 + (($playerUUID == null) ? 43 : $playerUUID.hashCode());
    Object<String> $readMails = (Object<String>)getReadMails();
    result = result * 59 + (($readMails == null) ? 43 : $readMails.hashCode());
    Object<String> $consumedMails = (Object<String>)getConsumedMails();
    return result * 59 + (($consumedMails == null) ? 43 : $consumedMails.hashCode());
  }
  
  public String toString() {
    return "MailPlayer(playerUUID=" + getPlayerUUID() + ", readMails=" + getReadMails() + ", consumedMails=" + getConsumedMails() + ")";
  }
  
  public static MailPlayerBuilder builder() {
    return new MailPlayerBuilder();
  }
  
  public static class MailPlayerBuilder {
    private String playerUUID;
    
    private ArrayList<String> readMails;
    
    private ArrayList<String> consumedMails;
    
    public MailPlayerBuilder playerUUID(String playerUUID) {
      this.playerUUID = playerUUID;
      return this;
    }
    
    public MailPlayerBuilder readMails(ArrayList<String> readMails) {
      this.readMails = readMails;
      return this;
    }
    
    public MailPlayerBuilder consumedMails(ArrayList<String> consumedMails) {
      this.consumedMails = consumedMails;
      return this;
    }
    
    public MailPlayer build() {
      return new MailPlayer(this.playerUUID, this.readMails, this.consumedMails);
    }
    
    public String toString() {
      return "MailPlayer.MailPlayerBuilder(playerUUID=" + this.playerUUID + ", readMails=" + this.readMails + ", consumedMails=" + this.consumedMails + ")";
    }
  }
  
  public void setPlayerUUID(String playerUUID) {
    this.playerUUID = playerUUID;
  }
  
  public void setReadMails(ArrayList<String> readMails) {
    this.readMails = readMails;
  }
  
  public void setConsumedMails(ArrayList<String> consumedMails) {
    this.consumedMails = consumedMails;
  }
  
  public MailPlayer(String playerUUID, ArrayList<String> readMails, ArrayList<String> consumedMails) {
    this.playerUUID = playerUUID;
    this.readMails = readMails;
    this.consumedMails = consumedMails;
  }
  
  public MailPlayer() {}
  
  public String getPlayerUUID() {
    return this.playerUUID;
  }
  
  public ArrayList<String> getReadMails() {
    return this.readMails;
  }
  
  public ArrayList<String> getConsumedMails() {
    return this.consumedMails;
  }
  
  public static String serialize(MailPlayer mail) {
    JsonObject json = new JsonObject();
    json.addProperty("playerUUID", mail.playerUUID);
    String toSplit = "";
    for (String str : mail.readMails)
      toSplit = toSplit + str + ";"; 
    json.addProperty("readMails", toSplit);
    toSplit = "";
    for (String str : mail.consumedMails)
      toSplit = toSplit + str + ";"; 
    json.addProperty("consumedMails", toSplit);
    return json.toString();
  }
  
  public static MailPlayer deserialize(String json) {
    JsonObject obj = (new JsonParser()).parse(json).getAsJsonObject();
    String playerUUID = obj.get("playerUUID").getAsString();
    String[] splitted = obj.get("readMails").getAsString().split(";");
    ArrayList<String> readMails = new ArrayList<>();
    for (String string : splitted) {
      if (!string.isEmpty())
        readMails.add(string); 
    } 
    String[] splitted2 = obj.get("consumedMails").getAsString().split(";");
    ArrayList<String> consumedMails = new ArrayList<>();
    for (String string : splitted2) {
      if (!string.isEmpty())
        consumedMails.add(string); 
    } 
    return new MailPlayer(playerUUID, readMails, consumedMails);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\client\pojo\MailPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */