package fr.paladium.palashop.server.shop.dto.item;

public class Subscription implements Comparable<Subscription> {
  public String uuid;
  
  public String itemId;
  
  public String itemName;
  
  public String itemDescription;
  
  public String itemThumbnail;
  
  public long createdDate;
  
  public long nextRenewalDate;
  
  public long lastRenewalDate;
  
  public Status status;
  
  public long updatedDate;
  
  public String toString() {
    return "Subscription(uuid=" + getUuid() + ", itemId=" + getItemId() + ", itemName=" + getItemName() + ", itemDescription=" + getItemDescription() + ", itemThumbnail=" + getItemThumbnail() + ", createdDate=" + getCreatedDate() + ", nextRenewalDate=" + getNextRenewalDate() + ", lastRenewalDate=" + getLastRenewalDate() + ", status=" + getStatus() + ", updatedDate=" + getUpdatedDate() + ")";
  }
  
  public Subscription() {}
  
  public Subscription(String uuid, String itemId, String itemName, String itemDescription, String itemThumbnail, long createdDate, long nextRenewalDate, long lastRenewalDate, Status status, long updatedDate) {
    this.uuid = uuid;
    this.itemId = itemId;
    this.itemName = itemName;
    this.itemDescription = itemDescription;
    this.itemThumbnail = itemThumbnail;
    this.createdDate = createdDate;
    this.nextRenewalDate = nextRenewalDate;
    this.lastRenewalDate = lastRenewalDate;
    this.status = status;
    this.updatedDate = updatedDate;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getItemId() {
    return this.itemId;
  }
  
  public String getItemName() {
    return this.itemName;
  }
  
  public String getItemDescription() {
    return this.itemDescription;
  }
  
  public String getItemThumbnail() {
    return this.itemThumbnail;
  }
  
  public long getCreatedDate() {
    return this.createdDate;
  }
  
  public long getNextRenewalDate() {
    return this.nextRenewalDate;
  }
  
  public long getLastRenewalDate() {
    return this.lastRenewalDate;
  }
  
  public Status getStatus() {
    return this.status;
  }
  
  public long getUpdatedDate() {
    return this.updatedDate;
  }
  
  public int compareTo(Subscription o) {
    return Long.compare(this.nextRenewalDate, o.nextRenewalDate);
  }
  
  public enum Status {
    PENDING, ACTIVE, CANCELED, EXPIRED, NOT_FOUND;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\server\shop\dto\item\Subscription.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */