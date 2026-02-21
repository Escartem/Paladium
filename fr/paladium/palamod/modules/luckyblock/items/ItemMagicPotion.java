package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.common.utils.ITooltipWiki;
import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMagicPotion extends Item implements ITooltipWiki {
  public ItemMagicPotion() {
    func_77625_d(1);
    func_77656_e(9);
    func_77637_a(PLuckyBlock.TAB);
    func_77655_b("magic_potion");
    func_111206_d("palamod:magic_potion");
  }
  
  public ItemStack func_77654_b(ItemStack item, World world, EntityPlayer player) {
    if (!world.field_72995_K && WorldGuardUtils.isItemEffectBlocked(world, player.field_70165_t, player.field_70163_u, player.field_70161_v, 
        Item.func_150891_b(item.func_77973_b())))
      return item; 
    if (!player.field_71075_bZ.field_75098_d)
      item.func_77972_a(1, (EntityLivingBase)player); 
    if (!world.field_72995_K) {
      player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 600, 1));
      player.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 600, 0));
    } 
    if (!player.field_71075_bZ.field_75098_d && 
      item.field_77994_a <= 0)
      return new ItemStack(Items.field_151069_bo); 
    return item;
  }
  
  public int func_77626_a(ItemStack item) {
    return 20;
  }
  
  public EnumAction func_77661_b(ItemStack p_77661_1_) {
    return EnumAction.drink;
  }
  
  public ItemStack func_77659_a(ItemStack item, World world, EntityPlayer player) {
    player.func_71008_a(item, func_77626_a(item));
    return item;
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    return false;
  }
  
  public void func_77624_a(ItemStack item, EntityPlayer player, List<String> list, boolean flag) {
    list.add("§eDonne un effet de :");
    list.add("  §bRégénération II");
    list.add("  §bSaturation I");
    list.add("§ependant 30 secondes");
    super.func_77624_a(item, player, list, flag);
  }
  
  public String getUrl(ItemStack arg0) {
    return "https://wiki.paladium-pvp.fr/gameplay/lucky-blocks/outils-et-items#1.-lucky-block-en-paladium-et-en-endium";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemMagicPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */