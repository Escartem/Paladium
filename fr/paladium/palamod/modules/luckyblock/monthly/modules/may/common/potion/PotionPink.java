package fr.paladium.palamod.modules.luckyblock.monthly.modules.may.common.potion;

import fr.paladium.palaforgeutils.lib.item.ItemUtils;
import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.awt.Color;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class PotionPink extends APotion {
  public PotionPink() {
    super("pink_effect", false, Color.PINK);
  }
  
  public void consume(EntityPlayer entityPlayer, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {
    if (player.field_70170_p.field_72995_K)
      return; 
    if (potionEffect.func_76459_b() == 1) {
      ItemUtils.spawnItemAtEntity((Entity)player, new ItemStack(BlocksRegister.STONE_HEARTH));
      MonthlyUtils.sendMessage(player, new String[] { "§dVous avez reçu une récompense!" });
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\may\common\potion\PotionPink.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */