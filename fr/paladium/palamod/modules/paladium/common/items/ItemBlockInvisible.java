package fr.paladium.palamod.modules.paladium.common.items;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemBlockInvisible extends ItemBlock {
  public ItemBlockInvisible(Block block) {
    super(block);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean b) {
    super.func_77624_a(stack, player, list, b);
    list.add(EnumChatFormatting.RED + "Warning : unbreakable by hand.");
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladium\common\items\ItemBlockInvisible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */