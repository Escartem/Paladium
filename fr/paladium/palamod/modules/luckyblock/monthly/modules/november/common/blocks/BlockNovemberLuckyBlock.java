package fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.november.common.tiles.TileEntityNovemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockNovemberLuckyBlock extends BlockLuckyBlock {
  public static final String NAME = "november_lucky_block";
  
  public BlockNovemberLuckyBlock() {
    super(Material.field_151576_e, LuckyType.NOVEMBER, "november_lucky_block", "palamod:november_lucky_block");
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderNovemberLuckyBlock;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntityNovemberLuckyBlock(getType());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\november\common\blocks\BlockNovemberLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */