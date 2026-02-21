package fr.paladium.palamod.modules.spellsv2.utils;

public class SpellObj {
  private Spell spell;
  
  private int tier;
  
  private boolean unlock;
  
  public Spell getSpell() {
    return this.spell;
  }
  
  public void setSpell(Spell spell) {
    this.spell = spell;
  }
  
  public int getTier() {
    return this.tier;
  }
  
  public void setTier(int tier) {
    this.tier = tier;
  }
  
  public boolean isUnlock() {
    return this.unlock;
  }
  
  public void setUnlock(boolean unlock) {
    this.unlock = unlock;
  }
  
  public SpellObj(Spell spell, int tier, boolean unlock) {
    this.spell = spell;
    this.tier = tier;
    this.unlock = unlock;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv\\utils\SpellObj.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */