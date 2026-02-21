package fr.paladium.pet.common.network.packet.pet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.pet.common.PetCommonProxy;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSChangeSkinVisibilityPacket extends ForgePacket {
  public void processServer(EntityPlayerMP player) {
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has())
      return; 
    pet.changeVisibility();
    PetCommonProxy.getInstance().getNetwork().sendTo((IMessage)new BBOpenUIPacket(player, pet), player);
  }
  
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\pet\CSChangeSkinVisibilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */