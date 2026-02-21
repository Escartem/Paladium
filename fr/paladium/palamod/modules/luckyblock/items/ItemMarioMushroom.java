package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMarioMushroom extends ItemBucketMilk implements ITooltipWiki {
  public ItemMarioMushroom() {
    func_77655_b("mariomushroom");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:mariomushroom");
    func_77656_e(10);
  }
  
  public ItemStack func_77654_b(ItemStack item, World word, EntityPlayer player) {
    if (item != null)
      player.func_70690_d(new PotionEffect(PLuckyBlock.MARIO_MUSHROOM.field_76415_H, 99999, 0)); 
    return item;
  }
  
  public EnumAction func_77661_b(ItemStack item) {
    return EnumAction.eat;
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#2.-lucky-block-en-findium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemMarioMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */