package fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.paladium.palamod.modules.luckyblock.ClientProxy;
import fr.paladium.palamod.modules.luckyblock.blocks.BlockLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.monthly.modules.september.common.tiles.TileEntitySeptemberLuckyBlock;
import fr.paladium.palamod.modules.luckyblock.utils.LuckyType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSeptemberLuckyBlock extends BlockLuckyBlock {
  public static final String NAME = "september_lucky_block";
  
  public BlockSeptemberLuckyBlock() {
    super(Material.field_151576_e, LuckyType.SEPTEMBER, "september_lucky_block", "palamod:september_lucky_block");
  }
  
  public boolean func_149686_d() {
    return false;
  }
  
  public boolean func_149662_c() {
    return false;
  }
  
  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return ClientProxy.renderSeptemberLuckyBlock;
  }
  
  public boolean hasTileEntity(int metadata) {
    return true;
  }
  
  public TileEntity createTileEntity(World world, int metadata) {
    return (TileEntity)new TileEntitySeptemberLuckyBlock(getType());
  }
}


/* Location:              E:\Paladium\!\fr\paladium\palamod\modules\luckyblock\monthly\modules\september\common\blocks\BlockSeptemberLuckyBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */