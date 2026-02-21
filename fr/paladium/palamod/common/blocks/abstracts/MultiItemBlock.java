package fr.paladium.palamod.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class MultiItemBlock extends ItemBlock {
  private final String[] blockType;
  
  private String unlocalizedName;
  
  private final String append;
  
  private final int[] specialIndex = new int[] { -1, -1 };
  
  public MultiItemBlock(Block b, String itemBlockUnlocalizedName, String[] blockTypes) {
    super(b);
    if (itemBlockUnlocalizedName.isEmpty()) {
      this.unlocalizedName = func_77658_a();
    } else {
      this.unlocalizedName = itemBlockUnlocalizedName;
    } 
    this.blockType = blockTypes;
    this.append = "";
  }
  
  public MultiItemBlock(Block b, String itemBlockUnlocalizedName, String appendToEnd, String[] blockTypes) {
    super(b);
    this.unlocalizedName = itemBlockUnlocalizedName;
    this.blockType = blockTypes;
    this.append = "." + appendToEnd;
  }
  
  public void setSpecialIndex(int clampIndex, int stringBuilderIndex) {
    this.specialIndex[0] = clampIndex;
    this.specialIndex[1] = stringBuilderIndex;
  }
  
  public int func_77647_b(int meta) {
    return meta;
  }
  
  public String func_77667_c(ItemStack itemstack) {
    int pos = MathHelper.func_76125_a(itemstack.func_77960_j(), 0, (this.specialIndex[0] > -1) ? this.specialIndex[0] : (this.blockType.length - 1));
    int sbIndex = (this.specialIndex[1] > -1) ? pos : (this.specialIndex[1] - pos);
    if (sbIndex < 0)
      sbIndex = -1 * sbIndex; 
    try {
      return this.unlocalizedName + "." + this.blockType[sbIndex - 1] + this.append;
    } catch (ArrayIndexOutOfBoundsException ex) {
      return func_77658_a();
    } 
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\common\blocks\abstracts\MultiItemBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */