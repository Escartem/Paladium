package fr.paladium.palashop.provider.box.common.network.server;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palashop.provider.box.BoxProvider;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CSPacketBoxStart extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private int keyCount;
  
  @PacketData
  private boolean fast;
  
  public CSPacketBoxStart() {}
  
  public CSPacketBoxStart(int entityId, int keyCount, boolean fast) {
    this.entityId = entityId;
    this.keyCount = keyCount;
    this.fast = fast;
  }
  
  public void processServer(EntityPlayerMP player) {
    Entity entity = player.field_70170_p.func_73045_a(this.entityId);
    if (!(entity instanceof EntityBox)) {
      player.func_145747_a((IChatComponent)new ChatComponentText("§8[§6Paladium§8] §cUne erreur est survenue, veuillez réessayer plus tard."));
      return;
    } 
    EntityBox box = (EntityBox)entity;
    BoxProvider.getServer().open(box, (EntityPlayer)player, this.keyCount, this.fast);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\network\server\CSPacketBoxStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */