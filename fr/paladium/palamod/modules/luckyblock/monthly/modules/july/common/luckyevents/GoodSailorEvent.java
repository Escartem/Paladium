package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fr.paladium.palaforgeutils.lib.cooldown.CooldownUtils;
import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class GoodSailorEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Connaître les océans en profondeur";
  
  private static final String TEXTURE_PATH = "july/good_sailor";
  
  private static final int RARITY = 300;
  
  private static final boolean IS_BAD = false;
  
  private static final String INFORMATION_MESSAGE = "&eVous avez 2 minutes pour vous noyer afin d'obtenir une récompense !";
  
  private static final String FAILURE_MESSAGE = "&cVous n'avez pas réussi à vous noyer à temps !";
  
  private static final String SUCCESS_MESSAGE = "&aVous avez réussi à vous noyer à temps !";
  
  private static final long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(2L);
  
  private static final String COOLDOWN_NAME = "good_sailor";
  
  private final Set<UUID> uuidList = new HashSet<>();
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eVous avez 2 minutes pour vous noyer afin d'obtenir une récompense !" });
    MonthlyUtils.startTimedHeliosEvent(player, getClass(), EXPIRATION_TIME, System.currentTimeMillis());
    CooldownUtils.setCooldown((Entity)player, "good_sailor", EXPIRATION_TIME);
  }
  
  @SubscribeEvent
  public void onDeath(LivingDeathEvent event) {
    Entity entity = event.entity;
    if (entity.field_70170_p.field_72995_K)
      return; 
    if (!(entity instanceof EntityPlayer))
      return; 
    EntityPlayer player = (EntityPlayer)entity;
    DamageSource source = event.source;
    if (source != DamageSource.field_76369_e)
      return; 
    if (!CooldownUtils.isOnCooldown((Entity)player, "good_sailor"))
      return; 
    MonthlyUtils.stopHeliosEvent((EntityPlayerMP)player, getClass());
    CooldownUtils.removeCooldown((Entity)player, "good_sailor");
    this.uuidList.add(player.func_110124_au());
  }
  
  @SubscribeEvent
  public void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
    EntityPlayer player = event.player;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (!this.uuidList.contains(player.func_110124_au()))
      return; 
    this.uuidList.remove(player.func_110124_au());
    MonthlyUtils.sendMessage(player, new String[] { "&aVous avez réussi à vous noyer à temps !" });
    InventoryUtils.giveOrDropitems(player, new ItemStack(ItemsRegister.DUTCH_BOAT));
  }
  
  public String getName() {
    return "Connaître les océans en profondeur";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "july/good_sailor";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "ne fais qu'un avec l'océan avant le temps imparti !" };
  }
  
  public String getAction() {
    return "temps restant : ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\GoodSailorEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */