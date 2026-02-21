package fr.paladium.palavanillagui.common.packet;

import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palavanillagui.common.container.ContainerAnvil;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatAllowedCharacters;

public class CSPacketRenameItemAnvil extends ForgePacket {
  @PacketData
  private String name;
  
  public CSPacketRenameItemAnvil(String name) {
    this.name = name;
  }
  
  public CSPacketRenameItemAnvil() {}
  
  public void processServer(EntityPlayerMP player) {
    if (player.field_71070_bA instanceof ContainerAnvil) {
      ContainerAnvil container = (ContainerAnvil)player.field_71070_bA;
      if (this.name != null && this.name.length() > 1) {
        String s = ChatAllowedCharacters.func_71565_a(this.name);
        if (s.length() <= 30)
          container.updateItemName(s); 
      } else {
        container.updateItemName("");
      } 
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\packet\CSPacketRenameItemAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */