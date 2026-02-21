package fr.paladium.palamod.modules.luckyblock.items.halloween;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockPumpkinCommu extends ItemBlock {
  public ItemBlockPumpkinCommu(Block p_i45328_1_) {
    super(p_i45328_1_);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean flag) {
    list.add(I18n.func_135052_a(this.field_150939_a.func_149739_a() + ".desc", new Object[0]));
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\halloween\ItemBlockPumpkinCommu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */