package fr.paladium.palamod.modules.luckyblock.utils;

import net.minecraft.entity.player.EntityPlayerMP;

public interface LuckyEvent {
  void perform(EntityPlayerMP paramEntityPlayerMP, int paramInt1, int paramInt2, int paramInt3);
  
  String getName();
  
  boolean isBad();
  
  int getRarity();
  
  String getTexture();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckybloc\\utils\LuckyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */