package fr.paladium.palamod.modules.luckyblock.items;

import fr.paladium.palamod.modules.luckyblock.PLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.entity.EntityNametagFirework;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemNameTagFirework extends ItemFirework {
  public ItemNameTagFirework() {
    func_77655_b("fireworks");
    func_77637_a(PLuckyBlock.TAB);
    func_111206_d("minecraft:fireworks");
  }
  
  public boolean func_77648_a(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_) {
    if (item != null && 
      item.func_77942_o() && 
      item.func_77978_p().func_74764_b("nametag")) {
      world.func_72838_d((Entity)new EntityNametagFirework(world, x, y, z, item
            .func_77978_p().func_74779_i("nametag")));
      item.field_77994_a--;
    } 
    return true;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemNameTagFirework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */