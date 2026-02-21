package fr.paladium.palamod.modules.back2future.entities.projectile;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palamod.PalaMod;
import fr.paladium.palamod.modules.spellsv2.network.client.PacketClientFrozen;
import fr.paladium.palamod.modules.spellsv2.utils.ServerManager;
import net.minecraft.entity.player.EntityPlayerMP;

class null implements Runnable {
  public void run() {
    try {
      Thread.sleep((2000 * EntityCustomFallingBlock.this.tier));
      ServerManager.removeFreeze(pl);
      PalaMod.getNetwork().sendTo((IMessage)new PacketClientFrozen(false, EntityCustomFallingBlock.this.tier), pl);
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\back2future\entities\projectile\EntityCustomFallingBlock$2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */