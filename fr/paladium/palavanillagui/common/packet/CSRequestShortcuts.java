package fr.paladium.palavanillagui.common.packet;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palavanillagui.server.ServerProxy;
import net.minecraft.entity.player.EntityPlayerMP;

public class CSRequestShortcuts extends ForgePacket {
  @SideOnly(Side.SERVER)
  public void processServer(EntityPlayerMP player) {
    (new SCRequestShortcutsReply(ServerProxy.shortcutConfig.getShortcuts())).send(player);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palavanillagui\common\packet\CSRequestShortcuts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */