package fr.paladium.palamod.modules.luckyblock.items;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockTrophy extends ItemBlockWithMetadata {
  public ItemBlockTrophy(Block block) {
    super(block, block);
  }
  
  public void func_77624_a(ItemStack stack, EntityPlayer player, List<String> list, boolean flag) {
    if (stack.func_77942_o()) {
      if (stack.func_77978_p().func_74764_b("owner")) {
        list.add("§eCe trophé à été décerné à §3" + stack.func_77978_p().func_74779_i("owner"));
        list.add("§epour avoir obtenu §a" + ((stack.func_77960_j() + 1) * 25) + "%");
        list.add("§ede tous les events Lucky Blocks");
      } else {
        list.add("§eCe trophé à été décerné à §cpersonne");
        list.add("§epour avoir obtenu §a" + ((stack.func_77960_j() + 1) * 25) + "%");
        list.add("§ede tous les events Lucky Blocks");
      } 
    } else {
      list.add("§eCe trophé à été décerné à §cpersonne");
      list.add("§epour avoir obtenu §a" + ((stack.func_77960_j() + 1) * 25) + "%");
      list.add("§ede tous les events Lucky Blocks");
    } 
    super.func_77624_a(stack, player, list, flag);
  }
  
  public String func_77653_i(ItemStack stack) {
    return "§eTrophée des §b" + ((stack.func_77960_j() + 1) * 25) + "%";
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\ItemBlockTrophy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */