package fr.paladium.palarpg.module.dungeon.common.network.start;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palarpg.module.dungeon.client.ui.loading.UIDungeonLoading;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;

public class SCPacketRPGDungeonStart extends ForgePacket {
  @SideOnly(Side.CLIENT)
  public void processClient() {
    UIDungeonLoading ui = (UIDungeonLoading)ZUI.getUI(UIDungeonLoading.class);
    if (ui != null)
      ui.stop(); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\start\SCPacketRPGDungeonStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */