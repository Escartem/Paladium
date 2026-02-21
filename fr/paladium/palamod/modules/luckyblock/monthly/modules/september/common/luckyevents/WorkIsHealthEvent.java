package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.server.objects.WorkData;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.world.BlockEvent;

public class WorkIsHealthEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Le travail c'est la santé";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "september/work_is_health";
  
  private static final String ALERT_MESSAGE = "&aLe travail c’est la santé! &eCassez des blocs pour ne pas mourir!";
  
  public static WorkIsHealthEvent INSTANCE;
  
  private final Map<UUID, WorkData> works;
  
  public WorkIsHealthEvent() {
    INSTANCE = this;
    this.works = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aLe travail c’est la santé! &eCassez des blocs pour ne pas mourir!" });
    this.works.put(player.func_110124_au(), new WorkData(player));
    applyPotionEffect(player);
  }
  
  @SubscribeEvent
  public void onBreak(BlockEvent.BreakEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.getPlayer();
    if (event.isCanceled())
      return; 
    UUID uniqueId = player.func_110124_au();
    WorkData data = get(uniqueId);
    long now = System.currentTimeMillis();
    if (data == null || data.isExpired(now))
      return; 
    data.setLastBreakMillis(now);
  }
  
  public void applyPotionEffect(EntityPlayerMP playerMP) {
    PotionEffect effect = new PotionEffect(PLuckyBlock.WORK.field_76415_H, MonthlyUtils.translateSecondsToTicks(30), 0);
    playerMP.func_70690_d(effect);
  }
  
  public WorkData get(UUID uuid) {
    return this.works.get(uuid);
  }
  
  public void remove(UUID uuid) {
    this.works.remove(uuid);
  }
  
  public String getName() {
    return "Le travail c'est la santé";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "september/work_is_health";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Tu dois encore miner un bloc par seconde..." };
  }
  
  public String getAction() {
    return "temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\WorkIsHealthEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */