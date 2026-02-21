package fr.paladium.announcement.common.pojo.ghost;

import fr.paladium.announcement.common.type.AnnouncementType;

public class GhostPost {
  private AnnouncementType type;
  
  private long date;
  
  private String uuid;
  
  private String title;
  
  private String html;
  
  private String feature_image;
  
  private String visibility;
  
  private String published_at;
  
  private String url;
  
  private String excerpt;
  
  public void setType(AnnouncementType type) {
    this.type = type;
  }
  
  public void setDate(long date) {
    this.date = date;
  }
  
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void setHtml(String html) {
    this.html = html;
  }
  
  public void setFeature_image(String feature_image) {
    this.feature_image = feature_image;
  }
  
  public void setVisibility(String visibility) {
    this.visibility = visibility;
  }
  
  public void setPublished_at(String published_at) {
    this.published_at = published_at;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public void setExcerpt(String excerpt) {
    this.excerpt = excerpt;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof GhostPost))
      return false; 
    GhostPost other = (GhostPost)o;
    if (!other.canEqual(this))
      return false; 
    if (getDate() != other.getDate())
      return false; 
    Object this$type = getType(), other$type = other.getType();
    if ((this$type == null) ? (other$type != null) : !this$type.equals(other$type))
      return false; 
    Object this$uuid = getUuid(), other$uuid = other.getUuid();
    if ((this$uuid == null) ? (other$uuid != null) : !this$uuid.equals(other$uuid))
      return false; 
    Object this$title = getTitle(), other$title = other.getTitle();
    if ((this$title == null) ? (other$title != null) : !this$title.equals(other$title))
      return false; 
    Object this$html = getHtml(), other$html = other.getHtml();
    if ((this$html == null) ? (other$html != null) : !this$html.equals(other$html))
      return false; 
    Object this$feature_image = getFeature_image(), other$feature_image = other.getFeature_image();
    if ((this$feature_image == null) ? (other$feature_image != null) : !this$feature_image.equals(other$feature_image))
      return false; 
    Object this$visibility = getVisibility(), other$visibility = other.getVisibility();
    if ((this$visibility == null) ? (other$visibility != null) : !this$visibility.equals(other$visibility))
      return false; 
    Object this$published_at = getPublished_at(), other$published_at = other.getPublished_at();
    if ((this$published_at == null) ? (other$published_at != null) : !this$published_at.equals(other$published_at))
      return false; 
    Object this$url = getUrl(), other$url = other.getUrl();
    if ((this$url == null) ? (other$url != null) : !this$url.equals(other$url))
      return false; 
    Object this$excerpt = getExcerpt(), other$excerpt = other.getExcerpt();
    return !((this$excerpt == null) ? (other$excerpt != null) : !this$excerpt.equals(other$excerpt));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof GhostPost;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $date = getDate();
    result = result * 59 + (int)($date >>> 32L ^ $date);
    Object $type = getType();
    result = result * 59 + (($type == null) ? 43 : $type.hashCode());
    Object $uuid = getUuid();
    result = result * 59 + (($uuid == null) ? 43 : $uuid.hashCode());
    Object $title = getTitle();
    result = result * 59 + (($title == null) ? 43 : $title.hashCode());
    Object $html = getHtml();
    result = result * 59 + (($html == null) ? 43 : $html.hashCode());
    Object $feature_image = getFeature_image();
    result = result * 59 + (($feature_image == null) ? 43 : $feature_image.hashCode());
    Object $visibility = getVisibility();
    result = result * 59 + (($visibility == null) ? 43 : $visibility.hashCode());
    Object $published_at = getPublished_at();
    result = result * 59 + (($published_at == null) ? 43 : $published_at.hashCode());
    Object $url = getUrl();
    result = result * 59 + (($url == null) ? 43 : $url.hashCode());
    Object $excerpt = getExcerpt();
    return result * 59 + (($excerpt == null) ? 43 : $excerpt.hashCode());
  }
  
  public String toString() {
    return "GhostPost(type=" + getType() + ", date=" + getDate() + ", uuid=" + getUuid() + ", title=" + getTitle() + ", html=" + getHtml() + ", feature_image=" + getFeature_image() + ", visibility=" + getVisibility() + ", published_at=" + getPublished_at() + ", url=" + getUrl() + ", excerpt=" + getExcerpt() + ")";
  }
  
  public AnnouncementType getType() {
    return this.type;
  }
  
  public long getDate() {
    return this.date;
  }
  
  public String getUuid() {
    return this.uuid;
  }
  
  public String getTitle() {
    return this.title;
  }
  
  public String getHtml() {
    return this.html;
  }
  
  public String getFeature_image() {
    return this.feature_image;
  }
  
  public String getVisibility() {
    return this.visibility;
  }
  
  public String getPublished_at() {
    return this.published_at;
  }
  
  public String getUrl() {
    return this.url;
  }
  
  public String getExcerpt() {
    return this.excerpt;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\common\pojo\ghost\GhostPost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */