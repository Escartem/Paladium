package fr.paladium.palarpg.module.dungeon.server.config.room;

public enum DungeonRoomType {
  CLASSIC("salle combat", "Tuez tous les monstres pour compléter la salle et récupérer du butin."),
  PUZZLE("salle énigme", "Résolvez l'énigme pour compléter la salle et récupérer du butin."),
  BOOST("salle bonus", "Profitez de cette salle pour récupérer des bonus avant de continuer l'aventure."),
  MERCHANT("salle commerce", "Profitez d'échanges avantageux avec le marchand avant de continuer l'aventure."),
  MINIBOSS("miniboss", "Vainquez le miniboss pour compléter la salle et récupérer du butin."),
  BOSS("boss", "Vainquez le boss pour compléter la salle et récupérer du butin.");
  
  DungeonRoomType(String title, String description) {
    this.title = title;
    this.description = description;
  }
  
  private final String title;
  
  private final String description;
  
  public String getTitle() {
    return this.title;
  }
  
  public String getDescription() {
    return this.description;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\config\room\DungeonRoomConfig$DungeonRoomType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */