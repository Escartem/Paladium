package fr.paladium.palarpg.module.dungeon.server.listener;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.command.impl.palagive.manager.PalaGiveManager;
import fr.paladium.palaforgeutils.lib.time.UniversalTimeUtils;
import fr.paladium.palarpg.common.api.ItemsRegister;
import fr.paladium.palarpg.common.extended.RPGPlayer;
import fr.paladium.palarpg.module.dungeon.common.playerdata.RPGDungeonPlayerData;
import fr.paladium.permissionbridge.common.data.PermissibleEntity;
import fr.paladium.permissionbridge.common.manager.PermissionManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class DungeonServerKeyRingListener {
  @SubscribeEvent(priority = EventPriority.LOWEST)
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    RPGDungeonPlayerData data = (RPGDungeonPlayerData)RPGPlayer.getData((Entity)event.player, "dungeon");
    if (data == null)
      return; 
    int value = ((Integer)PermissionManager.inst().getPermission(PermissibleEntity.from((Entity)event.player), "palarpg.keyring.", Integer.class).orElse(Integer.valueOf(0))).intValue();
    if (value <= 0)
      return; 
    long now = UniversalTimeUtils.now();
    long last = data.getLastKeyringReward();
    if (now - last < 86400000L)
      return; 
    data.setLastKeyringReward();
    PalaGiveManager.inst().give(event.player, new ItemStack(ItemsRegister.DUNGEON_KEYRING, value));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palarpg\module\dungeon\server\listener\DungeonServerKeyRingListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */