package fr.paladium.palamod.modules.egghunt.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

public class ItemEgg extends ItemBlock {
  public ItemEgg(Block block) {
    super(block);
    func_77625_d(1);
    func_77627_a(true);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int p_77617_1_) {
    return this.field_150939_a.func_149691_a(2, p_77617_1_);
  }
  
  public int func_77647_b(int p_77647_1_) {
    return p_77647_1_;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\egghunt\common\item\ItemEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */