package fr.paladium.palamod.modules.luckyblock.luckyevents.halloween;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.luckyblock.network.PacketSpookySpook;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.player.EntityPlayerMP;

public class SpookySpook extends ALuckyEvent {
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PalaMod.getNetwork().sendTo((IMessage)new PacketSpookySpook(), player);
  }
  
  public String getName() {
    return "Spooky Spook";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 50;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\luckyevents\halloween\SpookySpook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */