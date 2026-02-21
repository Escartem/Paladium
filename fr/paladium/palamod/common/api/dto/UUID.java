package fr.paladium.palamod.common.api.dto;

import com.google.gson.annotations.SerializedName;

public class UUID {
  @SerializedName("name")
  String name;
  
  @SerializedName("id")
  String id;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof UUID))
      return false; 
    UUID other = (UUID)o;
    if (!other.canEqual(this))
      return false; 
    Object this$name = getName(), other$name = other.getName();
    if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name))
      return false; 
    Object this$id = getId(), other$id = other.getId();
    return !((this$id == null) ? (other$id != null) : !this$id.equals(other$id));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof UUID;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $name = getName();
    result = result * 59 + (($name == null) ? 43 : $name.hashCode());
    Object $id = getId();
    return result * 59 + (($id == null) ? 43 : $id.hashCode());
  }
  
  public String toString() {
    return "UUID(name=" + getName() + ", id=" + getId() + ")";
  }
  
  UUID(String name, String id) {
    this.name = name;
    this.id = id;
  }
  
  public static UUIDBuilder builder() {
    return new UUIDBuilder();
  }
  
  public static class UUIDBuilder {
    private String name;
    
    private String id;
    
    public UUIDBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public UUIDBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public UUID build() {
      return new UUID(this.name, this.id);
    }
    
    public String toString() {
      return "UUID.UUIDBuilder(name=" + this.name + ", id=" + this.id + ")";
    }
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getId() {
    return this.id;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\dto\UUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */