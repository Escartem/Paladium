package fr.paladium.palarpg.module.entity.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.entity.common.entity.impl.RPGMobEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

public class SCPacketUpdateRPGEntitySize extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private float scale;
  
  public SCPacketUpdateRPGEntitySize() {}
  
  public SCPacketUpdateRPGEntitySize(int entityId, float scale) {
    this.entityId = entityId;
    this.scale = scale;
  }
  
  public int getEntityId() {
    return this.entityId;
  }
  
  public float getScale() {
    return this.scale;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    Entity entity = (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_73045_a(this.entityId);
    if (!(entity instanceof RPGMobEntity))
      return; 
    RPGMobEntity rpgEntity = (RPGMobEntity)entity;
    rpgEntity.setSizeScale(this.scale);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\entity\common\network\SCPacketUpdateRPGEntitySize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */