package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.tasks.EyePatchRunnable;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class EyePatchEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Cache-oeil";
  
  private static final String TEXTURE_PATH = "july/eye_patch";
  
  private static final int RARITY = 300;
  
  private static final boolean IS_BAD = true;
  
  private static final String ALERT_MESSAGE = "&eVous avez un cache-oeil pendant 2 minutes !";
  
  private static final long BLOCKER_DURATION = TimeUnit.MINUTES.toMillis(2L);
  
  private static final Map<UUID, Long> EQUIPED_MAP = new HashMap<>();
  
  public static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.EYE_PATCH.field_76415_H, 2400, 0));
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eVous avez un cache-oeil pendant 2 minutes !" });
    EyePatchRunnable runnable = new EyePatchRunnable((EntityPlayer)player);
    EXECUTOR.schedule((Runnable)runnable, BLOCKER_DURATION - TimeUnit.SECONDS.toMillis(1L), TimeUnit.MILLISECONDS);
  }
  
  @SubscribeEvent
  public void onPlayerTick(TickEvent.PlayerTickEvent event) {
    EntityPlayer player = event.player;
    if (player.field_70170_p.field_72995_K)
      return; 
    ItemStack helmetStack = player.field_71071_by.field_70460_b[3];
    UUID uniqueId = player.func_110124_au();
    boolean hasEyePatch = (helmetStack != null && helmetStack.func_77973_b() instanceof fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.items.ItemEyePatch);
    if (EQUIPED_MAP.containsKey(uniqueId)) {
      long expiration = ((Long)EQUIPED_MAP.get(uniqueId)).longValue();
      long now = System.currentTimeMillis();
      if (expiration < now) {
        EQUIPED_MAP.remove(uniqueId);
        player.func_82170_o(PLuckyBlock.EYE_PATCH.field_76415_H);
      } else if (!hasEyePatch) {
        player.func_70690_d(new PotionEffect(PLuckyBlock.EYE_PATCH.field_76415_H, 200, 0));
      } 
      return;
    } 
    if (hasEyePatch && !EQUIPED_MAP.containsKey(uniqueId))
      EQUIPED_MAP.put(uniqueId, Long.valueOf(System.currentTimeMillis() + BLOCKER_DURATION)); 
  }
  
  private boolean canRemoveEyePatch(EntityPlayer player) {
    if (!EQUIPED_MAP.containsKey(player.func_110124_au()))
      return true; 
    long now = System.currentTimeMillis();
    return (((Long)EQUIPED_MAP.get(player.func_110124_au())).longValue() < now);
  }
  
  public String getName() {
    return "Cache-oeil";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 300;
  }
  
  public String getTexture() {
    return "july/eye_patch";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\EyePatchEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */