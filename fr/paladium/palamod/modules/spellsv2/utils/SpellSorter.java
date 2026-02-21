package fr.paladium.palamod.modules.spellsv2.utils;

import java.util.Comparator;

public class SpellSorter implements Comparator<Spells> {
  public int compare(Spells s1, Spells s2) {
    if (s1.getSpell().getLevel() < s2.getSpell().getLevel())
      return 1; 
    return -1;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv\\utils\SpellSorter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */