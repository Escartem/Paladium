package fr.paladium.palashop.provider.box.common.network.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palashop.provider.box.client.render.entity.RenderEntityBox;
import fr.paladium.palashop.provider.box.common.entity.EntityBox;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class SCPacketBoxStop extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private boolean force;
  
  public SCPacketBoxStop() {}
  
  public SCPacketBoxStop(int entityId, boolean force) {
    this.entityId = entityId;
    this.force = force;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    long start = System.currentTimeMillis();
    (new Thread(() -> {
          while (System.currentTimeMillis() - start < 30000L) {
            Entity entity = player.field_70170_p.func_73045_a(this.entityId);
            if (!(entity instanceof EntityBox))
              continue; 
            EntityBox box = (EntityBox)entity;
            if (this.force || (box.getClientState().isWaiting() && !box.getClientState().isActive())) {
              box.getClientState().stop();
              RenderEntityBox.stop(box);
            } 
            return;
          } 
        }"PalaShop/SCPacketBoxStop"))
      
      .start();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palashop\provider\box\common\network\client\SCPacketBoxStop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */