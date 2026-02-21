package fr.paladium.announcement.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.announcement.common.data.AnnouncementPlayer;
import fr.paladium.announcement.common.network.SCAnnouncementPacket;
import fr.paladium.announcement.common.pojo.parsed.PaladiumPost;
import fr.paladium.announcement.server.config.PalaAnnouncementConfig;
import fr.paladium.announcement.server.manager.PalaAnnouncementManager;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class PlayerListener {
  private PalaAnnouncementManager manager = PalaAnnouncementManager.getInstance();
  
  @SubscribeEvent
  public void onJoin(EntityJoinWorldEvent e) {
    if (!(e.entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)e.entity;
    AnnouncementPlayer announcementPlayer = AnnouncementPlayer.get(player);
    List<PaladiumPost> unreadPosts = new ArrayList<>();
    long newsDate = PalaAnnouncementConfig.get().getFetchNewsFrom();
    if (announcementPlayer.getLastAnnouncement() < newsDate)
      announcementPlayer.setLastAnnouncement(newsDate); 
    this.manager.getCachedPosts().stream().forEach(post -> {
          if (announcementPlayer.getLastAnnouncement() <= post.getDate())
            unreadPosts.add(post); 
        });
    announcementPlayer.setLastAnnouncement(System.currentTimeMillis());
    announcementPlayer.sync();
    if (!unreadPosts.isEmpty())
      (new SCAnnouncementPacket(unreadPosts)).send((EntityPlayerMP)player); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\announcement\server\listener\PlayerListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */