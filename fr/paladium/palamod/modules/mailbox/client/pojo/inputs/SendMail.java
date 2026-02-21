package fr.paladium.palamod.modules.mailbox.client.pojo.inputs;

import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailSide;
import fr.paladium.palamod.modules.mailbox.client.pojo.EnumMailType;
import fr.paladium.palamod.modules.mailbox.client.pojo.Mail;
import java.util.ArrayList;

public class SendMail {
  private String object;
  
  private String content;
  
  private String recipientName;
  
  private String recipientUUID;
  
  private String senderName;
  
  private String senderUUID;
  
  private ArrayList<String> encodedItems;
  
  private String inviteCommand;
  
  private EnumMailType mailType;
  
  private EnumMailSide mailSide;
  
  private int expireMinutes;
  
  private boolean paladium;
  
  private boolean everyone;
  
  public SendMail(String object, String content, String recipientName, String recipientUUID, String senderName, String senderUUID, ArrayList<String> encodedItems, String inviteCommand, EnumMailType mailType, EnumMailSide mailSide, int expireMinutes, boolean paladium, boolean everyone) {
    this.object = object;
    this.content = content;
    this.recipientName = recipientName;
    this.recipientUUID = recipientUUID;
    this.senderName = senderName;
    this.senderUUID = senderUUID;
    this.encodedItems = encodedItems;
    this.inviteCommand = inviteCommand;
    this.mailType = mailType;
    this.mailSide = mailSide;
    this.expireMinutes = expireMinutes;
    this.paladium = paladium;
    this.everyone = everyone;
  }
  
  public void setObject(String object) {
    this.object = object;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }
  
  public void setRecipientUUID(String recipientUUID) {
    this.recipientUUID = recipientUUID;
  }
  
  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }
  
  public void setSenderUUID(String senderUUID) {
    this.senderUUID = senderUUID;
  }
  
  public void setEncodedItems(ArrayList<String> encodedItems) {
    this.encodedItems = encodedItems;
  }
  
  public void setInviteCommand(String inviteCommand) {
    this.inviteCommand = inviteCommand;
  }
  
  public void setMailType(EnumMailType mailType) {
    this.mailType = mailType;
  }
  
  public void setMailSide(EnumMailSide mailSide) {
    this.mailSide = mailSide;
  }
  
  public void setExpireMinutes(int expireMinutes) {
    this.expireMinutes = expireMinutes;
  }
  
  public void setPaladium(boolean paladium) {
    this.paladium = paladium;
  }
  
  public void setEveryone(boolean everyone) {
    this.everyone = everyone;
  }
  
  public String getObject() {
    return this.object;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public String getRecipientName() {
    return this.recipientName;
  }
  
  public String getRecipientUUID() {
    return this.recipientUUID;
  }
  
  public String getSenderName() {
    return this.senderName;
  }
  
  public String getSenderUUID() {
    return this.senderUUID;
  }
  
  public ArrayList<String> getEncodedItems() {
    return this.encodedItems;
  }
  
  public String getInviteCommand() {
    return this.inviteCommand;
  }
  
  public EnumMailType getMailType() {
    return this.mailType;
  }
  
  public EnumMailSide getMailSide() {
    return this.mailSide;
  }
  
  public int getExpireMinutes() {
    return this.expireMinutes;
  }
  
  public boolean isPaladium() {
    return this.paladium;
  }
  
  public boolean isEveryone() {
    return this.everyone;
  }
  
  public Mail toMail() {
    Mail mail = new Mail();
    mail.setObject(this.object);
    mail.setContent(this.content);
    mail.setRecipientName(this.recipientName);
    mail.setRecipientUUID(this.recipientUUID);
    mail.setSenderName(this.senderName);
    mail.setSenderUUID(this.senderUUID);
    mail.setEncodedItems(this.encodedItems);
    mail.setInviteCommand(this.inviteCommand);
    mail.setMailType(this.mailType);
    mail.setMailSide(this.mailSide);
    mail.setExpireDate("-1");
    mail.setPaladium(this.paladium);
    mail.setEveryone(this.everyone);
    return mail;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\client\pojo\inputs\SendMail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */