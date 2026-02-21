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

public class BBPacketRPGDungeonSynchronizedUIKeyPress extends ForgePacket {
  @PacketData
  private String leader;
  
  @PacketData
  private String uiClass;
  
  @PacketData
  private char c;
  
  @PacketData
  private int keyCode;
  
  public BBPacketRPGDungeonSynchronizedUIKeyPress() {}
  
  public BBPacketRPGDungeonSynchronizedUIKeyPress(String leader, String uiClass, char c, int keyCode) {
    this.leader = leader;
    this.uiClass = uiClass;
    this.c = c;
    this.keyCode = keyCode;
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
      dungeonUI.onRemoteKeyPressed(this.c, this.keyCode);
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
    world.getOnlinePlayers().forEach(p -> (new BBPacketRPGDungeonSynchronizedUIKeyPress(UUIDUtils.toString((Entity)player), this.uiClass, this.c, this.keyCode)).send((EntityPlayerMP)p));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\networ\\ui\sync\BBPacketRPGDungeonSynchronizedUIKeyPress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */