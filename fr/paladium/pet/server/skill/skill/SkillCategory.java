package fr.paladium.pet.server.skill.skill;

public enum SkillCategory {
  MOVEMENT("Déplacement"),
  UTILS("Utilitaire"),
  MINER("Mineur"),
  FARMER("Fermier"),
  HUNTER("Chasseur"),
  ALCHEMIST("Alchimiste"),
  PET("Familier"),
  PLUNDER("Pillage"),
  CLAIMED_LAND("Base claim"),
  OBJECT("Objet");
  
  private final String name;
  
  SkillCategory(String name) {
    this.name = name;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\pet\server\skill\skill\SkillCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */