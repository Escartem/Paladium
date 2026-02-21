package fr.paladium.announcement.server.config.pojo;

import fr.paladium.announcement.common.type.AnnouncementType;
import java.util.List;

public class AnnouncementCategory {
  private AnnouncementType announcementType;
  
  private String hookTranslationKey;
  
  private List<String> titleKeywords;
  
  private boolean openInBrowserButton;
  
  public void setAnnouncementType(AnnouncementType announcementType) {
    this.announcementType = announcementType;
  }
  
  public void setHookTranslationKey(String hookTranslationKey) {
    this.hookTranslationKey = hookTranslationKey;
  }
  
  public void setTitleKeywords(List<String> titleKeywords) {
    this.titleKeywords = titleKeywords;
  }
  
  public void setOpenInBrowserButton(boolean openInBrowserButton) {
    this.openInBrowserButton = openInBrowserButton;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof AnnouncementCategory))
      return false; 
    AnnouncementCategory other = (AnnouncementCategory)o;
    if (!other.canEqual(this))
      return false; 
    if (isOpenInBrowserButton() != other.isOpenInBrowserButton())
      return false; 
    Object this$announcementType = getAnnouncementType(), other$announcementType = other.getAnnouncementType();
    if ((this$announcementType == null) ? (other$announcementType != null) : !this$announcementType.equals(other$announcementType))
      return false; 
    Object this$hookTranslationKey = getHookTranslationKey(), other$hookTranslationKey = other.getHookTranslationKey();
    if ((this$hookTranslationKey == null) ? (other$hookTranslationKey != null) : !this$hookTranslationKey.equals(other$hookTranslationKey))
      return false; 
    Object<String> this$titleKeywords = (Object<String>)getTitleKeywords(), other$titleKeywords = (Object<String>)other.getTitleKeywords();
    return !((this$titleKeywords == null) ? (other$titleKeywords != null) : !this$titleKeywords.equals(other$titleKeywords));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof AnnouncementCategory;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + (isOpenInBrowserButton() ? 79 : 97);
    Object $announcementType = getAnnouncementType();
    result = result * 59 + (($announcementType == null) ? 43 : $announcementType.hashCode());
    Object $hookTranslationKey = getHookTranslationKey();
    result = result * 59 + (($hookTranslationKey == null) ? 43 : $hookTranslationKey.hashCode());
    Object<String> $titleKeywords = (Object<String>)getTitleKeywords();
    return result * 59 + (($titleKeywords == null) ? 43 : $titleKeywords.hashCode());
  }
  
  public String toString() {
    return "AnnouncementCategory(announcementType=" + getAnnouncementType() + ", hookTranslationKey=" + getHookTranslationKey() + ", titleKeywords=" + getTitleKeywords() + ", openInBrowserButton=" + isOpenInBrowserButton() + ")";
  }
  
  public AnnouncementType getAnnouncementType() {
    return this.announcementType;
  }
  
  public String getHookTranslationKey() {
    return this.hookTranslationKey;
  }
  
  public List<String> getTitleKeywords() {
    return this.titleKeywords;
  }
  
  public boolean isOpenInBrowserButton() {
    return this.openInBrowserButton;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\config\pojo\AnnouncementCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */