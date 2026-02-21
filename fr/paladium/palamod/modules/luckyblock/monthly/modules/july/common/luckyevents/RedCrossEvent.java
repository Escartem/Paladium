package fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.luckyevents;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.commands.StructureCommand;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.july.common.structures.RedCrossStructure;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import fr.paladium.palamod.modules.luckyblock.utils.ALuckyEvent;
import java.util.concurrent.TimeUnit;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class RedCrossEvent extends ALuckyEvent {
  public static final String STRUCTURE_NAME = "cross";
  
  public static final ItemStack[] CHEST_ITEMS = new ItemStack[] { new ItemStack(ItemsRegister.INVULNERABILITY_POTION, 1) };
  
  public static final int[] BLACKLISTED_POTIONS_IDS = new int[] { Potion.field_76436_u.field_76415_H, Potion.field_76437_t.field_76415_H, Potion.field_76421_d.field_76415_H, Potion.field_76431_k.field_76415_H, Potion.field_82731_v.field_76415_H };
  
  private static final String EVENT_NAME = "La croix rouge";
  
  private static final String TEXTURE_PATH = "july/red_cross";
  
  private static final int RARITY = 1550;
  
  private static final boolean IS_BAD = false;
  
  public void perform(EntityPlayerMP player, int x, int y, int z) {
    RedCrossStructure structure = new RedCrossStructure((EntityPlayer)player);
    if (!structure.canSpawn()) {
      MonthlyUtils.sendMessage((EntityPlayer)player, structure.getFailureMessage("cross"));
      StructureCommand.addWaitingPlayer((EntityPlayer)player, "cross");
      return;
    } 
    EyePatchEvent.EXECUTOR.schedule(() -> structure.spawn(), 250L, TimeUnit.MILLISECONDS);
  }
  
  @SubscribeEvent
  public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
    if (!(event.entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entity;
    if (!MonthlyUtils.hasInvulnerabilityEffect((EntityLivingBase)player))
      return; 
    for (int potionId : BLACKLISTED_POTIONS_IDS) {
      if (player.func_82165_m(potionId))
        player.func_82170_o(potionId); 
    } 
  }
  
  @SubscribeEvent
  public void onLivingHurt(LivingHurtEvent event) {
    if (!(event.entity instanceof EntityPlayerMP))
      return; 
    EntityPlayerMP player = (EntityPlayerMP)event.entity;
    if (!MonthlyUtils.hasInvulnerabilityEffect((EntityLivingBase)player))
      return; 
    DamageSource source = event.source;
    if (source.func_82725_o() && source.func_76355_l().equals("indirectMagic"))
      event.setCanceled(true); 
  }
  
  public String getName() {
    return "La croix rouge";
  }
  
  public boolean isBad() {
    return false;
  }
  
  public int getRarity() {
    return 1550;
  }
  
  public String getTexture() {
    return "july/red_cross";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\july\common\luckyevents\RedCrossEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */