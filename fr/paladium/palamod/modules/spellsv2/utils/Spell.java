package fr.paladium.palamod.modules.spellsv2.utils;

import net.minecraft.entity.player.EntityPlayerMP;

public interface Spell {
  int getId();
  
  String getName();
  
  int getMaxTiers();
  
  int getCost();
  
  int getCooldown();
  
  int getLevel();
  
  String[] getDescription();
  
  void perform(EntityPlayerMP paramEntityPlayerMP, int paramInt);
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\spellsv\\utils\Spell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */