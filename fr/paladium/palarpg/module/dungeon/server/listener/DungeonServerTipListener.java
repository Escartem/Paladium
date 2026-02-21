package fr.paladium.palarpg.module.dungeon.server.listener;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palarpg.PalaRPGMod;
import fr.paladium.palarpg.module.dungeon.common.world.DungeonWorld;
import fr.paladium.palarpg.module.dungeon.common.world.room.DungeonRoom;
import fr.paladium.palarpg.module.profile.client.ui.overlay.UIOverlayRPGTip;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayerMP;

public class DungeonServerTipListener {
  private static final String[] TIPS = new String[] { "Vous pouvez utiliser la commande /spawn pour retourner au début de la salle actuelle.", "Si vous êtes bloqué, la salle se débloquera automatiquement au bout de 10 minutes." };
  
  @SubscribeEvent
  public void onTick(TickEvent.PlayerTickEvent event) {
    if (event.phase != TickEvent.Phase.END || !PalaRPGMod.proxy.isDungeonWorld())
      return; 
    Optional<DungeonWorld> optionalDungeonWorld = DungeonWorld.get(event.player);
    if (!optionalDungeonWorld.isPresent())
      return; 
    Optional<DungeonRoom> optionalRoom = ((DungeonWorld)optionalDungeonWorld.get()).getRoom();
    if (!optionalRoom.isPresent())
      return; 
    long startTime = ((DungeonRoom)optionalRoom.get()).getStartTime();
    if (startTime > 0L && (System.currentTimeMillis() - startTime) % 180000L < 75L)
      ZUIServer.open(UIOverlayRPGTip.class, (EntityPlayerMP)event.player, new Object[] { TIPS[(int)(System.currentTimeMillis() / 180000L % TIPS.length)] }); 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\listener\DungeonServerTipListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */