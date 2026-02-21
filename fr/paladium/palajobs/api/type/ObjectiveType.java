package fr.paladium.palajobs.api.type;

public enum ObjectiveType {
  BREAK_BLOCK("jobs.objective.break"),
  SMELT("jobs.objective.smelt"),
  CRUSHER_GENERATE("jobs.objective.crusher"),
  COBBLE_BREAKER("jobs.objective.cobblebreaker"),
  CRAFT("jobs.objective.craft"),
  DROP_CAULDRON("jobs.objective.dropcauldron"),
  EXTRACT_SEVE("jobs.objective.extract"),
  CRAFT_CAULDRON("jobs.objective.craftcauldron"),
  CRAFT_PORTAL("jobs.objective.craftportal"),
  WITHER_SPAWN("jobs.objective.spawn"),
  FISH("jobs.objective.fish"),
  ENTITY_KILL("jobs.objective.kill"),
  ENTITY_KILL_SPECIAL("jobs.objective.kill"),
  ARROW_KILL("jobs.objective.arrowkill"),
  XP_BOTTLER("jobs.objective.xpbottler"),
  QUEST("jobs.objective.quest"),
  WARZONE_EVENT("jobs.objective.warzoneevent");
  
  private final String translate;
  
  ObjectiveType(String translate) {
    this.translate = translate;
  }
  
  public String getTranslate() {
    return this.translate;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\api\type\ObjectiveType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */