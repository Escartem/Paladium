package fr.paladium.palamod.modules.chisel.block.impl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import lombok.NonNull;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockChiselMetadata extends ItemBlock {
  public ItemBlockChiselMetadata(@NonNull Block block) {
    super(block);
    if (block == null)
      throw new NullPointerException("block is marked non-null but is null"); 
    func_77656_e(0);
    func_77627_a(true);
  }
  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int damage) {
    return this.field_150939_a.func_149735_b(2, damage);
  }
  
  public int func_77647_b(int damage) {
    return damage;
  }
  
  public String func_77667_c(@NonNull ItemStack item) {
    if (item == null)
      throw new NullPointerException("item is marked non-null but is null"); 
    return "tile." + ((BlockChiselMetadata)this.field_150939_a).getObject(item.func_77960_j()).getName();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\chisel\block\impl\BlockChiselMetadata$ItemBlockChiselMetadata.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */