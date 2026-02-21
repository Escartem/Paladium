package fr.paladium.palashop.provider.box.common.network.server;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketBoxStop extends ForgePacket {
  @PacketData
  private int entityId;
  
  public CSPacketBoxStop() {}
  
  public CSPacketBoxStop(int entityId) {
    this.entityId = entityId;
  }
  
  public void processServer(EntityPlayerMP player) {
    Entity entity = player.field_70170_p.func_73045_a(this.entityId);
    if (!(entity instanceof EntityBox))
      return; 
    EntityBox box = (EntityBox)entity;
    box.stop((EntityPlayer)player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\network\server\CSPacketBoxStop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */