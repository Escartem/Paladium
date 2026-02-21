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

public class BBPacketRPGDungeonSynchronizedUICursorMove extends ForgePacket {
  @PacketData
  private String player;
  
  @PacketData
  private String uiClass;
  
  @PacketData
  private double mouseX;
  
  @PacketData
  private double mouseY;
  
  public BBPacketRPGDungeonSynchronizedUICursorMove() {}
  
  public BBPacketRPGDungeonSynchronizedUICursorMove(String player, String uiClass, double mouseX, double mouseY) {
    this.player = player;
    this.uiClass = uiClass;
    this.mouseX = mouseX;
    this.mouseY = mouseY;
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
      if (dungeonUI.getCursorNode() == null)
        return; 
      dungeonUI.getCursorNode().setCursor(this.player, this.mouseX, this.mouseY);
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
    world.getOnlinePlayers().forEach(p -> (new BBPacketRPGDungeonSynchronizedUICursorMove(UUIDUtils.toString((Entity)player), this.uiClass, this.mouseX, this.mouseY)).send((EntityPlayerMP)p));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\networ\\ui\sync\BBPacketRPGDungeonSynchronizedUICursorMove.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */