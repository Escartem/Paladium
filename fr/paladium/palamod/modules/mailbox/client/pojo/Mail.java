package fr.paladium.palamod.modules.mailbox.client.pojo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;

public class Mail {
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
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Mail))
      return false; 
    Mail other = (Mail)o;
    if (!other.canEqual(this))
      return false; 
    if (isRead() != other.isRead())
      return false; 
    if (isPaladium() != other.isPaladium())
      return false; 
    if (isEveryone() != other.isEveryone())
      return false; 
    if (isDeleted() != other.isDeleted())
      return false; 
    Object this$id = getId(), other$id = other.getId();
    if ((this$id == null) ? (other$id != null) : !this$id.equals(other$id))
      return false; 
    Object this$object = getObject(), other$object = other.getObject();
    if ((this$object == null) ? (other$object != null) : !this$object.equals(other$object))
      return false; 
    Object this$content = getContent(), other$content = other.getContent();
    if ((this$content == null) ? (other$content != null) : !this$content.equals(other$content))
      return false; 
    Object this$recipientUUID = getRecipientUUID(), other$recipientUUID = other.getRecipientUUID();
    if ((this$recipientUUID == null) ? (other$recipientUUID != null) : !this$recipientUUID.equals(other$recipientUUID))
      return false; 
    Object this$recipientName = getRecipientName(), other$recipientName = other.getRecipientName();
    if ((this$recipientName == null) ? (other$recipientName != null) : !this$recipientName.equals(other$recipientName))
      return false; 
    Object this$senderUUID = getSenderUUID(), other$senderUUID = other.getSenderUUID();
    if ((this$senderUUID == null) ? (other$senderUUID != null) : !this$senderUUID.equals(other$senderUUID))
      return false; 
    Object this$senderName = getSenderName(), other$senderName = other.getSenderName();
    if ((this$senderName == null) ? (other$senderName != null) : !this$senderName.equals(other$senderName))
      return false; 
    Object<String> this$encodedItems = (Object<String>)getEncodedItems(), other$encodedItems = (Object<String>)other.getEncodedItems();
    if ((this$encodedItems == null) ? (other$encodedItems != null) : !this$encodedItems.equals(other$encodedItems))
      return false; 
    Object this$inviteCommand = getInviteCommand(), other$inviteCommand = other.getInviteCommand();
    if ((this$inviteCommand == null) ? (other$inviteCommand != null) : !this$inviteCommand.equals(other$inviteCommand))
      return false; 
    Object this$mailType = getMailType(), other$mailType = other.getMailType();
    if ((this$mailType == null) ? (other$mailType != null) : !this$mailType.equals(other$mailType))
      return false; 
    Object this$mailSide = getMailSide(), other$mailSide = other.getMailSide();
    if ((this$mailSide == null) ? (other$mailSide != null) : !this$mailSide.equals(other$mailSide))
      return false; 
    Object this$date = getDate(), other$date = other.getDate();
    if ((this$date == null) ? (other$date != null) : !this$date.equals(other$date))
      return false; 
    Object this$expireDate = getExpireDate(), other$expireDate = other.getExpireDate();
    return !((this$expireDate == null) ? (other$expireDate != null) : !this$expireDate.equals(other$expireDate));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof Mail;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + (isRead() ? 79 : 97);
    result = result * 59 + (isPaladium() ? 79 : 97);
    result = result * 59 + (isEveryone() ? 79 : 97);
    result = result * 59 + (isDeleted() ? 79 : 97);
    Object $id = getId();
    result = result * 59 + (($id == null) ? 43 : $id.hashCode());
    Object $object = getObject();
    result = result * 59 + (($object == null) ? 43 : $object.hashCode());
    Object $content = getContent();
    result = result * 59 + (($content == null) ? 43 : $content.hashCode());
    Object $recipientUUID = getRecipientUUID();
    result = result * 59 + (($recipientUUID == null) ? 43 : $recipientUUID.hashCode());
    Object $recipientName = getRecipientName();
    result = result * 59 + (($recipientName == null) ? 43 : $recipientName.hashCode());
    Object $senderUUID = getSenderUUID();
    result = result * 59 + (($senderUUID == null) ? 43 : $senderUUID.hashCode());
    Object $senderName = getSenderName();
    result = result * 59 + (($senderName == null) ? 43 : $senderName.hashCode());
    Object<String> $encodedItems = (Object<String>)getEncodedItems();
    result = result * 59 + (($encodedItems == null) ? 43 : $encodedItems.hashCode());
    Object $inviteCommand = getInviteCommand();
    result = result * 59 + (($inviteCommand == null) ? 43 : $inviteCommand.hashCode());
    Object $mailType = getMailType();
    result = result * 59 + (($mailType == null) ? 43 : $mailType.hashCode());
    Object $mailSide = getMailSide();
    result = result * 59 + (($mailSide == null) ? 43 : $mailSide.hashCode());
    Object $date = getDate();
    result = result * 59 + (($date == null) ? 43 : $date.hashCode());
    Object $expireDate = getExpireDate();
    return result * 59 + (($expireDate == null) ? 43 : $expireDate.hashCode());
  }
  
  public String toString() {
    return "Mail(id=" + getId() + ", object=" + getObject() + ", content=" + getContent() + ", recipientUUID=" + getRecipientUUID() + ", recipientName=" + getRecipientName() + ", senderUUID=" + getSenderUUID() + ", senderName=" + getSenderName() + ", encodedItems=" + getEncodedItems() + ", inviteCommand=" + getInviteCommand() + ", read=" + isRead() + ", mailType=" + getMailType() + ", mailSide=" + getMailSide() + ", date=" + getDate() + ", expireDate=" + getExpireDate() + ", paladium=" + isPaladium() + ", everyone=" + isEveryone() + ", deleted=" + isDeleted() + ")";
  }
  
  public static MailBuilder builder() {
    return new MailBuilder();
  }
  
  public static class MailBuilder {
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
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setObject(String object) {
    this.object = object;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public void setRecipientUUID(String recipientUUID) {
    this.recipientUUID = recipientUUID;
  }
  
  public void setRecipientName(String recipientName) {
    this.recipientName = recipientName;
  }
  
  public void setSenderUUID(String senderUUID) {
    this.senderUUID = senderUUID;
  }
  
  public void setSenderName(String senderName) {
    this.senderName = senderName;
  }
  
  public void setEncodedItems(ArrayList<String> encodedItems) {
    this.encodedItems = encodedItems;
  }
  
  public void setInviteCommand(String inviteCommand) {
    this.inviteCommand = inviteCommand;
  }
  
  public void setRead(boolean read) {
    this.read = read;
  }
  
  public void setMailType(EnumMailType mailType) {
    this.mailType = mailType;
  }
  
  public void setMailSide(EnumMailSide mailSide) {
    this.mailSide = mailSide;
  }
  
  public void setDate(String date) {
    this.date = date;
  }
  
  public void setExpireDate(String expireDate) {
    this.expireDate = expireDate;
  }
  
  public void setPaladium(boolean paladium) {
    this.paladium = paladium;
  }
  
  public void setEveryone(boolean everyone) {
    this.everyone = everyone;
  }
  
  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
  
  public Mail(String id, String object, String content, String recipientUUID, String recipientName, String senderUUID, String senderName, ArrayList<String> encodedItems, String inviteCommand, boolean read, EnumMailType mailType, EnumMailSide mailSide, String date, String expireDate, boolean paladium, boolean everyone, boolean deleted) {
    this.id = id;
    this.object = object;
    this.content = content;
    this.recipientUUID = recipientUUID;
    this.recipientName = recipientName;
    this.senderUUID = senderUUID;
    this.senderName = senderName;
    this.encodedItems = encodedItems;
    this.inviteCommand = inviteCommand;
    this.read = read;
    this.mailType = mailType;
    this.mailSide = mailSide;
    this.date = date;
    this.expireDate = expireDate;
    this.paladium = paladium;
    this.everyone = everyone;
    this.deleted = deleted;
  }
  
  public Mail() {}
  
  public String getId() {
    return this.id;
  }
  
  public String getObject() {
    return this.object;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public String getRecipientUUID() {
    return this.recipientUUID;
  }
  
  public String getRecipientName() {
    return this.recipientName;
  }
  
  public String getSenderUUID() {
    return this.senderUUID;
  }
  
  public String getSenderName() {
    return this.senderName;
  }
  
  public ArrayList<String> getEncodedItems() {
    return this.encodedItems;
  }
  
  public String getInviteCommand() {
    return this.inviteCommand;
  }
  
  public boolean isRead() {
    return this.read;
  }
  
  public EnumMailType getMailType() {
    return this.mailType;
  }
  
  public EnumMailSide getMailSide() {
    return this.mailSide;
  }
  
  public String getDate() {
    return this.date;
  }
  
  public String getExpireDate() {
    return this.expireDate;
  }
  
  public boolean isPaladium() {
    return this.paladium;
  }
  
  public boolean isEveryone() {
    return this.everyone;
  }
  
  public boolean isDeleted() {
    return this.deleted;
  }
  
  public static String serialize(Mail mail) {
    JsonObject json = new JsonObject();
    json.addProperty("id", mail.id);
    json.addProperty("object", mail.object);
    json.addProperty("content", mail.content);
    json.addProperty("recipientUUID", mail.recipientUUID);
    json.addProperty("recipientName", mail.recipientName);
    json.addProperty("senderUUID", mail.senderUUID);
    json.addProperty("senderName", mail.senderName);
    String toSplit = "";
    for (String str : mail.encodedItems)
      toSplit = toSplit + str + ";"; 
    json.addProperty("encodedItems", toSplit);
    json.addProperty("inviteCommand", mail.inviteCommand);
    json.addProperty("read", Boolean.valueOf(mail.read));
    json.addProperty("mailType", mail.mailType.toString());
    json.addProperty("mailSide", mail.mailSide.toString());
    json.addProperty("date", mail.date);
    json.addProperty("expireDate", mail.expireDate);
    json.addProperty("everyone", Boolean.valueOf(mail.everyone));
    json.addProperty("paladium", Boolean.valueOf(mail.paladium));
    json.addProperty("deleted", Boolean.valueOf(mail.deleted));
    return json.toString();
  }
  
  public static Mail deserialize(String json) {
    JsonObject obj = (new JsonParser()).parse(json).getAsJsonObject();
    String id = obj.get("id").getAsString();
    String object = obj.get("object").getAsString();
    String content = obj.get("content").getAsString();
    String recipientUUID = obj.get("recipientUUID").getAsString();
    String recipientName = obj.get("recipientName").getAsString();
    String senderUUID = obj.get("senderUUID").getAsString();
    String senderName = obj.get("senderName").getAsString();
    String[] splitted = obj.get("encodedItems").getAsString().split(";");
    ArrayList<String> encodedItems = new ArrayList<>();
    for (String string : splitted) {
      if (!string.isEmpty())
        encodedItems.add(string); 
    } 
    String inviteCommand = obj.get("inviteCommand").getAsString();
    boolean read = obj.get("read").getAsBoolean();
    EnumMailType mailType = EnumMailType.valueOf(obj.get("mailType").getAsString());
    EnumMailSide mailSide = EnumMailSide.valueOf(obj.get("mailSide").getAsString());
    String date = obj.get("date").getAsString();
    String expireDate = obj.get("expireDate").getAsString();
    boolean everyone = obj.get("everyone").getAsBoolean();
    boolean paladium = obj.get("paladium").getAsBoolean();
    boolean deleted = obj.get("deleted").getAsBoolean();
    return new Mail(id, object, content, recipientUUID, recipientName, senderUUID, senderName, encodedItems, inviteCommand, read, mailType, mailSide, date, expireDate, paladium, everyone, deleted);
  }
  
  public boolean hasItems() {
    return (this.mailType.isHasItems() && !this.encodedItems.isEmpty() && !this.deleted);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\mailbox\client\pojo\Mail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */