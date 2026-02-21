package fr.paladium.palarpg.module.dungeon.common.network.room.merchant;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.dungeon.client.render.entity.room.merchant.RenderEntityDungeonMerchant;
import fr.paladium.palarpg.module.dungeon.common.entity.room.merchant.EntityDungeonMerchant;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class SCPacketRPGDungeonRoomMerchantAnimation extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private String animation;
  
  public SCPacketRPGDungeonRoomMerchantAnimation() {}
  
  public SCPacketRPGDungeonRoomMerchantAnimation(int entityId, String animation) {
    this.entityId = entityId;
    this.animation = animation;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (this.entityId == 0 || this.animation == null || this.animation.isEmpty())
      return; 
    Entity entity = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(this.entityId);
    if (!(entity instanceof EntityDungeonMerchant))
      return; 
    RenderEntityDungeonMerchant.getAnimatable((EntityDungeonMerchant)entity).forceAnimation(this.animation, false);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\room\merchant\SCPacketRPGDungeonRoomMerchantAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */