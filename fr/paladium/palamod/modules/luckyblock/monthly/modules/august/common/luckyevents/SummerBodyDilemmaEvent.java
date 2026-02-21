package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

public class SummerBodyDilemmaEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Le dilemme du summer body";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 400;
  
  private static final String TEXTURE_PATH = "august/summer_body_dilemma";
  
  private static final long SUMMER_BODY_DURATION = TimeUnit.HOURS.toMillis(6L);
  
  private static final String SUMMER_BODY_SKING_PATH = "palamod:textures/entity/npc/shredded.png";
  
  public static final ResourceLocation SUMMER_BODY_TEXTURE = new ResourceLocation("palamod:textures/entity/npc/shredded.png");
  
  private static final String WIN_MESSAGE = "&eSummer body : &aDébloqué !";
  
  private static final String[] LOOSE_MESSAGES = new String[] { "&eSummer body : &céchec !", "&eNe t’en fais, tu n’as pas besoin de ça pour être super :D" };
  
  public static SummerBodyDilemmaEvent INSTANCE;
  
  public SummerBodyDilemmaEvent() {
    INSTANCE = this;
  }
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.RUNNING_TREADMILL));
  }
  
  public boolean canAddEffect(PlayerLuckyBlockProperties properties) {
    return (properties != null && properties.getSummerBodyExpirationMillis() > System.currentTimeMillis());
  }
  
  public void addEffect(EntityPlayer player) {
    PotionEffect effect = new PotionEffect(PLuckyBlock.SUMMER_BODY.field_76415_H, MonthlyUtils.translateSecondsToTicks(60), 0);
    effect.getCurativeItems().clear();
    player.func_70690_d(effect);
  }
  
  public void onWin(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&eSummer body : &aDébloqué !" });
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties == null)
      return; 
    properties.setSummerBodyExpirationMillis(System.currentTimeMillis() + SUMMER_BODY_DURATION);
    addEffect((EntityPlayer)player);
  }
  
  public void onLose(EntityPlayerMP player, int x, int y, int z) {
    MonthlyUtils.sendMessage((EntityPlayer)player, LOOSE_MESSAGES);
  }
  
  public String getName() {
    return "Le dilemme du summer body";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 400;
  }
  
  public String getTexture() {
    return "august/summer_body_dilemma";
  }
  
  public static ResourceLocation getSummerBodyTexture() {
    return SUMMER_BODY_TEXTURE;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\SummerBodyDilemmaEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */