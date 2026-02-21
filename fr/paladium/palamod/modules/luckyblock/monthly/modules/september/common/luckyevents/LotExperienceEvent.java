package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.network.PlayerLuckyBlockProperties;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class LotExperienceEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Beaucoup d'expérience dans le domaine";
  
  private static final boolean IS_BAD = false;
  
  private static final int RARITY = 700;
  
  private static final String TEXTURE_PATH = "september/lot_experience";
  
  private static final String START_MESSAGE = "C’est tout un art de gagner de l’expérience. Et sur Minecraft cela demande souvent de tuer des monstres. Gagne de l’expérience dans ce domaine pour obtenir une récompense !";
  
  private static final String SUCCESS_MESSAGE = "&aVous avez gagné un livre enchanté !";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)player);
    if (properties == null)
      return; 
    MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "C’est tout un art de gagner de l’expérience. Et sur Minecraft cela demande souvent de tuer des monstres. Gagne de l’expérience dans ce domaine pour obtenir une récompense !" });
    properties.setLuckyExperienceStarted(true);
    properties.setLuckyExperienceAmount(0);
  }
  
  @SubscribeEvent
  public void onKill(LivingDeathEvent event) {
    Entity entity = event.entity;
    if (entity.field_70170_p.field_72995_K)
      return; 
    if (entity instanceof EntityPlayer)
      return; 
    Entity killer = event.source.func_76346_g();
    if (!(killer instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP killerMP = (EntityPlayerMP)killer;
    PlayerLuckyBlockProperties properties = PlayerLuckyBlockProperties.get((EntityPlayer)killerMP);
    int killedAmount = getKilledAmount(properties);
    if (killedAmount == -1)
      return; 
    killedAmount++;
    giveReward(killerMP, properties, killedAmount);
    properties.setLuckyExperienceAmount(killedAmount);
  }
  
  public void giveReward(EntityPlayerMP player, PlayerLuckyBlockProperties properties, int killedAmount) {
    if (killedAmount > 1000)
      return; 
    if (killedAmount == 100) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez gagné un livre enchanté !" });
      ItemUtils.spawnItemAtEntity((Entity)player, MonthlyUtils.getEnchantedBook(PLuckyBlock.EXPERIENCE.field_77352_x, 1));
      return;
    } 
    if (killedAmount == 500) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez gagné un livre enchanté !" });
      ItemUtils.spawnItemAtEntity((Entity)player, MonthlyUtils.getEnchantedBook(PLuckyBlock.EXPERIENCE.field_77352_x, 2));
      return;
    } 
    if (killedAmount == 1000) {
      MonthlyUtils.sendMessage((EntityPlayer)player, new String[] { "&aVous avez gagné un livre enchanté !" });
      ItemUtils.spawnItemAtEntity((Entity)player, MonthlyUtils.getEnchantedBook(PLuckyBlock.EXPERIENCE.field_77352_x, 3));
      return;
    } 
  }
  
  public int getKilledAmount(PlayerLuckyBlockProperties properties) {
    if (properties == null)
      return -1; 
    if (!properties.isLuckyExperienceStarted())
      return -1; 
    return properties.getLuckyExperienceAmount();
  }
  
  public String getName() {
    return "Beaucoup d'expérience dans le domaine";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 700;
  }
  
  public String getTexture() {
    return "september/lot_experience";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\luckyevents\LotExperienceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */