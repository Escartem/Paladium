package fr.paladium.palaclicker.common.network.packet.click;

import fr.paladium.palaclicker.common.network.data.PlayerClickerData;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketClickerClick extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    PlayerClickerData data = PlayerClickerData.get((Entity)player);
    long now = System.currentTimeMillis();
    long diff = now - data.getLastClick();
    if (diff < 50L)
      return; 
    double value = data.getClickValue();
    data.setLastClick(now);
    data.addPoints(value);
    reply(Double.valueOf(value));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palaclicker\common\network\packet\click\BBPacketClickerClick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */