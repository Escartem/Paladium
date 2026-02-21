package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.potion;

import fr.paladium.palaforgeutils.lib.inventory.InventoryUtils;
import fr.paladium.palaforgeutils.lib.potion.APotion;
import fr.paladium.palamod.api.ItemsRegister;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.luckyevents.HeadInStarsEvent;
import fr.paladium.palamod.modules.luckyblock.monthly.utils.MonthlyUtils;
import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class PotionStar extends APotion {
  public PotionStar() {
    super("star", true, Color.YELLOW);
  }
  
  public void consume(EntityPlayer player, PotionEffect potionEffect) {}
  
  public void tick(EntityPlayer player, PotionEffect potionEffect) {
    if (player.field_70170_p.field_72995_K)
      return; 
    HeadInStarsEvent event = HeadInStarsEvent.getInstance();
    if (potionEffect.func_76459_b() <= 1 && event.isPending(player)) {
      event.remove(player);
      MonthlyUtils.sendMessage(player, new String[] { "§eVous êtes revenu sur Terre!" });
      InventoryUtils.giveOrDropitems(player, new ItemStack(ItemsRegister.STAR_HELMET));
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\potion\PotionStar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */