package fr.paladium.palamod.modules.luckyblock.blocks.june;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockSoundTest extends ItemBlock {
  public ItemBlockSoundTest(Block block) {
    super(block);
    func_77625_d(1);
    func_77627_a(true);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int meta) {
    return this.field_150939_a.func_149691_a(0, meta);
  }
  
  public int func_77647_b(int meta) {
    return meta;
  }
  
  public String func_77657_g(ItemStack stack) {
    int meta = stack.func_77960_j();
    return func_77658_a() + "." + meta;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\june\ItemBlockSoundTest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */