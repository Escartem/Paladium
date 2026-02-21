package fr.paladium.announcement.common.pojo.parsed;

import java.util.ArrayList;
import java.util.List;

public class PaladiumContent {
  private ContentType type;
  
  private String content;
  
  private List<PaladiumContent> children;
  
  public void setType(ContentType type) {
    this.type = type;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public void setChildren(List<PaladiumContent> children) {
    this.children = children;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof PaladiumContent))
      return false; 
    PaladiumContent other = (PaladiumContent)o;
    if (!other.canEqual(this))
      return false; 
    Object this$type = getType(), other$type = other.getType();
    if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
      return false; 
    Object this$content = getContent(), other$content = other.getContent();
    if ((this$content == null) ? (other$content != null) : !this$content.equals(other$content))
      return false; 
    Object<PaladiumContent> this$children = (Object<PaladiumContent>)getChildren(), other$children = (Object<PaladiumContent>)other.getChildren();
    return !((this$children == null) ? (other$children != null) : !this$children.equals(other$children));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof PaladiumContent;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    Object $type = getType();
    result = result * 59 + (($type == null) ? 43 : $type.hashCode());
    Object $content = getContent();
    result = result * 59 + (($content == null) ? 43 : $content.hashCode());
    Object<PaladiumContent> $children = (Object<PaladiumContent>)getChildren();
    return result * 59 + (($children == null) ? 43 : $children.hashCode());
  }
  
  public String toString() {
    return "PaladiumContent(type=" + getType() + ", content=" + getContent() + ", children=" + getChildren() + ")";
  }
  
  public ContentType getType() {
    return this.type;
  }
  
  public String getContent() {
    return this.content;
  }
  
  public List<PaladiumContent> getChildren() {
    return this.children;
  }
  
  public PaladiumContent(ContentType type, String content) {
    this.type = type;
    this.content = content;
    this.children = new ArrayList<>();
  }
  
  public void addChild(PaladiumContent child) {
    this.children.add(child);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\common\pojo\parsed\PaladiumContent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */