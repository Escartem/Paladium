package fr.paladium.palamod.modules.troll.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.modules.troll.commands.BaguetteCommand;
import fr.paladium.palamod.util.MixinBuilderHelper;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;

public class BaguetteEventHandler {
  private Map<UUID, Long> lastSend = new HashMap<>();
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent e) {
    if (BaguetteCommand.active && BaguetteCommand.firework != null && ((Long)this.lastSend.getOrDefault(e.player.func_110124_au(), Long.valueOf(0L))).longValue() + 12000L < System.currentTimeMillis() && !e.player.field_70170_p.field_72995_K && MixinBuilderHelper.isInArea((int)e.player.field_70165_t, (int)e.player.field_70161_v)) {
      this.lastSend.put(e.player.func_110124_au(), Long.valueOf(System.currentTimeMillis()));
      EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(e.player.field_70170_p, e.player.field_70165_t, e.player.field_70163_u + 3.0D, e.player.field_70161_v, BaguetteCommand.firework);
      entityfireworkrocket.field_70145_X = true;
      e.player.field_70170_p.func_72838_d((Entity)entityfireworkrocket);
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\troll\events\BaguetteEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */