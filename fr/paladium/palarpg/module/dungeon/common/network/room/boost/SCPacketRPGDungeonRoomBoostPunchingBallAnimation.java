package fr.paladium.palarpg.module.dungeon.common.network.room.boost;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palarpg.module.dungeon.client.render.entity.room.boost.RenderEntityDungeonPunchingBall;
import fr.paladium.palarpg.module.dungeon.common.entity.room.boost.EntityDungeonPunchingBall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class SCPacketRPGDungeonRoomBoostPunchingBallAnimation extends ForgePacket {
  @PacketData
  private int entityId;
  
  public SCPacketRPGDungeonRoomBoostPunchingBallAnimation() {}
  
  public SCPacketRPGDungeonRoomBoostPunchingBallAnimation(int entityId) {
    this.entityId = entityId;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (this.entityId == 0)
      return; 
    Entity entity = (Minecraft.func_71410_x()).field_71441_e.func_73045_a(this.entityId);
    if (!(entity instanceof EntityDungeonPunchingBall))
      return; 
    RenderEntityDungeonPunchingBall.getAnimatable((EntityDungeonPunchingBall)entity).forceAnimation("hurt", false);
    SoundRecord.create(new ResourceLocation("palarpg", "sounds/dungeon/entity/dummy/hit.ogg")).build(SoundCategory.ANIMALS).play();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\room\boost\SCPacketRPGDungeonRoomBoostPunchingBallAnimation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */