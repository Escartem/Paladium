package fr.paladium.palamod.modules.luckyblock.luckyevents;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketUseInversion;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class Inversion extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PalaMod.getNetwork().sendTo((IMessage)new PacketUseInversion(true), player);
    (new Thread(() -> {
          try {
            Thread.sleep(180000L);
            PalaMod.getNetwork().sendTo((IMessage)new PacketUseInversion(false), player);
          } catch (Exception exception) {}
        })).start();
  }
  
  public String getName() {
    return "Inversion";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 20;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\Inversion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */