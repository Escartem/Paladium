package fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.march.common.tile.TileEntityMarchLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMarchLuckyBlock extends BlockLuckyBlock {
  public static final String NAME = "march_lucky_block";
  
  public BlockMarchLuckyBlock() {
    super(Material.field_151576_e, LuckyType.MARCH, "march_lucky_block", "palamod:march_lucky_block");
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderMarchLuckyBlock;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityMarchLuckyBlock(getType());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\march\common\block\BlockMarchLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */