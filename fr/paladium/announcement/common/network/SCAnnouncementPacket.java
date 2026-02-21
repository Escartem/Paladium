package fr.paladium.announcement.common.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.announcement.client.ui.UIAnnouncement;
import fr.paladium.announcement.common.pojo.parsed.PaladiumPost;
import fr.paladium.palaforgeutils.lib.packet.ForgePacket;
import fr.paladium.palaforgeutils.lib.packet.utils.PacketData;
import fr.paladium.palaforgeutils.lib.scheduler.FMLClientScheduler;
import fr.paladium.zephyrui.internal.mod.client.utils.ZUI;
import fr.paladium.zephyrui.lib.ui.core.UI;
import java.util.List;

public class SCAnnouncementPacket extends ForgePacket {
  @PacketData
  private List<PaladiumPost> posts;
  
  public SCAnnouncementPacket() {}
  
  public SCAnnouncementPacket(List<PaladiumPost> posts) {
    this.posts = posts;
  }
  
  @SideOnly(Side.CLIENT)
  public void processClient() {
    if (this.posts == null || this.posts.isEmpty())
      return; 
    FMLClientScheduler.getInstance().add(new Runnable[] { () -> ZUI.open((UI)new UIAnnouncement(this.posts)) });
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\common\network\SCAnnouncementPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */