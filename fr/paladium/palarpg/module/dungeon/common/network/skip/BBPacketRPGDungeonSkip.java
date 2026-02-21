package fr.paladium.palarpg.module.dungeon.common.network.skip;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.dungeon.client.ui.skip.UIDungeonSkipOverlay;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketRPGDungeonSkip extends ForgePacket {
  @PacketData
  private String roomId;
  
  public BBPacketRPGDungeonSkip() {}
  
  public BBPacketRPGDungeonSkip(String roomId) {
    this.roomId = roomId;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    ZUI.open((UI)new UIDungeonSkipOverlay(this.roomId));
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get((EntityPlayer)player);
    if (!optionalDungeonWorld.isPresent())
      return; 
    DungeonWorld dungeonWorld = optionalDungeonWorld.get();
    if (!dungeonWorld.isLeader((Entity)player))
      return; 
    Optional<DungeonRoom> optionalRoom = dungeonWorld.getRoom();
    if (!optionalRoom.isPresent() || !((DungeonRoom)optionalRoom.get()).getConfig().getId().equals(this.roomId))
      return; 
    DungeonRoom currentRoom = optionalRoom.get();
    if (!currentRoom.isSkippable())
      return; 
    if (!currentRoom.isFinished())
      currentRoom.setFinished(true); 
    currentRoom.next();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\skip\BBPacketRPGDungeonSkip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */