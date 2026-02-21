package fr.paladium.palarpg.module.dungeon.common.network.start;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSPacketRPGDungeonReady extends ForgePacket {
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(player.field_70170_p);
    if (!optionalDungeonWorld.isPresent())
      return; 
    try {
      ((DungeonWorld)optionalDungeonWorld.get()).ready(player);
    } catch (Exception e) {
      throw new RuntimeException("Error while readying dungeon world", e);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\start\CSPacketRPGDungeonReady.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */