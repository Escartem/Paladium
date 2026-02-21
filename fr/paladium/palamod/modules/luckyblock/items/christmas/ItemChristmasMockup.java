package fr.paladium.palamod.modules.luckyblock.items.christmas;

import fr.paladium.palamod.modules.luckyblock.blocks.christmas.BlockChristmasMockup;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemChristmasMockup extends ItemBlock {
  public ItemChristmasMockup(Block block) {
    super(block);
    func_77627_a(true);
  }
  
  public String func_77667_c(ItemStack itemStack) {
    int buffer = itemStack.func_77960_j();
    if (buffer < 0 || buffer >= BlockChristmasMockup.subBlocks.length)
      buffer = 0; 
    return "tile.christmas_mockup." + BlockChristmasMockup.subBlocks[buffer];
  }
  
  public int func_77647_b(int metadata) {
    return metadata;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\items\christmas\ItemChristmasMockup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */