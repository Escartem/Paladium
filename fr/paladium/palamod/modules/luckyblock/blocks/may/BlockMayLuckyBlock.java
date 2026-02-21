package fr.paladium.palamod.modules.luckyblock.blocks.may;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.tileentity.may.TileEntityMayLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMayLuckyBlock extends BlockLuckyBlock {
  public BlockMayLuckyBlock() {
    super(Material.field_151576_e, LuckyType.MAY, "mayLuckyBlock", "palamod:may_lucky_block");
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderMayLuckyBlock;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityMayLuckyBlock(getType());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\blocks\may\BlockMayLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */