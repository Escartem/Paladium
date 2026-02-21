package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.Achievement;
import fr.paladium.achievement.core.pojo.AchievementCategory;
import fr.paladium.achievement.server.managers.AchievementManager;
import net.minecraft.entity.player.EntityPlayer;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandAchievement extends Achievement {
  private String command;
  
  public void setCommand(String command) {
    this.command = command;
  }
  
  public static CommandAchievementBuilder builder() {
    return new CommandAchievementBuilder();
  }
  
  public static class CommandAchievementBuilder {
    private String id;
    
    private AchievementCategory category;
    
    private String name;
    
    private String description;
    
    private int nbToValidate;
    
    private String command;
    
    private String icon;
    
    public CommandAchievementBuilder id(String id) {
      this.id = id;
      return this;
    }
    
    public CommandAchievementBuilder category(AchievementCategory category) {
      this.category = category;
      return this;
    }
    
    public CommandAchievementBuilder name(String name) {
      this.name = name;
      return this;
    }
    
    public CommandAchievementBuilder description(String description) {
      this.description = description;
      return this;
    }
    
    public CommandAchievementBuilder nbToValidate(int nbToValidate) {
      this.nbToValidate = nbToValidate;
      return this;
    }
    
    public CommandAchievementBuilder command(String command) {
      this.command = command;
      return this;
    }
    
    public CommandAchievementBuilder icon(String icon) {
      this.icon = icon;
      return this;
    }
    
    public CommandAchievement build() {
      return new CommandAchievement(this.id, this.category, this.name, this.description, this.nbToValidate, this.command, this.icon);
    }
    
    public String toString() {
      return "CommandAchievement.CommandAchievementBuilder(id=" + this.id + ", category=" + this.category + ", name=" + this.name + ", description=" + this.description + ", nbToValidate=" + this.nbToValidate + ", command=" + this.command + ", icon=" + this.icon + ")";
    }
  }
  
  public CommandAchievement(String id, AchievementCategory category, String name, String description, int nbToValidate, String command, String icon) {
    super(id, category, name, description, nbToValidate, icon);
    this.command = command;
  }
  
  public String getCommand() {
    return this.command;
  }
  
  public static void performCheck(String message, EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(CommandAchievement.class)) {
      if (achievement instanceof CommandAchievement) {
        CommandAchievement cmdAchvmt = (CommandAchievement)achievement;
        if (message.equalsIgnoreCase(cmdAchvmt.getCommand()))
          incrementStats(cmdAchvmt, p, 1); 
      } 
    } 
  }
  
  public static void performCheck(PlayerCommandPreprocessEvent event, EntityPlayer p) {
    for (Achievement achievement : AchievementManager.getInstance().getAchievements(CommandAchievement.class)) {
      if (achievement instanceof CommandAchievement) {
        CommandAchievement cmdAchvmt = (CommandAchievement)achievement;
        if (event.getMessage().equalsIgnoreCase(cmdAchvmt.getCommand()))
          incrementStats(cmdAchvmt, p, 1); 
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\CommandAchievement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */