package fr.paladium.palarpg.module.dungeon.common.network.chest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palarpg.module.dungeon.client.ui.chest.UIDungeonChest;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;

public class SCPacketRPGDungeonChestOpen extends ForgePacket {
  @PacketData
  private UIDungeonChest.UIDungeonChestData data;
  
  public SCPacketRPGDungeonChestOpen() {}
  
  public SCPacketRPGDungeonChestOpen(UIDungeonChest.UIDungeonChestData data) {
    this.data = data;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    ZUI.open((UI)new UIDungeonChest(this.data));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\common\network\chest\SCPacketRPGDungeonChestOpen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */