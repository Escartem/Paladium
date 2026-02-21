package fr.paladium.palamod.modules.hunter.items;

public enum Type {
  HUNTER("Fait apparaître une créature liée à l’un des 4 métiers (XP récupérable à partir du niveau 35 du métier lié au boss)."),
  VOYANCE("Permet de cacher le pseudo du joueur qui la porte à travers les murs."),
  DISCRETION("§cCette amulette est désactivée."),
  COMBAT("§cCette amulette est désactivée."),
  MAGMA("Permet d'obtenir un effet de résistance au feu constant."),
  DAEMON("Joue un son démoniaque lors d'un kill."),
  SUMMER("Enflamme les joueurs à proximité.");
  
  private final String description;
  
  Type(String description) {
    this.description = description;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\hunter\items\ItemAmulet$Type.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */