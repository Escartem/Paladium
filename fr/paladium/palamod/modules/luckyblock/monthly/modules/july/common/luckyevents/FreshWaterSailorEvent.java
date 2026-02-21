package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palaforgeutils.lib.task.DurationConverter;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class FreshWaterSailorEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Tu n'es qu'un marin d'eau douce";
  
  private static final String TEXTURE_PATH = "july/fresh_water_sailor";
  
  private static final int RARITY = 250;
  
  private static final boolean IS_BAD = true;
  
  private static final String COOLDOWN_NAME = "fresh_water_sailor";
  
  private static final long COOLDOWN_TIME = TimeUnit.HOURS.toMillis(2L);
  
  private static final String PERFORM_MESSAGE = "&eTu ne peux plus utiliser de bateau pendant &c%s";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    CooldownUtils.setCooldown((Entity)player, "fresh_water_sailor", COOLDOWN_TIME);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { String.format("&eTu ne peux plus utiliser de bateau pendant &c%s", new Object[] { DurationConverter.fromMillisToString(COOLDOWN_TIME) }) });
    MonthlyUtils.startTimedHeliosEvent(player, 
        getClass(), COOLDOWN_TIME, 
        System.currentTimeMillis());
  }
  
  @SubscribeEvent
  public void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
    EntityPlayer player = event.player;
    if (!CooldownUtils.isOnCooldown((Entity)player, "fresh_water_sailor"))
      return; 
    long duration = CooldownUtils.getCooldown((Entity)player, "fresh_water_sailor");
    long now = System.currentTimeMillis();
    MonthlyUtils.stopHeliosEvent((EntityPlayerMP)player, getClass());
    MonthlyUtils.startTimedHeliosEvent((EntityPlayerMP)player, 
        getClass(), duration - now, now);
  }
  
  @SubscribeEvent
  public void onInteract(EntityInteractEvent event) {
    EntityPlayer player = event.entityPlayer;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (!CooldownUtils.isOnCooldown((Entity)player, "fresh_water_sailor"))
      return; 
    if (!(event.target instanceof net.minecraft.entity.item.EntityBoat))
      return; 
    event.setCanceled(true);
    long remainingTime = CooldownUtils.getCooldown((Entity)player, "fresh_water_sailor") - System.currentTimeMillis();
    MonthlyUtils.sendMessage(player, new String[] { String.format("&eTu ne peux plus utiliser de bateau pendant &c%s", new Object[] { DurationConverter.fromMillisToString(remainingTime) }) });
  }
  
  public String getName() {
    return "Tu n'es qu'un marin d'eau douce";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "july/fresh_water_sailor";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Vous ne pouvez plus utiliser de bateau" };
  }
  
  public String getAction() {
    return "temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\FreshWaterSailorEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */