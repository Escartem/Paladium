package fr.paladium.palamod.modules.luckyblock.blocks;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockEndium extends ItemBlock {
  public ItemBlockEndium(Block block) {
    super(block);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
    super.func_77624_a(stack, p_77624_2_, list, p_77624_4_);
    list.add("§cNe peut pas être décrafté");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\ItemBlockEndium.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */