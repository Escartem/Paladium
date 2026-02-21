package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palaforgeutils.lib.bukkit.WorldGuardUtils;
import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCalcium extends ItemBucketMilk {
  public ItemCalcium() {
    func_77655_b("calcium");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("palamod:calcium");
    func_77656_e(10);
  }
  
  public ItemStack func_77654_b(ItemStack item, World word, EntityPlayer player) {
    if (item != null && !WorldGuardUtils.isItemEffectBlocked((Entity)player, item)) {
      item.func_77964_b(item.func_77960_j() + 1);
      player.func_70674_bp();
      if (item.func_77960_j() > 9)
        item = null; 
    } 
    return item;
  }
  
  public EnumAction func_77661_b(ItemStack item) {
    return EnumAction.drink;
  }
  
  public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
    if (stack != null && entity instanceof net.minecraft.entity.passive.EntityCow)
      stack.func_77964_b(0); 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemCalcium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */