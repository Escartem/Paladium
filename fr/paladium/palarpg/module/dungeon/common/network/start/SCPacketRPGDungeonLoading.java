package fr.paladium.palarpg.module.dungeon.common.network.start;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palarpg.module.dungeon.client.ui.loading.UIDungeonLoading;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;

public class SCPacketRPGDungeonLoading extends ForgePacket {
  @SideOnly(Side.CLIENT)
  public void processClient() {
    ZUI.open((UI)(new UIDungeonLoading()).start(), true);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\start\SCPacketRPGDungeonLoading.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */