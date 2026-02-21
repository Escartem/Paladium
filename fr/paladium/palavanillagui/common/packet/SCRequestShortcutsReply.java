package fr.paladium.palavanillagui.common.packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palavanillagui.client.ui.inventory.UIDirectory;
import fr.paladium.palavanillagui.common.utils.InventoryShortcut;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import java.util.List;

public class SCRequestShortcutsReply extends ForgePacket {
  @PacketData
  private List<InventoryShortcut> shortcutList;
  
  public SCRequestShortcutsReply() {}
  
  public SCRequestShortcutsReply(List<InventoryShortcut> shortcuts) {
    this.shortcutList = shortcuts;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (ZUI.isOpen(UIDirectory.class))
      ((UIDirectory)ZUI.getUI(UIDirectory.class)).getListSignal().set(this.shortcutList); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\packet\SCRequestShortcutsReply.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */