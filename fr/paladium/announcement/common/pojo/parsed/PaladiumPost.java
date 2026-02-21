package fr.paladium.announcement.common.pojo.parsed;

import fr.paladium.announcement.common.type.AnnouncementType;
import java.util.ArrayList;
import java.util.List;

public class PaladiumPost {
  private AnnouncementType type;
  
  private String title;
  
  private long date;
  
  private List<PaladiumContent> contents;
  
  public void setType(AnnouncementType type) {
    this.type = type;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void setDate(long date) {
    this.date = date;
  }
  
  public void setContents(List<PaladiumContent> contents) {
    this.contents = contents;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof PaladiumPost))
      return false; 
    PaladiumPost other = (PaladiumPost)o;
    if (!other.canEqual(this))
      return false; 
    if (getDate() != other.getDate())
      return false; 
    Object this$type = getType(), other$type = other.getType();
    if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
      return false; 
    Object this$title = getTitle(), other$title = other.getTitle();
    if ((this$title == null) ? (other$title != null) : !this$title.equals(other$title))
      return false; 
    Object<PaladiumContent> this$contents = (Object<PaladiumContent>)getContents(), other$contents = (Object<PaladiumContent>)other.getContents();
    return !((this$contents == null) ? (other$contents != null) : !this$contents.equals(other$contents));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof PaladiumPost;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $date = getDate();
    result = result * 59 + (int)($date >>> 32L ^ $date);
    Object $type = getType();
    result = result * 59 + (($type == null) ? 43 : $type.hashCode());
    Object $title = getTitle();
    result = result * 59 + (($title == null) ? 43 : $title.hashCode());
    Object<PaladiumContent> $contents = (Object<PaladiumContent>)getContents();
    return result * 59 + (($contents == null) ? 43 : $contents.hashCode());
  }
  
  public String toString() {
    return "PaladiumPost(type=" + getType() + ", title=" + getTitle() + ", date=" + getDate() + ", contents=" + getContents() + ")";
  }
  
  public AnnouncementType getType() {
    return this.type;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public long getDate() {
    return this.date;
  }
  
  public List<PaladiumContent> getContents() {
    return this.contents;
  }
  
  public PaladiumPost(String title) {
    this.title = title;
    this.contents = new ArrayList<>();
  }
  
  public void addContent(PaladiumContent content) {
    this.contents.add(content);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\common\pojo\parsed\PaladiumPost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */