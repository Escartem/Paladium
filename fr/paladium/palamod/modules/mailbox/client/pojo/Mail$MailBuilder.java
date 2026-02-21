package fr.paladium.palamod.modules.mailbox.client.pojo;

import java.util.ArrayList;

public class MailBuilder {
  private String id;
  
  private String object;
  
  private String content;
  
  private String recipientUUID;
  
  private String recipientName;
  
  private String senderUUID;
  
  private String senderName;
  
  private ArrayList<String> encodedItems;
  
  private String inviteCommand;
  
  private boolean read;
  
  private EnumMailType mailType;
  
  private EnumMailSide mailSide;
  
  private String date;
  
  private String expireDate;
  
  private boolean paladium;
  
  private boolean everyone;
  
  private boolean deleted;
  
  public MailBuilder id(String id) {
    this.id = id;
    return this;
  }
  
  public MailBuilder object(String object) {
    this.object = object;
    return this;
  }
  
  public MailBuilder content(String content) {
    this.content = content;
    return this;
  }
  
  public MailBuilder recipientUUID(String recipientUUID) {
    this.recipientUUID = recipientUUID;
    return this;
  }
  
  public MailBuilder recipientName(String recipientName) {
    this.recipientName = recipientName;
    return this;
  }
  
  public MailBuilder senderUUID(String senderUUID) {
    this.senderUUID = senderUUID;
    return this;
  }
  
  public MailBuilder senderName(String senderName) {
    this.senderName = senderName;
    return this;
  }
  
  public MailBuilder encodedItems(ArrayList<String> encodedItems) {
    this.encodedItems = encodedItems;
    return this;
  }
  
  public MailBuilder inviteCommand(String inviteCommand) {
    this.inviteCommand = inviteCommand;
    return this;
  }
  
  public MailBuilder read(boolean read) {
    this.read = read;
    return this;
  }
  
  public MailBuilder mailType(EnumMailType mailType) {
    this.mailType = mailType;
    return this;
  }
  
  public MailBuilder mailSide(EnumMailSide mailSide) {
    this.mailSide = mailSide;
    return this;
  }
  
  public MailBuilder date(String date) {
    this.date = date;
    return this;
  }
  
  public MailBuilder expireDate(String expireDate) {
    this.expireDate = expireDate;
    return this;
  }
  
  public MailBuilder paladium(boolean paladium) {
    this.paladium = paladium;
    return this;
  }
  
  public MailBuilder everyone(boolean everyone) {
    this.everyone = everyone;
    return this;
  }
  
  public MailBuilder deleted(boolean deleted) {
    this.deleted = deleted;
    return this;
  }
  
  public Mail build() {
    return new Mail(this.id, this.object, this.content, this.recipientUUID, this.recipientName, this.senderUUID, this.senderName, this.encodedItems, this.inviteCommand, this.read, this.mailType, this.mailSide, this.date, this.expireDate, this.paladium, this.everyone, this.deleted);
  }
  
  public String toString() {
    return "Mail.MailBuilder(id=" + this.id + ", object=" + this.object + ", content=" + this.content + ", recipientUUID=" + this.recipientUUID + ", recipientName=" + this.recipientName + ", senderUUID=" + this.senderUUID + ", senderName=" + this.senderName + ", encodedItems=" + this.encodedItems + ", inviteCommand=" + this.inviteCommand + ", read=" + this.read + ", mailType=" + this.mailType + ", mailSide=" + this.mailSide + ", date=" + this.date + ", expireDate=" + this.expireDate + ", paladium=" + this.paladium + ", everyone=" + this.everyone + ", deleted=" + this.deleted + ")";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\client\pojo\Mail$MailBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */