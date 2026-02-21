package fr.paladium.palarpg.module.dungeon.server.listener;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.paladiumui.kit.notification.Notification;
import fr.paladium.palaforgeutils.lib.java.map.player.SessionPlayerMap;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.client.ui.backpack.UIDungeonBackpack;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.palarpg.module.equipment.common.item.IRPGItem;
import fr.paladium.palarpg.module.profile.common.playerdata.RPGProfilePlayerData;
import fr.paladium.zephyrui.internal.mod.server.utils.ZUIServer;
import java.util.Optional;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class DungeonServerBackpackListener {
  private final SessionPlayerMap<Long> cooldownMap = new SessionPlayerMap<Long>() {
      public Long getDefaultValue() {
        return Long.valueOf(0L);
      }
    };
  
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)event.player, "dungeon");
    if (data == null)
      return; 
    data.getBackpack().clear();
    data.sync();
  }
  
  @SubscribeEvent
  public void onInteract(PlayerInteractEvent event) {
    if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && event.action != PlayerInteractEvent.Action.RIGHT_CLICK_AIR)
      return; 
    EntityPlayer player = event.entityPlayer;
    if (player == null || player.func_70694_bm() == null || player.func_70694_bm().func_77973_b() == null || System.currentTimeMillis() - ((Long)this.cooldownMap.get((Entity)player)).longValue() < 200L)
      return; 
    Optional<IRPGItem> optionalItem = IRPGItem.get(player.func_70694_bm());
    if (!optionalItem.isPresent())
      return; 
    IRPGItem rpgItem = optionalItem.get();
    Optional<Number> optionalTag = rpgItem.getTag("backpack");
    if (!optionalTag.isPresent())
      return; 
    RPGProfilePlayerData profile = (RPGProfilePlayerData)RPGPlayer.getData((Entity)player, "profile");
    if (profile == null || rpgItem.getItemData().getRequiredLevel() > profile.getLevel()) {
      (new Notification(Notification.NotificationType.ERROR, "Vous devez être niveau " + rpgItem.getItemData().getRequiredLevel() + " RPG pour utiliser cet item", "RPG")).send((EntityPlayerMP)player);
      return;
    } 
    ZUIServer.open(UIDungeonBackpack.class, (EntityPlayerMP)player, new Object[] { Integer.valueOf(((Number)optionalTag.get()).intValue()) });
    this.cooldownMap.put((Entity)player, Long.valueOf(System.currentTimeMillis()));
    event.useBlock = Event.Result.DENY;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\listener\DungeonServerBackpackListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */