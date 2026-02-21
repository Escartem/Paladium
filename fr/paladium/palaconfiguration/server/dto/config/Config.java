package fr.paladium.palaconfiguration.server.dto.config;

public class Config {
  private String name;
  
  private String absolutePath;
  
  private String content;
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setAbsolutePath(String absolutePath) {
    this.absolutePath = absolutePath;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof Config))
      return false; 
    Config other = (Config)o;
    if (!other.canEqual(this))
      return false; 
    Object this$name = getName(), other$name = other.getName();
    if ((this$name == null) ? (other$name != null) : !this$name.equals(other$name))
      return false; 
    Object this$absolutePath = getAbsolutePath(), other$absolutePath = other.getAbsolutePath();
    if ((this$absolutePath == null) ? (other$absolutePath != null) : !this$absolutePath.equals(other$absolutePath))
      return false; 
    Object this$content = getContent(), other$content = other.getContent();
    return !((this$content == null) ? (other$content != null) : !this$content.equals(other$content));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof Config;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $name = getName();
    result = result * 59 + (($name == null) ? 43 : $name.hashCode());
    Object $absolutePath = getAbsolutePath();
    result = result * 59 + (($absolutePath == null) ? 43 : $absolutePath.hashCode());
    Object $content = getContent();
    return result * 59 + (($content == null) ? 43 : $content.hashCode());
  }
  
  public String toString() {
    return "Config(name=" + getName() + ", absolutePath=" + getAbsolutePath() + ", content=" + getContent() + ")";
  }
  
  public Config() {}
  
  public Config(String name, String absolutePath, String content) {
    this.name = name;
    this.absolutePath = absolutePath;
    this.content = content;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getAbsolutePath() {
    return this.absolutePath;
  }
  
  public String getContent() {
    return this.content;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaconfiguration\server\dto\config\Config.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */