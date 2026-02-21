package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBanana extends ItemFood implements ITooltipWiki {
  public ItemBanana() {
    super(1, 1.0F, false);
    func_77655_b("banana");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:banana");
    func_77625_d(16);
  }
  
  public ItemStack func_77654_b(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K) {
      item.field_77994_a--;
      player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 300, 2));
      world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
    } 
    return item;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemBanana.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */