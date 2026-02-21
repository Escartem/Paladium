package fr.paladium.palaconfiguration.server.dto.config.response;

public class ConfigRefreshResponse {
  private long size;
  
  private String lastModifiedDate;
  
  private String name;
  
  private String absolutePath;
  
  public void setSize(long size) {
    this.size = size;
  }
  
  public void setLastModifiedDate(String lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setAbsolutePath(String absolutePath) {
    this.absolutePath = absolutePath;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof ConfigRefreshResponse))
      return false; 
    ConfigRefreshResponse other = (ConfigRefreshResponse)o;
    if (!other.canEqual(this))
      return false; 
    if (getSize() != other.getSize())
      return false; 
    Object this$lastModifiedDate = getLastModifiedDate(), other$lastModifiedDate = other.getLastModifiedDate();
    if ((this$lastModifiedDate == null) ? (other$lastModifiedDate != null) : !this$lastModifiedDate.equals(other$lastModifiedDate))
      return false; 
    Object this$name = getName(), other$name = other.getName();
    if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name))
      return false; 
    Object this$absolutePath = getAbsolutePath(), other$absolutePath = other.getAbsolutePath();
    return !((this$absolutePath == null) ? (other$absolutePath != null) : !this$absolutePath.equals(other$absolutePath));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof ConfigRefreshResponse;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $size = getSize();
    result = result * 59 + (int)($size >>> 32L ^ $size);
    Object $lastModifiedDate = getLastModifiedDate();
    result = result * 59 + (($lastModifiedDate == null) ? 43 : $lastModifiedDate.hashCode());
    Object $name = getName();
    result = result * 59 + (($name == null) ? 43 : $name.hashCode());
    Object $absolutePath = getAbsolutePath();
    return result * 59 + (($absolutePath == null) ? 43 : $absolutePath.hashCode());
  }
  
  public String toString() {
    return "ConfigRefreshResponse(size=" + getSize() + ", lastModifiedDate=" + getLastModifiedDate() + ", name=" + getName() + ", absolutePath=" + getAbsolutePath() + ")";
  }
  
  public ConfigRefreshResponse() {}
  
  public ConfigRefreshResponse(long size, String lastModifiedDate, String name, String absolutePath) {
    this.size = size;
    this.lastModifiedDate = lastModifiedDate;
    this.name = name;
    this.absolutePath = absolutePath;
  }
  
  public long getSize() {
    return this.size;
  }
  
  public String getLastModifiedDate() {
    return this.lastModifiedDate;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getAbsolutePath() {
    return this.absolutePath;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\config\response\ConfigRefreshResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */