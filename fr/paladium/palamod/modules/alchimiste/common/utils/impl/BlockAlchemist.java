package fr.paladium.palamod.modules.alchimiste.common.utils.impl;

import fr.paladium.palamod.api.BlocksRegister;
import fr.paladium.palamod.modules.alchimiste.PAlchimiste;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockAlchemist extends Block {
  public BlockAlchemist(Material p_i45394_1_) {
    super(p_i45394_1_);
    func_149647_a(PAlchimiste.TAB);
  }
  
  public abstract Class<? extends TileEntity> getTileEntity();
  
  public abstract String getName();
  
  public float getEnchantPowerBonus(World world, int x, int y, int z) {
    return (this == BlocksRegister.MYSTICAL_BOOKSHELF) ? 4.0F : 0.0F;
  }
  
  public ItemBlock getCustomStack() {
    return null;
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\alchimiste\commo\\utils\impl\BlockAlchemist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */