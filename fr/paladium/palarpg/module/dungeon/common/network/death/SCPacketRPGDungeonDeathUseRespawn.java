package fr.paladium.palarpg.module.dungeon.common.network.death;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.sound.SoundRecord;
import fr.paladium.palarpg.module.dungeon.client.listener.DungeonClientDeathListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;
import net.minecraft.item.ItemStack;

public class SCPacketRPGDungeonDeathUseRespawn extends ForgePacket {
  @PacketData
  private ItemStack item;
  
  public SCPacketRPGDungeonDeathUseRespawn() {}
  
  public SCPacketRPGDungeonDeathUseRespawn(ItemStack item) {
    this.item = item;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    SoundRecord.create("palarpg", "sounds/dungeon/item/respawn.ogg").build(SoundCategory.PLAYERS).play();
    DungeonClientDeathListener.respawnTicks = 40;
    DungeonClientDeathListener.respawnItem = this.item;
    for (int j = 0; j < 20; j++) {
      double offsetX = ((Minecraft.func_71410_x()).field_71439_g.func_70681_au().nextDouble() - 0.5D) * 2.0D;
      double offsetY = (Minecraft.func_71410_x()).field_71439_g.func_70681_au().nextDouble() * 2.0D - 1.0D;
      double offsetZ = ((Minecraft.func_71410_x()).field_71439_g.func_70681_au().nextDouble() - 0.5D) * 2.0D;
      (Minecraft.func_71410_x()).field_71439_g.field_70170_p.func_72869_a("happyVillager", (Minecraft.func_71410_x()).field_71439_g.field_70165_t + offsetX, (Minecraft.func_71410_x()).field_71439_g.field_70163_u + offsetY, (Minecraft.func_71410_x()).field_71439_g.field_70161_v + offsetZ, 0.0D, 0.0D, 0.0D);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\death\SCPacketRPGDungeonDeathUseRespawn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */