package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemChocolateMilk extends ItemFood {
  public ItemChocolateMilk() {
    super(1, 1.0F, false);
    func_77655_b("chocolate_milk");
    func_111206_d("palamod:chocolate_milk");
    func_77637_a(PLuckyBlock.TAB);
    func_77625_d(16);
  }
  
  protected void func_77849_c(ItemStack item, World world, EntityPlayer player) {
    player.func_70690_d(new PotionEffect(Potion.field_76424_c.field_76415_H, 600, 1));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemChocolateMilk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */