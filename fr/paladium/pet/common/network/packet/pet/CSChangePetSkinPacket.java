package fr.paladium.pet.common.network.packet.pet;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.pet.common.network.data.PetPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSChangePetSkinPacket extends ForgePacket {
  @PacketData
  private String id;
  
  public CSChangePetSkinPacket() {}
  
  public CSChangePetSkinPacket(String id) {
    this.id = id;
  }
  
  public void processServer(EntityPlayerMP player) {
    PetPlayer pet = PetPlayer.get((EntityPlayer)player);
    if (pet == null || !pet.has())
      return; 
    pet.changePetSkin(player, this.id);
  }
  
  public void processClient() {}
}


/* Location:              E:\Paladium\!\fr\paladium\pet\common\network\packet\pet\CSChangePetSkinPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */