package fr.paladium.palamod.modules.mailbox.client.pojo;

import java.util.ArrayList;

public class MailPlayerBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\client\pojo\MailPlayer$MailPlayerBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */