package fr.paladium.palamod.modules.luckyblock;

import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;

class null extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    event.perform(player, x, y, z);
  }
  
  public String getName() {
    return event.getName();
  }
  
  public boolean isBad() {
    return event.isBad();
  }
  
  public int getRarity() {
    return event.getRarity();
  }
  
  public String getTexture() {
    return event.getTexture();
  }
  
  public double getWeight() {
    return event.getWeight() * 1.25D;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\PLuckyBlock$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */