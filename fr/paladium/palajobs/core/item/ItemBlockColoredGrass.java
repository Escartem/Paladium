package fr.paladium.palajobs.core.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palajobs.core.block.BlockColoredGrass;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockColoredGrass extends ItemBlock {
  public ItemBlockColoredGrass(Block block) {
    super(block);
    func_77625_d(64);
    func_77627_a(true);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int p_77617_1_) {
    return this.field_150939_a.func_149691_a(2, p_77617_1_);
  }
  
  public int func_77647_b(int p_77647_1_) {
    return p_77647_1_;
  }
  
  public String func_77657_g(ItemStack p_77657_1_) {
    return func_77658_a() + "." + BlockColoredGrass.getColorById(p_77657_1_.func_77960_j());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palajobs\core\item\ItemBlockColoredGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */