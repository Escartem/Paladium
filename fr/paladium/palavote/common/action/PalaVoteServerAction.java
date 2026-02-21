package fr.paladium.palavote.common.action;

import fr.paladium.palaforgeutils.lib.sidedaction.ServerActionHook;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerActionContext;
import fr.paladium.palaforgeutils.lib.sidedaction.utils.server.ServerComponent;
import fr.paladium.palavote.server.config.PalaVoteConfig;
import fr.paladium.palavote.server.manager.PalaVoteManager;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import lombok.NonNull;

@ServerComponent
public class PalaVoteServerAction {
  @NonNull
  public static CompletableFuture<Boolean> vote(@NonNull String choice) {
    if (choice == null)
      throw new NullPointerException("choice is marked non-null but is null"); 
    return ServerActionHook.useServer(context -> CompletableFuture.completedFuture(Boolean.valueOf(PalaVoteManager.vote(context.getPlayer(), choice))), new Object[] { choice });
  }
  
  public static class PalaVoteData {
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
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\common\action\PalaVoteServerAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */