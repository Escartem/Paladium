package fr.paladium.palarpg.module.dungeon.common.network.world;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.dungeon.common.event.DungeonWorldUpdateEvent;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.player.DungeonPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class SCPacketRPGDungeonWorldSync extends ForgePacket {
  @PacketData
  private DungeonWorld dungeonWorld;
  
  public SCPacketRPGDungeonWorldSync() {}
  
  public SCPacketRPGDungeonWorldSync(DungeonWorld dungeonWorld) {
    this.dungeonWorld = dungeonWorld;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (this.dungeonWorld == null)
      return; 
    this.dungeonWorld.setWorld((World)(Minecraft.func_71410_x()).field_71441_e);
    EntityClientPlayerMP entityClientPlayerMP = (Minecraft.func_71410_x()).field_71439_g;
    for (DungeonPlayer dungeonPlayer : this.dungeonWorld.getPlayers()) {
      if (dungeonPlayer.getName().equals(entityClientPlayerMP.func_70005_c_())) {
        ObfuscationReflectionHelper.setPrivateValue(Entity.class, entityClientPlayerMP, dungeonPlayer.getUuid(), new String[] { "entityUniqueID", "field_96093_i" });
        break;
      } 
    } 
    MinecraftForge.EVENT_BUS.post((Event)new DungeonWorldUpdateEvent.Pre(this.dungeonWorld));
    DungeonWorld.set(this.dungeonWorld);
    MinecraftForge.EVENT_BUS.post((Event)new DungeonWorldUpdateEvent.Post(this.dungeonWorld));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\world\SCPacketRPGDungeonWorldSync.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */