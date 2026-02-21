package fr.paladium.achievement.core.pojo.types;

import fr.paladium.achievement.core.pojo.AchievementCategory;

public class CommandAchievementBuilder {
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


/* Location:              E:\Paladium\!\fr\paladium\achievement\core\pojo\types\CommandAchievement$CommandAchievementBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */