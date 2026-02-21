package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.hud.luckyevent.IHeliosLuckyEventWidget;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.items.ItemEmptyTradeFile;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;

public class ProfessionalSecrecyEvent extends ALuckyEvent implements IHeliosLuckyEventWidget {
  private static final String EVENT_NAME = "Secret professionnel";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 250;
  
  private static final String TEXTURE_PATH = "september/professional_secrecy";
  
  private static final String[] START_MESSAGES = new String[] { "&eAcceptes-tu de signer ce contrat et de respecter le secret professionnel ?", "&6Écrivez OUI ou NON dans le chat pour répondre." };
  
  private static final String WRONG_ANSWER_MESSAGE = "&cVous devez répondre par OUI ou NON.";
  
  private static final String REFUSE_MESSAGE = "&cVous avez choisi de ne pas respecter le secret professionnel.";
  
  private static final String ACCEPT_MESSAGE = "&aVous devez respecter le secret professionnel pendant 30 minutes.";
  
  private static final String CANT_SPEAK_MESSAGE = "&cVous devez respecter le secret professionnel pendant %s.";
  
  private static final String WIN_MESSAGE = "&avous avez respecté le secret professionnel. Voici votre récompense!";
  
  private static final String YES = "oui";
  
  private static final String NO = "non";
  
  private static final long NONE = -2L;
  
  private static final long WAITING = -1L;
  
  private static final long DURATION = TimeUnit.MINUTES.toMillis(30L);
  
  public static ProfessionalSecrecyEvent INSTANCE;
  
  private final Map<UUID, Long> cooldowns;
  
  public ProfessionalSecrecyEvent() {
    INSTANCE = this;
    this.cooldowns = new HashMap<>();
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, START_MESSAGES);
    this.cooldowns.put(player.func_110124_au(), Long.valueOf(-1L));
  }
  
  @SubscribeEvent
  public void onInteractWithPlayer(EntityInteractEvent event) {
    EntityPlayerMP player = (EntityPlayerMP)event.entityPlayer;
    ItemStack stack = event.entityPlayer.func_70694_bm();
    if (stack == null || stack.func_77973_b() != ItemsRegister.EMPTY_TRADE_FILE)
      return; 
    if (!(event.target instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP target = (EntityPlayerMP)event.target;
    ItemEmptyTradeFile item = (ItemEmptyTradeFile)stack.func_77973_b();
    ItemUtils.spawnItemAtEntity((Entity)player, item.createTradeFile(player, target, stack));
  }
  
  @SubscribeEvent
  public void onPlayerChat(ServerChatEvent event) {
    EntityPlayerMP player = event.player;
    UUID uniqueId = player.func_110124_au();
    long now = System.currentTimeMillis();
    long cooldown = getCooldown(uniqueId);
    if (cooldown == -2L)
      return; 
    if (cooldown == -1L) {
      if (event.message.equalsIgnoreCase("oui")) {
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous devez respecter le secret professionnel pendant 30 minutes." });
        this.cooldowns.put(uniqueId, Long.valueOf(now + DURATION));
        MonthlyUtils.startTimedHeliosEvent(player, getClass(), DURATION, System.currentTimeMillis());
      } else if (event.message.equalsIgnoreCase("non")) {
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous avez choisi de ne pas respecter le secret professionnel." });
        this.cooldowns.remove(uniqueId);
      } else {
        MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous devez répondre par OUI ou NON." });
      } 
      if (TimeIsMoneyEvent.INSTANCE.shouldHandle(player))
        return; 
      return;
    } 
    if (isOnCooldown(cooldown, now)) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&cVous avez choisi de ne pas respecter le secret professionnel." });
      this.cooldowns.remove(uniqueId);
      event.setCanceled(true);
      return;
    } 
    this.cooldowns.remove(uniqueId);
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&avous avez respecté le secret professionnel. Voici votre récompense!" });
    for (int i = 0; i < 5; i++)
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(ItemsRegister.EMPTY_TRADE_FILE)); 
  }
  
  public boolean isOnCooldown(long cooldown, long now) {
    return (cooldown > now);
  }
  
  public long getCooldown(UUID uniqueId) {
    if (!this.cooldowns.containsKey(uniqueId))
      return -2L; 
    return ((Long)this.cooldowns.get(uniqueId)).longValue();
  }
  
  public String getName() {
    return "Secret professionnel";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 250;
  }
  
  public String getTexture() {
    return "september/professional_secrecy";
  }
  
  public boolean isTimerType() {
    return true;
  }
  
  public String[] getDescription() {
    return new String[] { "Vous devez encore respecter le secret professionnel" };
  }
  
  public String getAction() {
    return "temps restant: ";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\ProfessionalSecrecyEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */