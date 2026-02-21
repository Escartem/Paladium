package fr.paladium.palamod.modules.paladium.utils;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;

public interface IObject {
  String getName();
  
  Block getObject();
  
  ItemBlock getItemBlock();
  
  Class<? extends TileEntity> getTile();
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\paladiu\\utils\IObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */