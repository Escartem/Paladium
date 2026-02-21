package fr.paladium.palavote.common.action;

import fr.paladium.palavote.server.config.PalaVoteConfig;
import java.util.Arrays;
import lombok.NonNull;

public class PalaVoteData {
  private String id;
  
  private String name;
  
  private String description;
  
  private String image;
  
  private PalaVoteConfig.PalaVoteChoice[] choices;
  
  private final boolean enabled;
  
  public String toString() {
    return "PalaVoteServerAction.PalaVoteData(id=" + getId() + ", name=" + getName() + ", description=" + getDescription() + ", image=" + getImage() + ", choices=" + Arrays.deepToString((Object[])getChoices()) + ", enabled=" + isEnabled() + ")";
  }
  
  public PalaVoteData(boolean enabled) {
    this.enabled = enabled;
  }
  
  public String getId() {
    return this.id;
  }
  
  public String getName() {
    return this.name;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public String getImage() {
    return this.image;
  }
  
  public PalaVoteConfig.PalaVoteChoice[] getChoices() {
    return this.choices;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public PalaVoteData(@NonNull PalaVoteConfig config) {
    if (config == null)
      throw new NullPointerException("config is marked non-null but is null"); 
    this.id = config.getId();
    this.name = config.getName();
    this.description = config.getDescription();
    this.image = config.getImage();
    this.choices = config.getChoices();
    this.enabled = config.isEnabled();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\common\action\PalaVoteServerAction$PalaVoteData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */