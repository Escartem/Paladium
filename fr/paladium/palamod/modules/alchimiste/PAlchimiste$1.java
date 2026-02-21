package fr.paladium.palamod.modules.alchimiste;

import fr.paladium.palamod.api.BlocksRegister;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

final class null extends CreativeTabs {
  null(String x0) {
    super(x0);
  }
  
  public Item func_78016_d() {
    return (BlocksRegister.EXTRACTOR == null) ? Item.func_150898_a(Blocks.field_150348_b) : Item.func_150898_a((Block)BlocksRegister.EXTRACTOR);
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\PAlchimiste$1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */