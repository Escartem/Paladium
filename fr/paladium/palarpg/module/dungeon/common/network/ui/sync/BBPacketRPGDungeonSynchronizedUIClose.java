package fr.paladium.palarpg.module.dungeon.common.network.ui.sync;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.uuid.UUIDUtils;
import fr.paladium.palarpg.module.dungeon.client.ui.utils.IDungeonSynchronizedUI;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class BBPacketRPGDungeonSynchronizedUIClose extends ForgePacket {
  @PacketData
  private String leader;
  
  @PacketData
  private String uiClass;
  
  @PacketData
  private boolean force;
  
  public BBPacketRPGDungeonSynchronizedUIClose() {}
  
  public BBPacketRPGDungeonSynchronizedUIClose(String leader, String uiClass, boolean force) {
    this.leader = leader;
    this.uiClass = uiClass;
    this.force = force;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    try {
      Class<? extends UI> clazz = (Class)Class.forName(this.uiClass);
      if (clazz == null)
        return; 
      UI ui = ZUI.getUI(clazz);
      if (!(ui instanceof IDungeonSynchronizedUI))
        return; 
      IDungeonSynchronizedUI dungeonUI = (IDungeonSynchronizedUI)ui;
      if (!dungeonUI.isMain(this.leader))
        return; 
      if (this.force) {
        ZUI.close(ui, this.force);
      } else {
        dungeonUI.onRemoteClosed();
      } 
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
  
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    Optional<DungeonWorld> optionalWorld = DungeonWorld.get((EntityPlayer)player);
    if (!optionalWorld.isPresent())
      return; 
    DungeonWorld world = optionalWorld.get();
    world.getOnlinePlayers().forEach(p -> (new BBPacketRPGDungeonSynchronizedUIClose(UUIDUtils.toString((Entity)player), this.uiClass, this.force)).send((EntityPlayerMP)p));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\networ\\ui\sync\BBPacketRPGDungeonSynchronizedUIClose.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */