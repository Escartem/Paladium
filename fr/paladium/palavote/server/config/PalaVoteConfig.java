package fr.paladium.palavote.server.config;

import fr.paladium.palaconfiguration.server.system.IConfig;
import fr.paladium.palaconfiguration.server.system.annotations.ConfigFile;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

@ConfigFile(path = "palavote/config.json", blocking = true)
public class PalaVoteConfig implements IConfig {
  private static final Pattern ID_PATTERN = Pattern.compile("[a-z0-9_]+");
  
  private String id;
  
  private String name;
  
  private String description;
  
  private String image;
  
  private PalaVoteChoice[] choices;
  
  private String mongoURI;
  
  private boolean enabled;
  
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
  
  public PalaVoteChoice[] getChoices() {
    return this.choices;
  }
  
  public String getMongoURI() {
    return this.mongoURI;
  }
  
  public boolean isEnabled() {
    return this.enabled;
  }
  
  public void onLoaded() {
    if (this.id == null)
      throw new RuntimeException("[PalaVote] No ID found in the configuration file"); 
    if (this.id.isEmpty() || !ID_PATTERN.matcher(this.id).matches())
      throw new RuntimeException("[PalaVote] Invalid ID in the configuration file"); 
    if (this.name == null)
      throw new RuntimeException("[PalaVote] No name found in the configuration file"); 
    if (this.name.isEmpty())
      throw new RuntimeException("[PalaVote] Invalid name in the configuration file"); 
    if (this.mongoURI == null)
      throw new RuntimeException("[PalaVote] No MongoDB URI found in the configuration file"); 
    if (this.choices == null)
      throw new RuntimeException("[PalaVote] No choices found in the configuration file"); 
    if (this.choices.length < 2)
      throw new RuntimeException("[PalaVote] Invalid number of choices in the configuration file"); 
  }
  
  public void onReloaded() {
    onLoaded();
  }
  
  public void onFailed() {
    throw new RuntimeException("[PalaVote] Failed to load the configuration file");
  }
  
  public Optional<PalaVoteChoice> getChoice(String name) {
    for (PalaVoteChoice choice : this.choices) {
      if (choice.getName().equals(name))
        return Optional.of(choice); 
    } 
    return Optional.empty();
  }
  
  public static class PalaVoteChoice {
    private String name;
    
    private String description;
    
    private String image;
    
    public String getName() {
      return this.name;
    }
    
    public String getDescription() {
      return this.description;
    }
    
    public String getImage() {
      return this.image;
    }
    
    public String toString() {
      return this.name;
    }
    
    public int hashCode() {
      return Objects.hash(new Object[] { this.name });
    }
    
    public boolean equals(Object obj) {
      if (this == obj)
        return true; 
      if (obj == null || getClass() != obj.getClass())
        return false; 
      PalaVoteChoice other = (PalaVoteChoice)obj;
      return Objects.equals(this.name, other.name);
    }
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavote\server\config\PalaVoteConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */