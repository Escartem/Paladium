package fr.paladium.palarpg.module.stat.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.stat.client.renderer.RPGDamageRenderer;
import fr.paladium.palarpg.module.stat.server.manager.impl.DamageManager;

public class SCRPGDamagePacket extends ForgePacket {
  @PacketData
  private int entityId;
  
  @PacketData
  private DamageManager.DamageObject damage;
  
  public void setEntityId(int entityId) {
    this.entityId = entityId;
  }
  
  public void setDamage(DamageManager.DamageObject damage) {
    this.damage = damage;
  }
  
  public SCRPGDamagePacket() {}
  
  public SCRPGDamagePacket(int entityId, DamageManager.DamageObject damage) {
    this.entityId = entityId;
    this.damage = damage;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    RPGDamageRenderer.appendDamage(new RPGDamageRenderer.RPGDamage(this.entityId, this.damage.isCritical(), this.damage.getApplicableDamage().doubleValue()));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\stat\common\network\SCRPGDamagePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */