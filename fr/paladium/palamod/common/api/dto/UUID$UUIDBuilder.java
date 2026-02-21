package fr.paladium.palamod.common.api.dto;

public class UUIDBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\api\dto\UUID$UUIDBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */