package fr.paladium.palamod.modules.communityevents.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.common.Registry;
import fr.paladium.palamod.modules.communityevents.ClientProxy;
import fr.paladium.palamod.modules.communityevents.tiles.TileEntityBarrelWood;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBarrelWood extends Block {
  public BlockBarrelWood() {
    super(Material.field_151576_e);
    func_149647_a((CreativeTabs)Registry.PALADIUM_TAB);
    func_149663_c("barrelwood");
    func_149658_d("palamod:barrelwood");
    func_149711_c(1.0F);
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderBarrel;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityBarrelWood();
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\communityevents\blocks\BlockBarrelWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */