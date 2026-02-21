package fr.paladium.palamod.modules.luckyblock.monthly.modules.august.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class ArmorBakeEvent extends ALuckyEvent {
  private static final String EVENT_NAME = "Tu vas cuire dans cette armure !";
  
  private static final boolean IS_BAD = true;
  
  private static final int RARITY = 350;
  
  private static final String TEXTURE_PATH = "august/armor_bake";
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    player.func_70690_d(new PotionEffect(PLuckyBlock.BAKING.field_76415_H, MonthlyUtils.translateSecondsToTicks(180), 0));
  }
  
  @SubscribeEvent
  public void onLivingTick(TickEvent.PlayerTickEvent event) {
    EntityPlayer player = event.player;
    if (player.field_70170_p.field_72995_K)
      return; 
    if (!MonthlyUtils.hasPotionEffect((EntityLivingBase)player, (Potion)PLuckyBlock.BAKING))
      return; 
    if (player.field_70173_aa % 60 != 0)
      return; 
    if (!hasArmorPiece(player))
      return; 
    player.func_70097_a(DamageSource.field_76376_m, 2.0F);
  }
  
  private boolean hasArmorPiece(EntityPlayer player) {
    return (player.field_71071_by.field_70460_b[0] != null || player.field_71071_by.field_70460_b[1] != null || player.field_71071_by.field_70460_b[2] != null || player.field_71071_by.field_70460_b[3] != null);
  }
  
  public String getName() {
    return "Tu vas cuire dans cette armure !";
  }
  
  public boolean isBad() {
    return true;
  }
  
  public int getRarity() {
    return 350;
  }
  
  public String getTexture() {
    return "august/armor_bake";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\august\common\luckyevents\ArmorBakeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */